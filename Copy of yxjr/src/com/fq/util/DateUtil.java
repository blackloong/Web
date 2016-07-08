package com.fq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 日期工具类
 * @author P
 * @date d2015-4-24
 */
public class DateUtil {

	private final static  SimpleDateFormat  sdfDay = new SimpleDateFormat ("yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final static SimpleDateFormat sdfStr = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/**
	 * 获得当前日期 yyyy-MM-dd
	 * @return
	 */
	public static String getDay () {
		
		return sdfDay.format(new Date());
	}
	/**
	 * 获得当前时间
	 * @return
	 */
	public static String getTime () {
		
		return sdfTime.format(new Date());
	}
	/**
	 * 获得当前时间天数后的天数
	 * @param days
	 * @return
	 */
	public static String getDayBetween (int days){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, days);
		return sdfDay.format(c.getTime());
	} 
	/**
	 * 获得当前时间yyyyMMddHHmmss  一般用于订单号及文件上传名称生成
	 * @return
	 */
	public static String getTimeStr (){
		return sdfStr.format(new Date());
	}
	
	public static void main(String[] args) throws ParseException {
		 System.out.println(getDayBetween(-4));
		
		 SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		 Date date = sdf.parse( "2008-07-09 19:20:00" );
 		 System.out.println(date.getDate());
 		 Calendar c=Calendar.getInstance();
 		 System.out.println(c.getTime().getDate());
 		 
		 
	}
	/**
	 * unix时间戳转换指定格式
	 * @param timestampString
	 * @param formats
	 * @return
	 */
    public String TimeStamp2Date(String timestampString, String formats){    
        Long timestamp = Long.parseLong(timestampString)*1000;    
        String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));    
        return date;    
      }  
   /**
    * 获取前num天的时间
    * @param now
    * @param num
    * @return
    */
    public static  String getDate(String now , Integer num){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -num);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		return defaultStartDate;
	}
     
}
