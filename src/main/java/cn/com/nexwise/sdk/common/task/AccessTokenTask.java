package cn.com.nexwise.sdk.common.task;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.ApiException;
import cn.com.nexwise.sdk.common.Parameter;
import cn.com.nexwise.sdk.common.QueryTask;
import cn.com.nexwise.sdk.common.RestInfo;
import cn.com.nexwise.sdk.common.dto.AccessTokenResult;
import cn.com.nexwise.sdk.common.dto.ApiRequest;
import cn.com.nexwise.sdk.common.dto.ApiResult;

public class AccessTokenTask extends QueryTask<AccessTokenResult>{
	
	
  public AccessTokenTask(ApiRequest req) {
		super(req);
  }
  
  @Override
  protected void commonParameterMerge(ApiRequest req) {
	Parameter p = new Parameter();
	String name = "accessKeyId";
	p.setName(name);
	if(req.getAccessKeyId()!=null && !req.getAccessKeyId().trim().isEmpty()) {
		p.setValue(req.getAccessKeyId());
		p.setType(Parameter.TYPE_QUERY);
		parameterMap.put(name,p);
	}else {
		throw new ApiException(String.format("missing required field [%s]", name));
	}
	
	p = new Parameter();
	name = "accessKeySecret";
	p.setName(name);
	if(req.getAccessKeySecret()!=null && !req.getAccessKeySecret().trim().isEmpty()) {
		p.setValue(req.getAccessKeySecret());
		p.setType(Parameter.TYPE_QUERY);
		parameterMap.put(name,p);
	}else {
		throw new ApiException(String.format("missing required field [%s]", name));
	}
  }
  
  protected RestInfo initRestInfo() {
    RestInfo info = new RestInfo();
    info.setHttpMethod("GET");
    info.setPath("/accessToken.do");
    return info;
  }

	@Override
	protected ApiResult translateResult(String str) {
		return JSON.parseObject(str,AccessTokenResult.class);
	}

}
