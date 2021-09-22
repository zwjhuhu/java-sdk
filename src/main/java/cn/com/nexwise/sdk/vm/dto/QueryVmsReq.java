package cn.com.nexwise.sdk.vm.dto;

import cn.com.nexwise.sdk.common.Param;
import cn.com.nexwise.sdk.common.Parameter;
import cn.com.nexwise.sdk.common.dto.ApiRequest;

public class QueryVmsReq extends ApiRequest {
	
	@Param(paramType = Parameter.TYPE_QUERY,required = true,validValues = {"asc","desc"},
		description = "排序顺序")
	private String order="asc";
	
	@Param(paramType = Parameter.TYPE_QUERY,required = true,min = 0,
		description = "跳过记录数")
	private int offset=0;
	
	@Param(paramType = Parameter.TYPE_QUERY,required = true,min = 0,
		description = "获取最大记录数量")
	private int limit=20;
	
	@Param(paramType = Parameter.TYPE_QUERY,required = true)
	private int searchtype=0;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(int searchtype) {
		this.searchtype = searchtype;
	}
	
}
