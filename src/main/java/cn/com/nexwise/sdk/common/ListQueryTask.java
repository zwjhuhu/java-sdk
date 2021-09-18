package cn.com.nexwise.sdk.common;

import cn.com.nexwise.sdk.common.dto.ApiRequest;

public abstract class ListQueryTask<T> extends QueryTask {

	public ListQueryTask(ApiRequest req) {
		super(req);
	}
	  
}
