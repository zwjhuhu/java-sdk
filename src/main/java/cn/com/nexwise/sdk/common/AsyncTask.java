package cn.com.nexwise.sdk.common;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.dto.ApiRequest;
import cn.com.nexwise.sdk.common.dto.TaskResult;

public abstract class AsyncTask extends AbstractTask {

	public AsyncTask(ApiRequest req) {
		super(req);
	}

	@Override
	protected TaskResult translateResult(String str) {
		return JSON.parseObject(str,TaskResult.class);
	}

	/**
	 * 同步调用，会一直等到执行完成返回或者超时
	 * @return
	 */
	public TaskResult processSync() {
		return (TaskResult) ApiClient.call(this);
	}
		  
	/**
	 * 异步调用，请求成功之后立即返回，结果通过completion 处理<br/>
	 * 如果设置了webcallbak的推送地址，则需要调用方在收到web推送后自行调用<br/>
	 * {@link Api#onWebHookResult} 方法
	 * @param completion
	 */
	public void processAsync(final Completion completion) {
		ApiClient.call(this, completion);
	}
	
	public long getTimeout() {
		return restInfo.getTimeout();
	}

	public void setTimeout(long timeout) {
		this.restInfo.timeout = timeout;
	}

	public long getPollInterval() {
		return restInfo.pollInterval;
	}

	public void setPollInterval(long pollInterval) {
		this.restInfo.pollInterval = pollInterval;
	}

	public String getWebCallback() {
		return restInfo.webCallback;
	}

	public void setWebCallback(String webCallback) {
		this.restInfo.webCallback = webCallback;
	}

}
