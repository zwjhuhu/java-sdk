package cn.com.nexwise.sdk.common.dto;

import cn.com.nexwise.sdk.common.Param;
import cn.com.nexwise.sdk.common.Parameter;

public class RefreshAccessTokenReq extends ApiRequest {
	
	@Param(paramType = Parameter.TYPE_QUERY,
			required = true,notEmpty = true)
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
