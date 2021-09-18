package cn.com.nexwise.sdk.common.dto;

public class DataWrapper extends ApiResult {

	public DataWrapper() {
	}

	private String id;

	/**
	 * 首次登录的区域ID
	 */
	private String zoneId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
}

