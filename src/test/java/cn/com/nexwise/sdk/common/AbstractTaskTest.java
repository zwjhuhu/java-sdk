package cn.com.nexwise.sdk.common;

import org.junit.BeforeClass;


public class AbstractTaskTest {
	
	public static final String accessKeySecret = "TXpRd01qVmxNV1EyTXpnM05HVmlza3l3aW4wOTIw";
	
	@BeforeClass
	public static void beforeClass() {
        ApiClient.configure(
                new NWXConfig.Builder()
                		.setShowLog(true)
                        .setHostname("www.zwjhuhu.top")
                        .setPort(8091)
                        .build()
        );
	}
}
