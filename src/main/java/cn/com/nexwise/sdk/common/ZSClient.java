package cn.com.nexwise.sdk.common;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collection;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.com.nexwise.sdk.common.dto.ApiResult;
import okhttp3.OkHttpClient;

public class ZSClient {
  static OkHttpClient http = new OkHttpClient();
  
  public static final Gson gson;
  
  public static final Gson prettyGson;
  
  private static final DateTimeFormatter formatter;
  
  private static ConcurrentHashMap<String, Api> waittingApis = new ConcurrentHashMap<>();
  
  private static final long ACTION_DEFAULT_TIMEOUT = -1L;
  
  private static final long ACTION_DEFAULT_POLLINGINTERVAL = -1L;
  
  private static NWXConfig config;
  
  static {
    gson = (new GsonBuilder()).create();
    prettyGson = (new GsonBuilder()).setPrettyPrinting().create();
    formatter = (new DateTimeFormatterBuilder()).parseCaseInsensitive().appendPattern("EEE, dd MMM yyyy HH:mm:ss VV").toFormatter(Locale.ENGLISH);
  }
  
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
  
//  public static void webHookCallback(HttpServletRequest req, HttpServletResponse rsp) {
//    try {
//      StringBuilder jb = new StringBuilder();
//      BufferedReader reader = req.getReader();
//      String line;
//      while ((line = reader.readLine()) != null)
//        jb.append(line); 
//      String taskUuid = req.getHeader(Constants.HEADER_TASK_UUID);
//      if (taskUuid == null) {
//        rsp.sendError(400, "missing header " + Constants.HEADER_TASK_UUID);
//        return;
//      } 
//      
//      TaskResult res = new TaskResult();
//      if (!success) {
//        res = (ApiResult)gson.fromJson(jb.toString(), ApiResult.class);
//      } else {
//        res.setResultString(jb.toString());
//      } 
//      Api api = waittingApis.get(taskUuid);
//      if (api == null) {
//        rsp.sendError(404, String.format("no job[uuid:%s] found", new Object[] { taskUuid }));
//        return;
//      } 
//      api.wakeUpFromWebHook(res);
//      rsp.setStatus(200);
//      rsp.getWriter().write("");
//    } catch (Exception e) {
//      throw new ApiException(e);
//    } 
//  }
  
  static String join(Collection lst, String sep) {
    StringBuilder ret = new StringBuilder();
    boolean first = true;
    for (Object s : lst) {
      if (first) {
        ret = new StringBuilder(s.toString());
        first = false;
        continue;
      } 
      ret.append(sep).append(s.toString());
    } 
    return ret.toString();
  }
  
  private static void errorIfNotConfigured() {
    if (config == null)
      throw new RuntimeException("need config first"); 
  }
  
  public static void call(AbstractTask action, Completion completion) {
    errorIfNotConfigured();
    (new Api(action)).call(completion);
  }
  
  public static ApiResult call(AbstractTask action) {
    errorIfNotConfigured();
    return (new Api(action)).call();
  }
}

