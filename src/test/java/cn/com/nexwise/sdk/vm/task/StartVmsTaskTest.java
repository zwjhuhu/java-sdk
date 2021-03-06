package cn.com.nexwise.sdk.vm.task;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.BaseApiTest;
import cn.com.nexwise.sdk.common.dto.TaskResult;
import cn.com.nexwise.sdk.vm.dto.StartVmsReq;

public class StartVmsTaskTest extends BaseApiTest {
	
	private StartVmsTask createTask() {
		Set<String> ids = new HashSet<>();
		ids.add("d46d470e5aad4a3085b745c946ca1afc");
		StartVmsReq req = new StartVmsReq(ids);
		req.setAccessToken(accessToken);
		req.setAccessKeySecret(accessKeySecret);
		
		return new StartVmsTask(req);
	}
	
	@Test
	public void testSync() {
		TaskResult res = createTask().processSync();
		System.out.println(JSON.toJSONString(res));
	}
	
	@Test
	public void testAsync() throws InterruptedException {
		
		createTask().processAsync((res) ->{
			System.out.println(JSON.toJSONString(res));
		});
		Thread.sleep(30*1000L);
	}

}
