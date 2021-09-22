package cn.com.nexwise.sdk.common;

import org.junit.Before;

import cn.com.nexwise.sdk.common.dto.AccessTokenResult;
import cn.com.nexwise.sdk.common.dto.ApiRequest;
import cn.com.nexwise.sdk.common.task.AccessTokenTask;

public class BaseApiTest extends AbstractTaskTest {
	

	protected static String accessToken;
	
	protected static final String accessKeyId = "MzQwMjVlMWQ2Mzg3NGVi";
	
	@Before
	public void before() {
		if(accessToken==null) {
			accessToken = getAccessToken();
		}
	}
	
	private String getAccessToken() {
		ApiRequest req = new ApiRequest();
        req.setAccessKeyId(accessKeyId);
        req.setAccessKeySecret(accessKeySecret);
        
        AccessTokenResult res = new AccessTokenTask(req).query();
		if(res.checkSuccess()) {
			return res.getAccessToken();
		}
		return null;
	}
	
}
