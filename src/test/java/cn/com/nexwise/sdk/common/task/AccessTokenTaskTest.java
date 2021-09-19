package cn.com.nexwise.sdk.common.task;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.AbstractTaskTest;
import cn.com.nexwise.sdk.common.dto.AccessTokenResult;
import cn.com.nexwise.sdk.common.dto.ApiRequest;

public class AccessTokenTaskTest extends AbstractTaskTest {
	
	@Test
	public void test() {
		String accessKeyId = "MzQwMjVlMWQ2Mzg3NGVi";
        
        ApiRequest req = new ApiRequest();
        req.setAccessKeyId(accessKeyId);
        req.setAccessKeySecret(accessKeySecret);
        
        AccessTokenResult res = new AccessTokenTask(req).query();
        System.out.println(JSON.toJSONString(res));
	}

}
