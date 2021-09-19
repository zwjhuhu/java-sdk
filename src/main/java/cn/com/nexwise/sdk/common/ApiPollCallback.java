package cn.com.nexwise.sdk.common;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import cn.com.nexwise.sdk.common.dto.ApiResult;
import cn.com.nexwise.sdk.common.dto.TaskResult;
import okhttp3.Request;
import okhttp3.Response;

public class ApiPollCallback implements Callable<ApiResult>,Delayed {
	
	private final SimpleLogger logger = new SimpleLogger(this.getClass());
	
    protected long timeInterval = 5000L;
    
    protected long timeout = 3 * 60 * 1000L;
    
    protected long startTime = -1L;
    
    private Api api;
    
    private String url;
    
    private long count = 0;
    
    private boolean runFlag = false;
    
    private boolean completed = false; 
    
    private ApiResult done(ApiResult res) {
    	if(!this.isCompleted()) {
    		this.setCompleted(true);
    		logger.info("poll callback completed {}",System.currentTimeMillis());
    	}else {
    		return null;
    	}
    	try {
  	      api.completion.complete(res);
  	    } catch (Throwable t) {
  	      res = new ApiResult();
  	      res.setCode(ReturnCode.ERROR);
  	      res.setMsg(Constants.COMPLETE_EXECUTE_ERROR);
  	      api.completion.complete(res);
  	    } 
        return res;
    }
    
    private synchronized boolean checkToRun() {
    	if(runFlag || completed) {
    		return false;
    	}
    	long cur = System.currentTimeMillis();
    	long nexttime = startTime + (this.count * this.timeInterval);
    	boolean flag = cur >= nexttime;
    	if(flag) {
    		runFlag = true;
    		this.count++;
    	}
    	return flag;
    }

    
    
	@Override
	public ApiResult call() throws Exception {
		if(!checkToRun()) {
			return null;
		}
		logger.info("poll callback run {}",System.currentTimeMillis());
		try {
			return doCall();
		}catch (Throwable e) {
			ApiResult res = new ApiResult();
			res.setCode(ReturnCode.ERROR);
			res.setMsg(e.getMessage());
			return done(res);
		}finally {
			synchronized (this) {
				runFlag = false;
			}
		}
	}
	
	private ApiResult doCall() {

		// 检查是否超时
        if (getDelay() < 0L) {
        	ApiResult res = new ApiResult();
          	res.setCode(ReturnCode.ERROR);
          	res.setMsg(Constants.POLLING_TIMEOUT_ERROR);
          	return done(res);
        } 
        
		Request.Builder builder = (new Request.Builder())
				.url(url);
		api.fillPollingApiHeaderParams(builder);
		Request req = builder.build();
      try (Response response = ApiClient.http.newCall(req).execute()) {
        if (response.code() != 200) {
          return done(api.httpError(response.code(), response.body().string()));
        } else {
        	TaskResult ret = (TaskResult) api.task.translateResult(response.body().string());
        	
        	if(!ret.checkSuccess()) {
			  throw new ApiException(String.format(" %s code: [%s], msg [%s]", 
					  Constants.POLLING_RETURN_ERROR,ret.getCode(),ret.getMsg()));
        	}
			  
        	if(ret.checkCompleted()) {
        		return done(ret);
        	}
        }
        
      } catch (Throwable e) {
    	  ApiResult res = new ApiResult();
          res.setCode(ReturnCode.ERROR);
          res.setMsg(e.getMessage());
          return done(res);
      } 
      return null; 
	}

    public ApiPollCallback(Api api, String url, long timeInterval, long timeout) {
		this.api = api;
		this.url = url;
		this.timeInterval = timeInterval;
		this.timeout = timeout;
		this.startTime = System.currentTimeMillis();
	}

	@Override
    public int compareTo(Delayed o) {
       if(getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
           return -1;
       }else if(getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
           return 1;
       }
       return 0;
    }

    @Override
    public synchronized long getDelay(TimeUnit unit) {
        return startTime + timeout - System.currentTimeMillis();
    }
    
    public long getDelay() {
        return getDelay(TimeUnit.MILLISECONDS);
    }
    
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }



	public synchronized boolean isCompleted() {
		return completed;
	}

	public synchronized void setCompleted(boolean completed) {
		this.completed = completed;
	}
    
}
