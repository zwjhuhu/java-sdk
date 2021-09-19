package cn.com.nexwise.sdk.common;

import cn.com.nexwise.sdk.common.dto.ApiRequest;
import cn.com.nexwise.sdk.common.dto.PagingDataWrapper;

public abstract class ListQueryTask<T> extends QueryTask<PagingDataWrapper<T>> {

	public ListQueryTask(ApiRequest req) {
		super(req);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PagingDataWrapper<T> query() {
		return (PagingDataWrapper<T>) ApiClient.call(this);
	}
	  
}
