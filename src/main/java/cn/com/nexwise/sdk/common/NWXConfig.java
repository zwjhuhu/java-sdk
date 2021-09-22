package cn.com.nexwise.sdk.common;

import java.util.concurrent.TimeUnit;

public class NWXConfig {	
	
	boolean showLog;
	
	String scheme = "http";

	/**
	 * 访问接口的主机名
	 */
  String hostname = "localhost";
  
  /**
   * 访问api的端口
   */
  int port = 38080;
  
  /**
   * 轮询等待结果时，最大等待时间
   */
  long defaultPollingTimeout = TimeUnit.HOURS.toMillis(2L);
  
  /**
   * 轮询等待结果每次的查询请求的间隔时间
   */
  long defaultPollingInterval = TimeUnit.SECONDS.toMillis(5L);
  
  /**
   * web远端的回调地址
   */
  String webCallback;
  
  /**
   * http client read 超时
   */
  Long readTimeout = 15 * 1000L;
  
  /**
   * http client write 超时
   */
  Long writeTimeout = 15 * 1000L;
  
  /**
   * 访问api的前缀
   */
  String prefix = "";
  
  
  
  	@Override
	public String toString() {
		return "NWXConfig [showLog=" + showLog + ", scheme=" + scheme + ", hostname=" + hostname + ", port=" + port
				+ ", defaultPollingTimeout=" + defaultPollingTimeout + ", defaultPollingInterval=" + defaultPollingInterval
				+ ", webCallback=" + webCallback + ", readTimeout=" + readTimeout + ", writeTimeout=" + writeTimeout
				+ ", prefix=" + prefix + "]";
	}

  	public String getHostname() {
    return this.hostname;
  }
  
  public int getPort() {
	return this.port;
  }
  
  public long getDefaultPollingTimeout() {
	return this.defaultPollingTimeout;
  }
  
  public long getDefaultPollingInterval() {
	return this.defaultPollingInterval;
  }
  
  public static class Builder {
  	NWXConfig config = new NWXConfig();
  	
    public Builder setShowLog(boolean showLog) {
        this.config.showLog = showLog;
        return this;
      }
  	
    public Builder setScheme(String scheme) {
        this.config.scheme = scheme;
        return this;
      }

    public Builder setHostname(String hostname) {
      this.config.hostname = hostname;
      return this;
    }
    
    public Builder setWebCallback(String webCallback) {
      this.config.webCallback = webCallback;
      return this;
    }
    
    public Builder setPort(int port) {
      this.config.port = port;
      return this;
    }
    
    public Builder setDefaultPollingTimeout(long value, TimeUnit unit) {
      this.config.defaultPollingTimeout = unit.toMillis(value);
      return this;
    }
    
    public Builder setDefaultPollingInterval(long value, TimeUnit unit) {
      this.config.defaultPollingInterval = unit.toMillis(value);
      return this;
    }
    
    public Builder setReadTimeout(long value, TimeUnit unit) {
      this.config.readTimeout = Long.valueOf(unit.toMillis(value));
      return this;
    }
    
    public Builder setWriteTimeout(long value, TimeUnit unit) {
      this.config.writeTimeout = Long.valueOf(unit.toMillis(value));
      return this;
    }
    
    public Builder setPrefix(String prefix) {
      this.config.prefix = prefix;
      return this;
    }
    
    public NWXConfig build() {
    	System.out.println("get global config: "+ this.config.toString());
    	return this.config;
    }
  }
}
