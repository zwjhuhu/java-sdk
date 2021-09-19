package cn.com.nexwise.sdk.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.dto.ApiResult;
import cn.com.nexwise.sdk.common.dto.TaskResult;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
	
	private static final SimpleLogger logger = new SimpleLogger(Api.class);
	
    AbstractTask task;
    
    RestInfo info;
    
    Completion completion;
        
    private ApiResult resultFromWebHook;
    
    private String signUri;
    
    String taskId;
    
    private final static PollThread pollThread;
    
    private static ConcurrentHashMap<String, Api> waitWebHookMap = new ConcurrentHashMap<>();
    
    static {
    	pollThread = new PollThread();
    	pollThread.setDaemon(true);
    	pollThread.start();
    }
    
    Api(AbstractTask task) {
      this.task = task;
      this.info = task.getRestInfo();
    }
    
    private String substituteUrl(String url, Map<String, Object> tokens) {
      Pattern pattern = Pattern.compile("\\{(.+?)\\}");
      Matcher matcher = pattern.matcher(url);
      StringBuffer buffer = new StringBuffer();
      while (matcher.find()) {
        String varName = matcher.group(1);
        Object replacement = tokens.get(varName);
        if (replacement == null)
          throw new ApiException(String.format("cannot find value for path variable [%s]", varName)); 
        matcher.appendReplacement(buffer, "");
        buffer.append(replacement.toString());
      } 
      matcher.appendTail(buffer);
      return buffer.toString();
    }
    
    private List<String> getVarNamesFromUrl(String url) {
      Pattern pattern = Pattern.compile("\\{(.+?)\\}");
      Matcher matcher = pattern.matcher(url);
      List<String> urlVars = new ArrayList<>();
      while (matcher.find())
        urlVars.add(matcher.group(1)); 
      return urlVars;
    }
    
    ApiResult call() {
        return doCall(true);
    }
    
    void call(Completion completion) {
      this.completion = completion;
      doCall(false);
    }
    
    ApiResult doCall(boolean wait) {
      this.task.checkParameters();
      Request.Builder reqBuilder = new Request.Builder();
//      if (this.action.apiTimeout != null)
//        reqBuilder.addHeader("X-API-Timeout", this.action.apiTimeout.toString()); 

      String webCallback = getWebCallback();
      if (webCallback != null) {
    	  logger.debug("find web callback url: {}",webCallback);
    	  reqBuilder.addHeader(Constants.HEADER_WEB_CALLBACK, webCallback); 
      }
      try {
        
        HttpUrl.Builder urlBuilder = fillCommonParams(reqBuilder);
        fillApiPathParams(urlBuilder);
        fillApiHeaderParams(reqBuilder);
        fillApiQueryParams(reqBuilder,urlBuilder);
        fillApiBodyParams(reqBuilder);
        reqBuilder.url(urlBuilder.build());
      } catch (Exception e) {
        throw new ApiException(e);
      } 
      Request request = reqBuilder.build();
      try {
    	  ApiResult apiRet = null;
    	  logger.debug("requset api {} params {}",info.getPath(),task.parameterMap);
        try (Response response = ApiClient.http.newCall(request).execute()) {
        	int code = response.code();
        	String body = response.body().string();
        	logger.debug("api server return code: {} body: {}",code,body);
          if (!response.isSuccessful()) {
        	  apiRet = httpError(code, body); 
          }else {
        	  taskId = response.header(Constants.HEADER_TASK_UUID);
        	  logger.info("polling task id: {}",taskId);
          }
          if (code == 200) {
        	  if(task instanceof QueryTask<?>) {
        		  apiRet = writeApiResult(code,body); 
        	  }else {
        		  apiRet = doPollOrWebCallbakProcess(wait,webCallback,response);
        	  }
          } else {
        	  throw new ApiException(Constants.HTTP_ERROR+ " server status code: " + code);
          }
        } 
        return apiRet;
      } catch (IOException e) {
        throw new ApiException(e);
      } 
    }
    
    private TaskResult doPollOrWebCallbakProcess(boolean wait, String webCallback,Response response) throws IOException {
    	if(webCallback == null) {
    		return (TaskResult) pollResult(wait,response);
    	}else {
    		if(taskId!=null) {
    			waitWebHookMap.put(taskId, this);
    		}
    		if(wait) {
    			return (TaskResult) webHookResult();
    		}else {
    			return null;
    		}
    	}
    }


	private ApiResult webHookResult() {
      synchronized (this) {
        long timeout = getTimeout();
        try {
          wait(timeout);
        } catch (InterruptedException e) {
          throw new ApiException(e);
        } 
        if (this.resultFromWebHook == null) {
          this.resultFromWebHook = new ApiResult();
          
          this.resultFromWebHook.setCode(ReturnCode.ERROR);
          String msg = String.format("waiting webhook result of api [%s] timeout after %s ms", 
        		  task.getClass().getSimpleName(), Long.valueOf(timeout));
          this.resultFromWebHook.setMsg(msg);
        } 
        return this.resultFromWebHook;
      } 
    }
    
	/**
	 * 由第三方应用在收到web推送消息的时候主动调用
	 */
    public static void onWebHookResult(HttpServletRequest request) {
    	logger.debug("onWebHookResult called");
    	String taskId = request.getHeader(Constants.HEADER_TASK_UUID);
    	if(taskId==null) {
    		//do nothing
    	}
    	taskId = taskId.trim();
    	logger.debug("onWebHookResult called for taskId {}",taskId);
    	Api api = waitWebHookMap.remove(taskId);
    	if(api==null) {
    		return;
    	}
    	
    	ApiResult result = null;
    	try {
    		InputStream in = request.getInputStream();
    		byte[] bs = new byte[1024];
    		int len = 0;
    		int size = 0;
    		byte[] allBuf = new byte[0];
    		while((len = in.read(bs)) != -1) {
    			if(len>0) {
    				size += len;
    				byte[] buf = new byte[size];
    				if(allBuf.length>0) {
    					System.arraycopy(allBuf, 0, buf, 0, allBuf.length);
    				}
    				System.arraycopy(bs, 0, buf, allBuf.length, len);
    				allBuf = buf;
    			}
    		}
    		String body = new String(allBuf,"utf-8");
    		result = JSON.parseObject(body,TaskResult.class);
    	}catch (Exception e) {
    		result = new ApiResult();
    	    result.setCode(ReturnCode.ERROR);
    	    result.setMsg(Constants.READ_RESULT_FROM_WEBHOOK_ERROR);
		}
    	
    	api.wakeUpFromWebHook(result);
    }
    
    
  void wakeUpFromWebHook(ApiResult res) {
	  if (this.completion == null) {
	    this.resultFromWebHook = res;
	    synchronized (this) {
	      notifyAll();
	    } 
	  } else {
	    try {
	      this.completion.complete(res);
	    } catch (Throwable t) {
	      res = new ApiResult();
	      res.setCode(ReturnCode.ERROR);
	      res.setMsg(Constants.COMPLETE_EXECUTE_ERROR);
	      this.completion.complete(res);
	    } 
	  } 
  }
    
    private HttpUrl.Builder fillCommonParams(Request.Builder reqBuilder) {
    	HttpUrl.Builder builder = (new HttpUrl.Builder())
      		  .scheme(ApiClient.getConfig().scheme)
      		  .host(ApiClient.getConfig().hostname)
      		  .port(ApiClient.getConfig().port);
        if (ApiClient.getConfig().prefix != null)
          builder.addPathSegments(ApiClient.getConfig().prefix);
        return builder;
	}
    
    private void fillApiPathParams(HttpUrl.Builder builder) {
        String path = this.info.getPath();
        List<String> varNames = getVarNamesFromUrl(path);
        if (!varNames.isEmpty()) {
          Map<String, Object> vars = new HashMap<>();
          for (String vname : varNames) {
            Object value = this.task.getParameterValue(vname);
            if (value == null)
              throw new ApiException(String.format("missing required field [%s]",vname)); 
            vars.put(vname, value);
          } 
          path = substituteUrl(path, vars);
        }
        signUri = path;
        builder.addPathSegments(path.replaceFirst("/", ""));
	}
    
    private void fillApiHeaderParams(Request.Builder reqBuilder) {
    	String accessToken = (String) task.getParameterValue("accessToken",false);
    	reqBuilder.addHeader(Constants.HEADER_ACCESS_TOKEN_AUTH, accessToken+ ":" 
    			+ createAccessTokenSign(task.getRestInfo().getHttpMethod(),signUri));
    }
    
    void fillPollingApiHeaderParams(Request.Builder reqBuilder) {
    	String accessToken = (String) task.getParameterValue("accessToken",false);
    	reqBuilder.addHeader(Constants.HEADER_ACCESS_TOKEN_AUTH, accessToken+ ":" + createAccessTokenSign("GET",signUri));
    }
    
    private String createAccessTokenSign(String method,String uri) {
    	String accessToken = (String) task.getParameterValue("accessToken",false);
    	String accessKeySecret = (String) task.getParameterValue("accessKeySecret"); 
    	if (accessToken != null && accessKeySecret != null) {
    		try {
    			String sign = Sha1Utils.getBase64Sha(accessKeySecret 
    					+ method.toUpperCase() 
    					+ uri);
    			return sign;
    		}catch (Exception e) {
				throw new ApiException(Constants.INTERNAL_ERROR,e);
			}
        } 
    	return null;
    }
    
    private void fillApiQueryParams(Request.Builder reqBuilder,HttpUrl.Builder urlBuilder) 
    		throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	
    	Map<String,Object> ps= task.getAllQueryParameterValues();
    	ps.forEach((name,val)->{
    		if(!name.equalsIgnoreCase("accessKeySecret")) {
    			if(val instanceof Collection) {
    				Collection<?> cos = (Collection<?>)val;
    				cos.forEach(v -> {
    					urlBuilder.addQueryParameter(name, v.toString()); 
    				});
    			}else {
    				urlBuilder.addQueryParameter(name, val.toString());  
    			}
    		}
    	});
    	
    	String accessKeyId = (String) task.getParameterValue("accessKeyId",false);
    	String accessKeySecret = (String) task.getParameterValue("accessKeySecret");
    	if (accessKeyId != null && accessKeySecret != null) {
    		String str = Sha1Utils.getBase64Sha(accessKeyId + accessKeySecret);
    		urlBuilder.addQueryParameter("signature",str);
    	}
    }
    
    private void fillApiBodyParams(Request.Builder reqBuilder) throws Exception {
      // form参数
    	
    	Map<String,Object> map = task.getAllFormParameterValues();
    	if(!map.isEmpty()) {
    		FormBody.Builder formBodyBuilder = new FormBody.Builder();
    		map.forEach((name,val)->{
    			if(val instanceof Collection) {
        			Collection<?> cos = (Collection<?>)val;
        			cos.forEach(v -> {
        				formBodyBuilder.add(name, v.toString()); 
        			});
        		}else {
        			formBodyBuilder.add(name, val.toString());  
        		}
    		});
    		reqBuilder.method(this.info.getHttpMethod(), 
    				formBodyBuilder.build());
    	}else {
    		map = task.getAllBodyParameterValues();
    		if(!map.isEmpty()) {
    			reqBuilder.method(this.info.getHttpMethod(), 
    					RequestBody.create(Constants.JSON, JSON.toJSONString(map)));
    		}
    	}
    	
    }
    
    private ApiResult pollResult(boolean wait, Response response) throws IOException {
 
    	String pollingUrl = response.header(Constants.HEADER_TASK_POLL_URL);
    	if (pollingUrl == null) {
    		throw new ApiException(String.format("api [%s] is an async API but the server doesn't return the polling url", 
    				this.task.getClass().getSimpleName() )); 
    	}
    	signUri = pollingUrl;
    	pollingUrl = pollingUrl.startsWith("/") ? 
    			pollingUrl.replaceFirst("/", ""):pollingUrl;
    	
    	HttpUrl.Builder builder = (new HttpUrl.Builder())
    			.scheme(ApiClient.getConfig().scheme)
    			.host(ApiClient.getConfig().hostname)
    			.port(ApiClient.getConfig().port);
    	String prefix = ApiClient.getConfig().prefix;
        if (prefix != null && !prefix.isEmpty()) {
        	builder.addPathSegments(prefix);
        }
        builder.addPathSegments(pollingUrl);
        pollingUrl = builder.build().toString();
        if (wait) {
        	return syncPollResult(pollingUrl); 
        }else if(this.completion!=null){
        	asyncPollResult(pollingUrl);
        }
        return null;
    }
    
    private void asyncPollResult(final String url) {
      final long timeout = getTimeout();
      final long timeInterval = getPollInterval();
      
      ApiPollCallback callback = new ApiPollCallback(this,url,timeInterval,timeout);
      pollThread.addPoll(callback);
    }
    
    private ApiResult syncPollResult(String url) {
      long current = System.currentTimeMillis();
      long timeout = getTimeout();
      long expiredTime = current + timeout;
      long interval = getPollInterval();
      
      while (current < expiredTime) {
    	 
    	logger.info("polling start for api {} taskId {} {}",
    			info.path,taskId,System.currentTimeMillis());
		Request.Builder builder = (new Request.Builder())
				.url(url);
		fillPollingApiHeaderParams(builder);
		Request req = builder.build();
		try (Response response = ApiClient.http.newCall(req).execute()) {
		  if (response.code() != 200) {
			  return httpError(response.code(), response.body().string()); 
		  }else {
			  AsyncTask asyncTask = (AsyncTask)task;
			  TaskResult res = asyncTask.translateResult(response.body().string());
			  if(!res.checkSuccess()) {
				  throw new ApiException(String.format(" %s code: [%s], msg [%s]", 
						  Constants.POLLING_RETURN_ERROR,res.getCode(),res.getMsg()));
			  }
			  if(res.checkCompleted()) {
				  return res;
			  }
		  }
		  TimeUnit.MILLISECONDS.sleep(interval);
		  current += interval;
		} catch (Exception e) {
			throw new ApiException(e);
		} 
      } 
      throw new ApiException(Constants.POLLING_TIMEOUT_ERROR); 
    }
    
    ApiResult writeApiResult(int code,String body) throws IOException {
      ApiResult res = new ApiResult();
      if (code == 200) {
        res = task.translateResult(body);
      } else {
        throw new ApiException(String.format("error status code: %s", Integer.valueOf(code)));
      } 
      return res;
    }
    
    ApiResult httpError(int code, String details) {
      ApiResult res = new ApiResult();
      res.setCode(ReturnCode.ERROR);
      res.setMsg(String.format("http response status code [%s] error, content [%s]", Integer.valueOf(code), details));
      return res;
    }
    
    private long getTimeout() {
      long timeout = this.info.getTimeout();
      return (timeout >= 0L) ? ApiClient.getConfig().defaultPollingTimeout : timeout;
    }
    
    private long getPollInterval() {
      long pollInterval = this.info.getPollInterval();
      return (pollInterval >= 0L) ? ApiClient.getConfig().defaultPollingInterval : pollInterval;
    }
    
    private String getWebCallback() {
        String webCallback = this.info.getWebCallback();
        if(webCallback == null) {
      	  webCallback = ApiClient.getConfig().webCallback;
        }
        if(webCallback != null) {
        	webCallback = webCallback.trim();
        }
        return webCallback;
    }
    
    private static class PollThread extends Thread {

        volatile boolean runFlag = true;
        protected long scanInterval = 1000L;

        private PriorityQueue<ApiPollCallback> queus = new PriorityQueue<>();

        private ExecutorService executor;

        PollThread() {
            super("poll-thread");
            int minsize = 4;
            int coreSize = Runtime.getRuntime().availableProcessors();
    		if (coreSize > minsize) {
    			coreSize--;
    		}else {
    			coreSize = minsize;
    		}
            executor = Executors.newFixedThreadPool(minsize);
        }
        
        public synchronized void addPoll(ApiPollCallback callback) {
        	if(!queus.contains(callback)) {
        		queus.add(callback);
        	}
        }

        @Override
        public void run() {
            while (runFlag) {
                try {
                    // 从队列中找出一个可以执行的任务，delay已经小于0的
                	ApiPollCallback poll = queus.poll();
                    if (poll != null && !poll.isCompleted()) {
                        long delay = poll.getDelay();
                        if (delay >= 0) {
                        	executor.submit(poll);
                            this.addPoll(poll);
                        }
                    }
                    Thread.sleep(scanInterval);

                } catch (InterruptedException e) {
                    Thread.interrupted();
                    if (!runFlag) {
                        break;
                    }
                } catch (Throwable t) {
                    //ignore
                }

            }
        }

        public void shutdown() {
            runFlag = false;
        }
    }
  }
