package cn.com.nexwise.sdk.vm.task;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.BaseApiTest;
import cn.com.nexwise.sdk.common.dto.PagingDataWrapper;
import cn.com.nexwise.sdk.vm.dto.QueryVmInfo;
import cn.com.nexwise.sdk.vm.dto.QueryVmsReq;

public class QueryVmsTaskTest extends BaseApiTest {
	
	@Test
	public void test() {
		QueryVmsReq req = new QueryVmsReq();
		req.setAccessToken(accessToken);
		req.setAccessKeySecret(accessKeySecret);
		req.setLimit(1);
		QueryVmsTask task = new QueryVmsTask(req);
		PagingDataWrapper<QueryVmInfo> res = task.query();
		System.out.println(JSON.toJSONString(res));
	}

}
