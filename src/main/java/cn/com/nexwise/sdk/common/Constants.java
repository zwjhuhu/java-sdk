package cn.com.nexwise.sdk.common;

import okhttp3.MediaType;

public interface Constants {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	  
	public static final String PARAMS_ERROR = "sdk.params.valid.error";
	
	public static final String HTTP_ERROR = "sdk.params.valid.error";
	  
	public static final String POLLING_TIMEOUT_ERROR = "sdk.polling.timeout";
	  
	public static final String INTERNAL_ERROR = "sdk.internal.error";
	  
    /**
     * 使用access token 请求接口的认证头部
     */
	public static final String HEADER_ACCESS_TOKEN_AUTH = "X-AUTH-TOKEN";
	    
    /**
     * 指定任务完成时，推送结果信息到远端的地址
     */
    public static final String HEADER_WEB_CALLBACK = "X-WEB-CALLBACK";
    
    /**
     * 指定任务的UUID，这个地方先使用操作日志的id
     */
    public static final String HEADER_TASK_UUID = "X-TASK-UUID";
    
    
    /**
     * 指定轮询获取任务状态的路径，这里用一个相对的值
     */
    public static final String HEADER_TASK_POLL_URL = "X-TASK-POLL-URL";

}
