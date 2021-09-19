package cn.com.nexwise.sdk.common;

import cn.com.nexwise.sdk.common.dto.ApiRequest;

public abstract class QueryTask<T> extends AbstractTask {

	protected QueryTask(ApiRequest req) {
		super(req);
	}
	
	@SuppressWarnings("unchecked")
	public T query() {
		return (T) ApiClient.call(this);
	}
	  
}
