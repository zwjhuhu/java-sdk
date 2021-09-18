package cn.com.nexwise.sdk.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.dto.ApiResult;
import cn.com.nexwise.sdk.common.dto.TaskResult;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Api {
	
    AbstractTask action;
    
    RestInfo info;
    
    Completion completion;
    
    String jobUuid = UUID.randomUUID().toString().replaceAll("-", "");
    
    private TaskResult resultFromWebHook;
    
    private String signUri;
    
    Api(AbstractTask action) {
      this.action = action;
      this.info = action.getRestInfo();
    }
    
//    void wakeUpFromWebHook(TaskResult res) {
//      if (this.completion == null) {
//        this.resultFromWebHook = res;
//        synchronized (this) {
//          notifyAll();
//        } 
//      } else {
//    	  TaskResultWrapper wrapper = new TaskResultWrapper();
//        try {
//          this.completion.complete(wrapper);
//        } catch (Throwable t) {
//          res = new TaskResultWrapper();
//          this.completion.complete(wrapper);
//        } 
//      } 
//    }
    
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
    
    void call(Completion completion) {
      this.completion = completion;
      doCall();
    }
    
    ApiResult doCall() {
      this.action.checkParameters();
      Request.Builder reqBuilder = new Request.Builder();
//      if (this.action.apiTimeout != null)
//        reqBuilder.addHeader("X-API-Timeout", this.action.apiTimeout.toString()); 
      String webCallback = action.getRestInfo().getWebCallback();
      if(webCallback==null) {
    	  webCallback = ZSClient.getConfig().webCallback;
      }
      if (webCallback != null) {
    	  reqBuilder.addHeader(Constants.HEADER_WEB_CALLBACK, ZSClient.getConfig().webCallback); 
      }
      try {
        if (this.action instanceof QueryTask) {
          info.setAsync(false);
        } 
        
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
        try (Response response = ZSClient.http.newCall(request).execute()) {
          if (!response.isSuccessful())
        	  apiRet = httpError(response.code(), response.body().string()); 
          if (response.code() == 200) {
        	  apiRet = writeApiResult(response); 
          } else {
        	  throw new ApiException(Constants.HTTP_ERROR);
          }
        } 
        if(!info.isAsync() && this.completion!=null) {
        	this.completion.complete(apiRet);
        }
        return apiRet;
      } catch (IOException e) {
        throw new ApiException(e);
      } 
    }


//	private ApiResult syncWebHookResult() {
//      synchronized (this) {
//        long timeout = getTimeout();
//        try {
//          wait(timeout);
//        } catch (InterruptedException e) {
//          throw new ApiException(e);
//        } 
//        if (this.resultFromWebHook == null) {
//          this.resultFromWebHook = new ApiResult();
//          this.resultFromWebHook.error = errorCode("sdk.1001", "timeout of polling webhook result", 
//              
//              String.format("polling result of api[%s] timeout after %s ms", new Object[] { this.action.getClass().getSimpleName(), Long.valueOf(timeout) }));
//        } 
//        ZSClient.waittingApis.remove(this.jobUuid);
//        return this.resultFromWebHook;
//      } 
//    }
    
//    private ApiResult webHookResult() {
//      if (this.completion == null)
//        return syncWebHookResult(); 
//      return null;
//    }
    
    
    
    private HttpUrl.Builder fillCommonParams(Request.Builder reqBuilder) {
    	HttpUrl.Builder builder = (new HttpUrl.Builder())
      		  .scheme("http")
      		  .host(ZSClient.getConfig().hostname)
      		  .port(ZSClient.getConfig().port);
        if (ZSClient.getConfig().prefix != null)
          builder.addPathSegments(ZSClient.getConfig().prefix);
        return builder;
	}
    
    private void fillApiPathParams(HttpUrl.Builder builder) {
        String path = this.info.getPath();
        List<String> varNames = getVarNamesFromUrl(path);
        if (!varNames.isEmpty()) {
          Map<String, Object> vars = new HashMap<>();
          for (String vname : varNames) {
            Object value = this.action.getParameterValue(vname);
            if (value == null)
              throw new ApiException(String.format("missing required field [%s]",vname)); 
            vars.put(vname, value);
          } 
          path = substituteUrl(path, vars);
        }
        signUri = path;
        builder.addPathSegments(path.replaceFirst("/", ""));
	}
    
    private void fillApiHeaderParams(Request.Builder reqBuilder) 
    		throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	String accessToken = (String) action.getParameterValue("accessToken",false);
    	String accessKeySecret = (String) action.getParameterValue("accessKeySecret"); 
    	if (accessToken != null && accessKeySecret != null) {
    		String sign = Sha1Utils.getBase64Sha(accessKeySecret 
    				+ action.getRestInfo().getHttpMethod().toUpperCase() 
    				+ signUri);
    		reqBuilder.addHeader(Constants.HEADER_ACCESS_TOKEN_AUTH, accessToken+ ":" + sign);
        } 
    }
    
    private void fillApiQueryParams(Request.Builder reqBuilder,HttpUrl.Builder urlBuilder) 
    		throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	
    	Map<String,Object> ps= action.getAllQueryParameterValues();
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
    	
    	String accessKeyId = (String) action.getParameterValue("accessKeyId",false);
    	String accessKeySecret = (String) action.getParameterValue("accessKeySecret");
    	if (accessKeyId != null && accessKeySecret != null) {
    		String str = Sha1Utils.getBase64Sha(accessKeyId + accessKeySecret);
    		urlBuilder.addQueryParameter("signature",str);
    	}
    }
    
    private void fillApiBodyParams(Request.Builder reqBuilder) throws Exception {
      // form参数
    	
    	Map<String,Object> map = action.getAllFormParameterValues();
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
    		map = action.getAllBodyParameterValues();
    		if(!map.isEmpty()) {
    			reqBuilder.method(this.info.getHttpMethod(), 
    					RequestBody.create(Constants.JSON, JSON.toJSONString(map)));
    		}
    	}
    	
    }
    
//    private ApiResult pollResult(Response response) throws IOException {
//      if (!this.info.isNeedPoll())
//        throw new ApiException(String.format("[Internal Error] the api[%s] is not an async API but the server returns 201 status code", new Object[] { this.action
//                .getClass().getSimpleName() })); 
//      Map body = (Map)ZSClient.gson.fromJson(response.body().string(), LinkedHashMap.class);
//      String pollingUrl = (String)body.get("location");
//      if (pollingUrl == null)
//        throw new ApiException(String.format("Internal Error] the api[%s] is an async API but the server doesn't return the polling location url", new Object[] { this.action
//                .getClass().getSimpleName() })); 
////      String configHost = String.format("%s:%s", new Object[] { ZSClient.access$000().getHostname(), Integer.valueOf(ZSClient.access$000().getPort()) });
//      String configHost = null;
//      if (!pollingUrl.contains(configHost)) {
//        String splitRegex = "/zstack/v1/api-jobs";
//        pollingUrl = String.format("http://%s%s%s", new Object[] { configHost, splitRegex, pollingUrl.split(splitRegex)[1] });
//      } 
//      if (this.completion == null)
//        return syncPollResult(pollingUrl); 
//      asyncPollResult(pollingUrl);
//      return null;
//    }
    
//    private void asyncPollResult(final String url) {
//      final long current = System.currentTimeMillis();
//      final long timeout = getTimeout();
//      final long expiredTime = current + timeout;
//      final long i = getInterval();
//      final Object sessionId = this.action.getParameterValue("sessionId");
//      final Object requestIp = this.action.getParameterValue("requestIp");
//      final Timer timer = new Timer();
//      timer.schedule(new TimerTask() {
//            long count = current;
//            
//            long interval = i;
//            
//            private void done(ApiResult res) {
//              ZSClient.Api.this.completion.complete(res);
//              timer.cancel();
//            }
//            
//            public void run() {
//              Request.Builder builder = (new Request.Builder()).url(url).addHeader("Authorization", String.format("%s %s", new Object[] { "OAuth",null })).addHeader("X-JSON-Schema", Boolean.TRUE.toString()).get();
//              if (requestIp != null)
//                builder.addHeader("X-Request-Ip", String.valueOf(requestIp)); 
//              Request req = builder.build();
//              try (Response response = ZSClient.http.newCall(req).execute()) {
//                if (response.code() != 200 && response.code() != 503 && response.code() != 202) {
//                  done(ZSClient.Api.this.httpError(response.code(), response.body().string()));
//                  return;
//                } 
//                if (response.code() == 200 || response.code() == 503) {
//                  done(ZSClient.Api.this.writeApiResult(response));
//                  return;
//                } 
//                this.count += this.interval;
//                if (this.count >= expiredTime) {
//                  ApiResult res = new ApiResult();
//                  res.error = ZSClient.Api.this.errorCode("sdk.1001", "timeout of async polling API result", 
//                      
//                      String.format("polling result of api[%s] timeout after %s ms", new Object[] { this.getClass().getSimpleName(), Long.valueOf(0L) }));
//                  done(res);
//                } 
//              } catch (Throwable e) {
//                ApiResult res = new ApiResult();
//                res.error = ZSClient.Api.this.errorCode("sdk.1002", "an internal error happened", e
//                    
//                    .getMessage());
//                done(res);
//              } 
//            }
//          },0L, i);
//    }
    
//    private ApiResult syncPollResult(String url) {
//      long current = System.currentTimeMillis();
//      long timeout = getTimeout();
//      long expiredTime = current + timeout;
//      long interval = getPollInterval();
//      Object sessionId = this.action.getParameterValue("sessionId");
//      Object requestIp = this.action.getParameterValue("requestIp");
//      while (current < expiredTime) {
//        Request.Builder builder = (new Request.Builder()).url(url).addHeader("Authorization", String.format("%s %s", new Object[] { "OAuth", sessionId })).addHeader("X-JSON-Schema", Boolean.TRUE.toString()).get();
//        if (requestIp != null)
//          builder.addHeader("X-Request-Ip", String.valueOf(requestIp)); 
//        Request req = builder.build();
//        try (Response response = ZSClient.http.newCall(req).execute()) {
//          if (response.code() != 200 && response.code() != 503 && response.code() != 202)
//            return httpError(response.code(), response.body().string()); 
//          if (response.code() == 200 || response.code() == 503)
//            return writeApiResult(response); 
//          TimeUnit.MILLISECONDS.sleep(interval);
//          current += interval;
//        } catch (InterruptedException interruptedException) {
//        
//        } catch (IOException e) {
//          throw new ApiException(e);
//        } 
//      } 
//      ApiResult res = new ApiResult();
//      res.error = errorCode("sdk.1001", "timeout of sync polling API result", 
//          
//          String.format("polling result of api[%s] timeout after %s ms", new Object[] { this.action.getClass().getSimpleName(), Long.valueOf(timeout) }));
//      return res;
//    }
    
    private ApiResult writeApiResult(Response response) throws IOException {
      ApiResult res = new ApiResult();
      if (response.code() == 200) {
        res = action.translateResult(response.body().string());
      } else {
        throw new ApiException(String.format("error status code: %s", Integer.valueOf(response.code())));
      } 
      return res;
    }
    
    private ApiResult httpError(int code, String details) {
      ApiResult res = new ApiResult();
      res.setCode(ReturnCode.ERROR);
      res.setMsg(String.format("http response status code [%s] error, content [%s]", Integer.valueOf(code), details));
      return res;
    }
    
    ApiResult call() {
      return doCall();
    }
    
    private long getTimeout() {
      long timeout = this.info.getTimeout();
      return (timeout >= 0L) ? ZSClient.getConfig().defaultPollingTimeout : timeout;
    }
    
    private long getPollInterval() {
      long pollInterval = this.info.getPollInterval();
      return (pollInterval >= 0L) ? ZSClient.getConfig().defaultPollingInterval : pollInterval;
    }
  }
