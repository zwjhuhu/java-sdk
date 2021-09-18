package cn.com.nexwise.sdk.common;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class CommonTest {
	

	@Test
	public void signTest() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String accessKeyId = "MzQwMjVlMWQ2Mzg3NGVi";
        String accesskeySecret = "TXpRd01qVmxNV1EyTXpnM05HVmlza3l3aW4wOTIw";
        
		System.out.println(Sha1Utils.getBase64Sha(accessKeyId + accesskeySecret));
	}
}
