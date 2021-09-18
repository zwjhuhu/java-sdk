package cn.com.nexwise.sdk.vm.task;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.AbstractTaskTest;
import cn.com.nexwise.sdk.common.dto.PagingDataWrapper;
import cn.com.nexwise.sdk.vm.dto.QueryVmInfo;
import cn.com.nexwise.sdk.vm.dto.QueryVmReq;

public class QueryVmsTaskTest extends AbstractTaskTest {
	
	@Test
	public void test() {
		QueryVmReq req = new QueryVmReq();
		req.setAccessToken(accessToken);
		req.setAccessKeySecret(accessKeySecret);
		req.setLimit(1);
		QueryVmsTask task = new QueryVmsTask(req);
		PagingDataWrapper<QueryVmInfo> res = task.call();
		System.out.println(JSON.toJSONString(res));
	}

}
