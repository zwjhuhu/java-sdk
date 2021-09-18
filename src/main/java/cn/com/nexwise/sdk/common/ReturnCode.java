package cn.com.nexwise.sdk.common;

public final class ReturnCode {

	private ReturnCode() {

	}

	/**
	 * 成功
	 */
	public static final int SUCCESS = 1;

	/**
	 * 失败
	 */
	public static final int ERROR = -1;

	/**
	 * 无权操作
	 */
	public static final int NO_PERMISSION = -1001;

	/**
	 * 用户登录过期
	 */
	public static final int SESSION_TIME = -1002;
	
	/**
	 * 证书已到期
	 */
	public static final int LICENSE_EXPIRED = -1003;
	
	/**
	 * 证书无效
	 */
	public static final int LICENSE_INVALID = -1004;
}
