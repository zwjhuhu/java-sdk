package cn.com.nexwise.sdk.common.task;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.QueryTask;
import cn.com.nexwise.sdk.common.RestInfo;
import cn.com.nexwise.sdk.common.dto.ApiRequest;
import cn.com.nexwise.sdk.common.dto.ApiResult;
import cn.com.nexwise.sdk.common.dto.TaskResult;

/**
 * 查询任务的完成情况
 * @author GDJC
 *
 */
public class QueryTaskInfoTask extends QueryTask<TaskResult> {
	
	
  public QueryTaskInfoTask(ApiRequest req) {
		super(req);
  }
  
  protected RestInfo getRestInfo() {
    RestInfo info = new RestInfo();
    info.setHttpMethod("GET");
    info.setPath("/api-task/{id}");
    return info;
  }

	@Override
	protected ApiResult translateResult(String str) {
		return JSON.parseObject(str,TaskResult.class);
	}
}
