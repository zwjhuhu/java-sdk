package cn.com.nexwise.sdk.common;

public class RestInfo {
	
	private String httpMethod;
	
	/**
	 * 路径不包含代理的前缀
	 */
	private String path;
	  
	private boolean async = true;
	
	/**
	 * 可以覆盖默认配置
	 */
	private long timeout;
	  
	  
	/**
	 * 可以覆盖默认配置
	 */
	private long pollInterval;
	
	/**
	 * 可以覆盖默认配置
	 */
	private String webCallback;

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public long getPollInterval() {
		return pollInterval;
	}

	public void setPollInterval(long pollInterval) {
		this.pollInterval = pollInterval;
	}

	public String getWebCallback() {
		return webCallback;
	}

	public void setWebCallback(String webCallback) {
		this.webCallback = webCallback;
	}
	
	
	
}
