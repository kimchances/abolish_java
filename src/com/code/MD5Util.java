package com.code;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5 加密
 * @author Lucas
 * @date 2017年5月26日 上午10:19:21
 */
public class MD5Util {
	
	public static String encodeGtPassword(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if (str == null) {  
            return null;  
        }  
		return encode(str).substring(0,20);
	}
	
  
    
    public static String encode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if (str == null) {  
            return null;  
        }  
		return DigestUtils.md5Hex(str);
	}
 
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException{  
        String passwd =  "123qwe";  
        
        System.out.println(encodeGtPassword(passwd));    
    }  
}
