package cn.com.nexwise.sdk.common;

import cn.com.nexwise.sdk.common.dto.TaskResult;

public interface WebHookCompletion extends Completion {
	void complete(TaskResult result);
}
