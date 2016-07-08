package com.fq.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * String 工具类
 * @author P
 * @date  2015-4-24
 */
public class StringUtils {

	/**
	 * 检查Object 是否未空
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj ){
		return obj == null || obj.toString().trim().length()<=0 ;
	}
	public static boolean timeOut(String startTime,int milliSecond) throws Exception{
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(startTime);
		long time1=now.getTime();
		long time2=date.getTime();
		long cha=Math.abs(time1-time2);
		if(cha>milliSecond){
		   return true;
		}else{
		  return false;
		}
	}
	/**
	 * 根据时间获取no
	 * @return
	 */
	public static String getNo() {
		String str = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random r = new Random();
		str = str+""+r.nextInt(10000);
		return str;
	}
	public static boolean NotEmpty(String str){
		boolean flag=false;
		if(str!=null && !"".equals(str)) flag=true;
		return flag;
	}
}
