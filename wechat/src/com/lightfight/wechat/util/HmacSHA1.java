package com.lightfight.wechat.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA1 {

	private static final String HMAC_SHA1 = "HmacSHA1";

	public static String encrypt(String data, String key) throws Exception{
		byte[] keyBytes = key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte b : rawHmac) {
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}

	private static String byteToHexString(byte ib) {
		char[] hexBaseChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = hexBaseChars[(ib >>> 4) & 0X0f];
		ob[1] = hexBaseChars[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
}
