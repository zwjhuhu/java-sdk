package cn.com.nexwise.sdk.common.task;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.Completion;
import cn.com.nexwise.sdk.common.QueryTask;
import cn.com.nexwise.sdk.common.RestInfo;
import cn.com.nexwise.sdk.common.ZSClient;
import cn.com.nexwise.sdk.common.dto.ApiRequest;
import cn.com.nexwise.sdk.common.dto.ApiResult;
import cn.com.nexwise.sdk.common.dto.TaskResult;

/**
 * 查询任务的完成情况
 * @author GDJC
 *
 */
public class QueryTaskInfoTask extends QueryTask {
	
	
  public QueryTaskInfoTask(ApiRequest req) {
		super(req);
  }

  public TaskResult call() {
    return (TaskResult) ZSClient.call(this);
  }
  
  public void call(final Completion completion) {
    ZSClient.call(this, completion);
  }
  
  
  protected RestInfo getRestInfo() {
    RestInfo info = new RestInfo();
    info.setHttpMethod("GET");
    info.setPath("/api-task/{id}");
    info.setAsync(false);
    return info;
  }

	@Override
	protected ApiResult translateResult(String str) {
		return JSON.parseObject(str,TaskResult.class);
	}
}
