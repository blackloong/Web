package com.fq.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassWordUtil {
/**
 *  16 位加密
 * @param str
 * @return
 * @throws Exception
 */
	public static String MD5(String str)throws Exception{
		
		MessageDigest mdt = MessageDigest.getInstance("MD5");
		mdt.update(str.getBytes());
		byte[] b = mdt.digest();
		int i ;
		StringBuffer sb = new StringBuffer() ;
		for (int offset = 0 ; offset < b.length ; offset++ )
		{
			i = b[offset];
			if(i<0)i+=256;
			if(i<16){
				sb.append("0");
			}
			sb.append(Integer.toHexString(i));
		}
		return sb.toString().substring(8,24);
	}
	/**
	 *  16 位加密
	 * @param str
	 * @return
	 * @throws Exception
	 */
		public static String MD5Pay(String str)throws Exception{
			
			MessageDigest mdt = MessageDigest.getInstance("MD5");
			mdt.update(str.getBytes());
			byte[] b = mdt.digest();
			int i ;
			StringBuffer sb = new StringBuffer() ;
			for (int offset = 0 ; offset < b.length ; offset++ )
			{
				i = b[offset];
				if(i<0)i+=256;
				if(i<16){
					sb.append("0");
				}
				sb.append(Integer.toHexString(i));
			}
			return sb.toString();
		}
		
		/**
		 * 32 位小写 
		 * @param sixteen
		 * @param str
		 * @return
		 */
		public static String toMD5String(boolean sixteen,String str) {
			MessageDigest messageDigest = null;
			try {
				messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.reset();
				messageDigest.update(str.getBytes("UTF-8"));
			} catch (NoSuchAlgorithmException e) {
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			if(sixteen)
			{
				return md5StrBuff.substring(8, 24).toString();
			}
			else
			{
				return md5StrBuff.toString();
			}
		}
	public static void main(String[] args)throws Exception {
		String pd = PassWordUtil.MD5("1");
		System.out.println(pd);
	}
}
