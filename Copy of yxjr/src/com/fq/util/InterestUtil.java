package com.fq.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 利息计算
 * @author Administrator
 *
 */
public class InterestUtil {

	
	
	
	
	public static Map<String, Object> getInterestNum(String q, String y,
			String x, String f){
		List<Map<String, Object>> list=InterestUtil.getInterest(q, y, x, f,new Date());
		double a=0, b=0;
		/*map.put("myhkje", je);
		map.put("myhkbj", bj);
		map.put("myhklx", lx);
		map.put("syhkje", sy);*/
		if(list!=null&&list.size()>=1){
			Map<String, Object> map=list.get(0);
			a= getdoubleValue(map.get("myhkje")+"",2) +getdoubleValue(map.get("syhkje")+"",2);
			b=  getdoubleValue(""+(a-Double.parseDouble(q)),2); 
		}
		
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("lx", b);
		m.put("ze", a);
		return m;
	}
	
	
	
	
	
	
	/**
	 *转让计算头月利息 无需考虑还款类型
	 * @return
	 */
	public static Map<String, Object> getInterestDay(String q, String y,String x){
		double qq = Double.parseDouble(q);// 金额
		int yy = Integer.parseInt(y);// 期限
		double xx = Double.parseDouble(x);// 利息
		Map<String, Object> map = new HashMap<String, Object>();
		double je = 0, lx = 0;
		
		je = getdoubleValue((qq * xx / 100 / 12/30 * yy + qq)+"",2);
		lx = getdoubleValue((qq * xx / 100 / 12/30 * yy)+"",2);
			map.put("je", je);
			map.put("lx", lx);
		return map;
		
	}
	
	
	
	
	
	
	/**
	 *  q 金额
	 *  y 月份
	 *  x 利率
	 *  f 类型  1等额本息 2等本等息 3先息后本 4按月付息到期还本
	 */
	public static List<Map<String, Object>> getInterest(String q, String y,
			String x, String f,Date date) {
		double qq = Double.parseDouble(q);// 金额
		int yy = Integer.parseInt(y);// 期限
		double xx = Double.parseDouble(x);// 利息
		int ff = Integer.parseInt(f);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		double je = 0, bj = 0, lx = 0, sy = 0, zje = 0, zlx = 0;
		if (ff == 1) {// 等额本息
			zje = qq;
			double xxx = xx / 100 / 12;
			double mqhke = 1;
			for (int i = 0; i < yy; i++) {
				mqhke = mqhke * (1 + xxx);
			}
			for (int i = 0; i < yy; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("qs", (i + 1));
				je =  getdoubleValue((xxx * mqhke / (mqhke - 1) * qq)+"",2);
				lx =  getdoubleValue(  (zje * xxx)+"",2);
				bj =  getdoubleValue(  (je - lx)+"",2);
				sy =  getdoubleValue(  ((je * yy) - je * (i + 1))+"",2);
				map.put("myhkje", je);
				map.put("myhkbj", bj);
				map.put("myhklx", lx);
				map.put("syhkje", sy);
				map.put("hkrq", getinterestDate(getinterestDate(date), (i+1)));
				list.add(map);
				zje = zje - bj;
			}
		} else if (ff == 2) {// 等本等息
			zje =  getdoubleValue(    (qq * xx / 100 / 12 * yy + qq)+"",2);// 总金额
			zlx =  getdoubleValue(    (qq * xx / 100 / 12 * yy)+"",2);// 总利息
			sy = zje;
			for (int i = 0; i < yy; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("qs", (i + 1) );
				if (i + 1 == yy) {
					je = sy;
					bj =  getdoubleValue(  ((zje - zlx) / yy)+"",2);
					lx =  getdoubleValue(  (sy - bj)+"",2);
					sy =  getdoubleValue(  (0)+"",2);
				} else {
					je =  getdoubleValue(  (zje / yy)+"",2);
					bj =  getdoubleValue(  ((zje - zlx) / yy)+"",2);
					lx =  getdoubleValue(  (zlx / yy)+"",2);
					sy =  getdoubleValue(  (sy - je)+"",2);
				}
				map.put("hkrq", getinterestDate(getinterestDate(date), (i+1)));
				map.put("myhkje", je);
				map.put("myhkbj", bj);
				map.put("myhklx", lx);
				map.put("syhkje", sy);
				list.add(map);
			}
		} else if (ff == 3) {// 先息后本
			zje =  getdoubleValue(  (qq * xx / 100 / 12 * yy + qq)+"",2);// 总金额
			zlx =  getdoubleValue(  (qq * xx / 100 / 12 * yy)+"",2); // 总利息
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("qs", "1");
			map1.put("myhkje", zlx);
			map1.put("myhkbj", "0.00");
			map1.put("myhklx", zlx);
			map1.put("syhkje", zje - zlx);
			map1.put("hkrq", getinterestDate(getinterestDate(date),0));
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("qs", "2");
			map2.put("myhkje", zje - zlx);
			map2.put("myhkbj", zje - zlx);
			map2.put("myhklx", "0.00");
			map2.put("syhkje", "0.00");
			map2.put("hkrq", getinterestDate(getinterestDate(date),yy));
			list.add(map1);
			list.add(map2);
		}else if (ff == 4){//按月付息到期还本
			zje =  getdoubleValue(  (qq * xx / 100 / 12 * yy + qq)+"",2);// 总金额
			zlx =  getdoubleValue(  (qq * xx / 100 / 12 * yy)+"",2); // 总利息
			sy = zje;
			for (int i = 0; i < yy; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("qs", (i + 1));
				if (i + 1 == yy) {
					je = sy;
					bj =  getdoubleValue(  (qq)+"",2);
					lx =  getdoubleValue(  (sy-qq)+"",2);
					sy =  getdoubleValue(  (0)+"",2);
				} else {
					je =  getdoubleValue(  (zlx / yy)+"",2);
					bj =  getdoubleValue(  (0)+"",2);
					lx =  getdoubleValue(  (zlx / yy)+"",2);
					sy =  getdoubleValue(  (sy - je)+"",2);
				}
				map.put("hkrq", getinterestDate(getinterestDate(date),i));
				map.put("myhkje", je);
				map.put("myhkbj", bj);
				map.put("myhklx", lx);
				map.put("syhkje", sy);
				list.add(map);
			}
		}

		return list;
	}

	
	public static String  getinterestDate(String date,int mm)  {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			  Calendar  g = Calendar.getInstance();  
	          g.setTime(sdf.parse(date));  
	          g.add(Calendar.MONTH,mm);             
	          Date d = g.getTime(); 
			 return sdf.format(d);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return date;
		}
	
	}
	
	

	
	
	public static String  getinterestDate(Date date)  {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	return sdf.format(date);	
	}
	
	
	public static double getdoubleValue(String value,int i){
		return (new  BigDecimal(Double.parseDouble(value)).setScale(i, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	public static void main(String[] args) {
	/*	List<Map<String, Object>> list = InterestUtil.getInterest("4955","3","6.6", "4",new Date());
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			System.out.println(map.get("qs") + "_" + map.get("myhkje") + "_"
					+ map.get("myhkbj") + "_" + map.get("myhklx") + "_"
					+ map.get("syhkje")+ map.get("hkrq"));
		}
		*/
		
	}

	
	
}
