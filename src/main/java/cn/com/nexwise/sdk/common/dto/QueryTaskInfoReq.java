package cn.com.nexwise.sdk.common.dto;

import cn.com.nexwise.sdk.common.Param;
import cn.com.nexwise.sdk.common.Parameter;

public class QueryTaskInfoReq extends ApiRequest {
	
	@Param(paramType = Parameter.TYPE_PATH,
			required = true,validRegexValue="[0-9a-f]{32}",notEmpty = true)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
