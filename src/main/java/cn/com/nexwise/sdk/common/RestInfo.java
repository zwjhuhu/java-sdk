package cn.com.nexwise.sdk.common;

public class RestInfo {
	
	protected String httpMethod;
	
	/**
	 * 路径不包含代理的前缀
	 */
	protected String path;
	  	
	/**
	 * 可以覆盖默认配置
	 */
	protected long timeout;
	  
	  
	/**
	 * 可以覆盖默认配置
	 */
	protected long pollInterval;
	
	/**
	 * 可以覆盖默认配置
	 */
	protected String webCallback;

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
