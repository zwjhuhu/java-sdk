package cn.com.nexwise.sdk.common;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public final class Sha1Utils {

    private Sha1Utils() {}

    /**
     * 得到base64编码后的sha值
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getBase64Sha(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        byte[] bs = getSha(str);
        return Base64.encodeBase64String(bs);
    }

    /**
     * 计算sha值
     * 
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static byte[] getSha(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha = null;
        sha = MessageDigest.getInstance("SHA");

        byte[] byteArray = str.getBytes(StandardCharsets.UTF_8);
        return sha.digest(byteArray);
    }
}
