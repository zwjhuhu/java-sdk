package cn.com.nexwise.sdk.common;

import java.util.concurrent.TimeUnit;

import cn.com.nexwise.sdk.common.dto.ApiResult;
import okhttp3.OkHttpClient;

public class ApiClient {
  static OkHttpClient http = new OkHttpClient();
      
  private static NWXConfig config;
  
  public static NWXConfig getConfig() {
    return config;
  }
  
  public static void configure(NWXConfig c) {
    config = c;
    if (c.readTimeout != null || c.writeTimeout != null) {
      OkHttpClient.Builder b = new OkHttpClient.Builder();
      if (c.readTimeout != null)
        b.readTimeout(c.readTimeout.longValue(), TimeUnit.MILLISECONDS); 
      if (c.writeTimeout != null)
        b.writeTimeout(c.writeTimeout.longValue(), TimeUnit.MILLISECONDS); 
      http = b.build();
    } 
  }
  
  private static void errorIfNotConfigured() {
    if (config == null)
      throw new RuntimeException("need config first"); 
  }
  
  public static void call(AbstractTask task, Completion completion) {
    errorIfNotConfigured();
    (new Api(task)).call(completion);
  }
  
  public static ApiResult call(AbstractTask task) {
    errorIfNotConfigured();
    return (new Api(task)).call();
  }
}

