package cn.com.nexwise.sdk.common.dto;

import cn.com.nexwise.sdk.common.ReturnCode;

public class ApiResult {
	
	/**
	 * 状态码:1-成功,-1-失败,-1001-无权操作,-1002-用户登录过期
	 */
	protected int code = ReturnCode.ERROR;

	/**
	 * 前端显示信息
	 */
	protected String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean checkSuccess() {
		return code == ReturnCode.SUCCESS;
	}

}
