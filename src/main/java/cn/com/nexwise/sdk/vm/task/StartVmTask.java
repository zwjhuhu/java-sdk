package cn.com.nexwise.sdk.vm.task;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.nexwise.sdk.common.AbstractTask;
import cn.com.nexwise.sdk.common.ApiException;
import cn.com.nexwise.sdk.common.Completion;
import cn.com.nexwise.sdk.common.InternalCompletion;
import cn.com.nexwise.sdk.common.NonAPIParam;
import cn.com.nexwise.sdk.common.Param;
import cn.com.nexwise.sdk.common.RestInfo;
import cn.com.nexwise.sdk.common.ZSClient;
import cn.com.nexwise.sdk.common.dto.ApiResult;
import cn.com.nexwise.sdk.common.dto.ErrorCode;
import cn.com.nexwise.sdk.vm.dto.StartVmInstanceResult;

public class StartVmTask extends AbstractTask {
  private static final HashMap<String, AbstractTask.Parameter> parameterMap = new HashMap<>();
  
  private static final HashMap<String, AbstractTask.Parameter> nonAPIParameterMap = new HashMap<>();
  
  @Param(required = true, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
  public String uuid;
  
  @Param(required = false, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
  public String clusterUuid;
  
  @Param(required = false, nonempty = false, nullElements = false, emptyString = true, noTrim = false)
  public String hostUuid;
  
  @Param(required = false)
  public List systemTags;
  
  @Param(required = false)
  public List userTags;
  
  @Param(required = false)
  public String sessionId;
  
  @Param(required = false)
  public String accessKeyId;
  
  @Param(required = false)
  public String accessKeySecret;
  
  @Param(required = false)
  public String requestIp;
  
  public static class Result {
    public ErrorCode error;
    
    public StartVmInstanceResult value;
    
    public Result throwExceptionIfError() {
      if (this.error != null)
        throw new ApiException(
            String.format("error[code: %s, description: %s, details: %s]", new Object[] { this.error.code, this.error.description, this.error.details })); 
      return this;
    }
  }
  
  @NonAPIParam
  public long timeout = -1L;
  
  @NonAPIParam
  public long pollingInterval = -1L;
  
  private Result makeResult(ApiResult res) {
    Result ret = new Result();
    if (res.error != null) {
      ret.error = res.error;
      return ret;
    } 
    StartVmInstanceResult value = res.<StartVmInstanceResult>getResult(StartVmInstanceResult.class);
    ret.value = (value == null) ? new StartVmInstanceResult() : value;
    return ret;
  }
  
  public Result call() {
    ApiResult res = ZSClient.call(this);
    return makeResult(res);
  }
  
  public void call(final Completion<Result> completion) {
    ZSClient.call(this, new InternalCompletion() {
          public void complete(ApiResult res) {
            completion.complete(StartVmTask.this.makeResult(res));
          }
        });
  }
  
  protected Map<String, AbstractTask.Parameter> getParameterMap() {
    return parameterMap;
  }
  
  protected Map<String, AbstractTask.Parameter> getNonAPIParameterMap() {
    return nonAPIParameterMap;
  }
  
  protected RestInfo getRestInfo() {
    RestInfo info = new RestInfo();
    info.httpMethod = "PUT";
    info.path = "/vm-instances/{uuid}/actions";
    info.needSession = true;
    info.needPoll = true;
    info.parameterName = "startVmInstance";
    return info;
  }
}
