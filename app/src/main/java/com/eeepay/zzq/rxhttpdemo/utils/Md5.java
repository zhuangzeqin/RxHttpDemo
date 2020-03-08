package com.eeepay.zzq.rxhttpdemo.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述：MD5算法基础类
 * 
 * @author 创建时间：2009-4-2
 */

public class Md5 {
	// private static Log log = new Log(Md5.class);//disable by xuqingfeng

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {

		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 计算MD5
	 * 
	 * @param origin
	 *            原始字符串
	 * @return md5字符串；null：出错
	 */
	public static String encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {
			// log.logPrint("计算MD5出错", ex);//disable by xuqingfeng
		}
		return resultString;
	}

	/**
	 * 验证MD5串
	 * 
	 * @param origin
	 *            原始字符串
	 * @param md5
	 *            md5字符串
	 * @return true：正确；false：不正确
	 */
	public static boolean verify(String origin, String md5) {
		String tmp = encode(origin);
		try {
			if (tmp.equals(md5)) {
				return true;
			}
		} catch (Exception e) {
		}

		return false;
	}

	/**
	 * 根据明文生成md5密文
	 * 
	 * @param str
	 *            要加密的明文
	 * @return md5密文
	 */
	public static String md5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}



	/**
	 * 27 md5加密产生，产生128位（bit）的mac 28 将128bit Mac转换成16进制代码 29
	 *
	 * @param strSrc
	 *            30
	 * @param key
	 *            31
	 * @return 32
	 */
	public static String MD5EncodeByKey(String strSrc, String key, String charset) {
		try {
			if(TextUtils.isEmpty(charset))
				charset="UTF-8";
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(strSrc.getBytes(charset));
			StringBuilder result = new StringBuilder(32);
			byte[] temp;
			temp = md5.digest(key.getBytes(charset));
			for (int i = 0; i < temp.length; i++) {
				result.append(Integer.toHexString(
						(0x000000ff & temp[i]) | 0xffffff00).substring(6));
			}
			return result.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}



}
