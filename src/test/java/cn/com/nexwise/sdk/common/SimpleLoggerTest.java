package cn.com.nexwise.sdk.common;

import org.junit.Test;

public class SimpleLoggerTest {
	

	@Test
	public void test() {
		
        ApiClient.configure(
                new NWXConfig.Builder()
                	.setShowLog(true)
                    .build()
        );
        
		SimpleLogger logger = new SimpleLogger(this.getClass());
		logger.debug("test");
		
		logger.info("test {} {}","aa",System.currentTimeMillis());
		
		logger.warn("test",new RuntimeException("test"));
		
		logger.error("test",new RuntimeException("error"));
	}
}
