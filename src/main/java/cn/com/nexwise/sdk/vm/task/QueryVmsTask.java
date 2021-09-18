package cn.com.nexwise.sdk.vm.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.com.nexwise.sdk.common.Completion;
import cn.com.nexwise.sdk.common.ListQueryTask;
import cn.com.nexwise.sdk.common.RestInfo;
import cn.com.nexwise.sdk.common.ZSClient;
import cn.com.nexwise.sdk.common.dto.PagingDataWrapper;
import cn.com.nexwise.sdk.vm.dto.QueryVmInfo;
import cn.com.nexwise.sdk.vm.dto.QueryVmReq;

public class QueryVmsTask extends ListQueryTask<QueryVmInfo> {
  
  public QueryVmsTask(QueryVmReq req) {
		super(req);
	}

@SuppressWarnings("unchecked")
  public PagingDataWrapper<QueryVmInfo> call() {
    return (PagingDataWrapper<QueryVmInfo>) ZSClient.call(this);
  }
  
  public void call(final Completion completion) {
    ZSClient.call(this, completion);
  }
  
  protected RestInfo getRestInfo() {
    RestInfo info = new RestInfo();
    info.setHttpMethod("GET");
    info.setPath("/cloudHost/query.do");
    info.setAsync(false);
    return info;
  }

	@Override
	protected PagingDataWrapper<QueryVmInfo> translateResult(String str) {
		return JSON.parseObject(str,
				new TypeReference<PagingDataWrapper<QueryVmInfo>>(){});
	}

}
