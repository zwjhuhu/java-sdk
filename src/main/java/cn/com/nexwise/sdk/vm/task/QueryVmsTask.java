package cn.com.nexwise.sdk.vm.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.com.nexwise.sdk.common.ListQueryTask;
import cn.com.nexwise.sdk.common.RestInfo;
import cn.com.nexwise.sdk.common.dto.PagingDataWrapper;
import cn.com.nexwise.sdk.vm.dto.QueryVmInfo;
import cn.com.nexwise.sdk.vm.dto.QueryVmsReq;

public class QueryVmsTask extends ListQueryTask<QueryVmInfo> {
  
  public QueryVmsTask(QueryVmsReq req) {
		super(req);
	}
  
  @Override
  protected RestInfo initRestInfo() {
    RestInfo info = new RestInfo();
    info.setHttpMethod("GET");
    info.setPath("/cloudHost/query.do");
    return info;
  }

	@Override
	protected PagingDataWrapper<QueryVmInfo> translateResult(String str) {
		return JSON.parseObject(str,
				new TypeReference<PagingDataWrapper<QueryVmInfo>>(){});
	}

}
