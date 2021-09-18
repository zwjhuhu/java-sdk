package cn.com.nexwise.sdk.common.task;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.com.nexwise.sdk.common.NWXConfig;
import cn.com.nexwise.sdk.common.ZSClient;
import cn.com.nexwise.sdk.common.dto.AccessTokenResult;
import cn.com.nexwise.sdk.common.dto.ApiRequest;

public class AccessTokenTaskTest {
	
	@Test
	public void test() {
		String accessKeyId = "MzQwMjVlMWQ2Mzg3NGVi";
        String accesskeySecret = "TXpRd01qVmxNV1EyTXpnM05HVmlza3l3aW4wOTIw";
 
        ZSClient.configure(
                new NWXConfig.Builder()
                        .setHostname("192.168.20.31")
                        .setPort(38080)
                        .build()
        );
 
        
        ApiRequest req = new ApiRequest();
        req.setAccessKeyId(accessKeyId);
        req.setAccessKeySecret(accesskeySecret);
        
        AccessTokenResult ret = new AccessTokenTask(req).call();
        System.out.println(JSON.toJSONString(ret));
	}

}
