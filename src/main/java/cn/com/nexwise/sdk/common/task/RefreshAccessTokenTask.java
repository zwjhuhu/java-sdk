package cn.com.nexwise.sdk.common.task;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.QueryTask;
import cn.com.nexwise.sdk.common.RestInfo;
import cn.com.nexwise.sdk.common.dto.AccessTokenResult;
import cn.com.nexwise.sdk.common.dto.ApiResult;
import cn.com.nexwise.sdk.common.dto.RefreshAccessTokenReq;

public class RefreshAccessTokenTask extends QueryTask<AccessTokenResult>{
	
	
  public RefreshAccessTokenTask(RefreshAccessTokenReq req) {
		super(req);
  }
  
  protected RestInfo initRestInfo() {
    RestInfo info = new RestInfo();
    info.setHttpMethod("GET");
    info.setPath("/refreshAccessToken.do");
    return info;
  }

	@Override
	protected ApiResult translateResult(String str) {
		return JSON.parseObject(str,AccessTokenResult.class);
	}

}
