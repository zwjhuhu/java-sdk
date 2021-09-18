package cn.com.nexwise.sdk.common.task;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.AbstractTaskTest;
import cn.com.nexwise.sdk.common.dto.QueryTaskInfoReq;
import cn.com.nexwise.sdk.common.dto.TaskResult;

public class QueryTaskInfoTaskTest extends AbstractTaskTest {
	
	@Test
	public void test() {
		QueryTaskInfoReq req = new QueryTaskInfoReq();
		req.setAccessToken(accessToken);
		req.setAccessKeySecret(accessKeySecret);
		req.setId("cb73f60bf0dd4b1bab3e56c58f998c77");
		QueryTaskInfoTask task = new QueryTaskInfoTask(req);
		TaskResult res = task.call();
		System.out.println(JSON.toJSONString(res));
	}

}
