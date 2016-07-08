package com.fq.util;

import java.util.Comparator;
import java.util.Map;


public class DistanceUtil {
	/** 
     * 计算地球上任意两点(经纬度)距离 
     *  
     * @param long1 
     *            第一点经度 
     * @param lat1 
     *            第一点纬度 
     * @param long2 
     *            第二点经度 
     * @param lat2 
     *            第二点纬度 
     * @return 返回距离 单位：米 
     */  
    public static double Distance(double long1, double lat1, double long2,  
            double lat2) {  
        double a, b, R;  
        R = 6378137; // 地球半径  
        lat1 = lat1 * Math.PI / 180.0;  
        lat2 = lat2 * Math.PI / 180.0;  
        a = lat1 - lat2;  
        b = (long1 - long2) * Math.PI / 180.0;  
        double d;  
        double sa2, sb2;  
        sa2 = Math.sin(a / 2.0);  
        sb2 = Math.sin(b / 2.0);  
        d = 2  
                * R  
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)  
                        * Math.cos(lat2) * sb2 * sb2));  
        return d;  
    }  
 
    
    /***
     * 
     * @author list<Map<String, String>> 距离排序 
     *
     */
    public static class MapComparator implements Comparator<Map<String, String>> {
    	  @Override
		public int compare(Map<String, String> o1, Map<String, String> o2) {
           Double s1 = Double.parseDouble( o1.get("shopjl"));  
           Double s2 = Double.parseDouble(o2.get("shopjl"));  
	
	        if(s1>s2) {  
	         return 1;  
	        }else {  
	         return -1;  
	        }  
       }
    }
    
    
    /***
     * 
     * @author list<Map<String, Object>> 距离排序 
     *
     */
    public  class MapComparator1 implements Comparator<Map<String, Object>> {
    	@Override
		public int compare(Map<String, Object> o1, Map<String, Object> o2) {
           Double s1 = Double.parseDouble( o1.get("shopjl").toString());  
           Double s2 = Double.parseDouble(o2.get("shopjl").toString());  
	
	        if(s1>s2) {  
	         return 1;  
	        }else {  
	         return -1;  
	        }  
       }
    }
    
	
}
