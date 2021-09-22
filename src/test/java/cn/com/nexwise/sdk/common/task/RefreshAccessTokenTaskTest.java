package cn.com.nexwise.sdk.common.task;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.BaseApiTest;
import cn.com.nexwise.sdk.common.dto.AccessTokenResult;
import cn.com.nexwise.sdk.common.dto.RefreshAccessTokenReq;

public class RefreshAccessTokenTaskTest extends BaseApiTest {
	
	@Test
	public void test() {
		RefreshAccessTokenReq req = new RefreshAccessTokenReq();
		req.setAccessToken(accessToken);
		req.setAccessKeySecret(accessKeySecret);
		req.setToken(accessToken);
		RefreshAccessTokenTask task = new RefreshAccessTokenTask(req);
		AccessTokenResult res = task.query();
		System.out.println(JSON.toJSONString(res));
	}

}
