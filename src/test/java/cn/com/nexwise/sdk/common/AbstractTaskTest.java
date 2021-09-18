package cn.com.nexwise.sdk.common;

import org.junit.Before;


public class AbstractTaskTest {
	
	public static final String accessKeySecret = "TXpRd01qVmxNV1EyTXpnM05HVmlza3l3aW4wOTIw";
	
	public static final String accessToken = "1jhCcpGwrtcG4nPzHoP58p+yJQY=";
	
	@Before
	public void before() {
        ZSClient.configure(
                new NWXConfig.Builder()
                        .setHostname("192.168.20.31")
                        .setPort(38080)
                        .build()
        );
	}
}
