package com.fq.controller.appinterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fq.annotations.InterfaceValidate;
import com.fq.service.UserService;
import com.fq.util.DistanceUtil;
import com.fq.util.FormData;
/***
 * 
 * 网点类
 */
@Controller
@RequestMapping(value="interface")
public class DotIntefaceController extends ResponseBaseController {
	
	private UserService userservice;
	public UserService getUserservice() {
		return userservice;
	}
	@Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
 
	
    /***
     * 获取网点查询信息 wma20151106
     * @return
     * @throws Exception
     */
	@RequestMapping(value="dotList")
	@ResponseBody
 	public Map<String, Object> dotList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
		List<FormData> list = userservice.dotList(form);
		res.put("data", list);
		if(list.size()>0 ){
			res.put("code", "00");
			res.put("msg", "查询成功");
			return res;
		}
		else{
			res.put("code", "00");
			res.put("msg", "查询失败");
			return res;
		}
		 
  	
 		}
	
	
	  /***
     * 获取网点详细信息 wma20151106
     * id 网点地区id
     * @return
     * @throws Exception
     */
	@RequestMapping(value="dotderList")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"id"})
	public Map<String, Object> dotderList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
		List<FormData> list = userservice.dotderList(form);
		res.put("data", list);
		if(list.size()>0 ){
			res.put("code", "00");
			res.put("msg", "查询成功");
			return res;
		}
		else{
			res.put("code", "00");
			res.put("msg", "查询失败");
			return res;
		}
  	
 		}
	
	/**
	 * 附近网点 信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="jrdotList")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"long1","lat1"})
	public Map<String, Object> BannerList(String uid) throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();		 
		FormData form=this.getFormData();
		List<Map<String, Object>> shops = userservice.dotLists(form);
		 Double lat2=0D;
         Double lng2=0D;
         Double jl=0D;
         
         //当前坐标的经度 维度
    	 Double lat1=Double.parseDouble(form.get("long1").toString());
         Double lng1=Double.parseDouble(form.get("lat1").toString());
         
         //`longitude` varchar(30) default NULL COMMENT '经度',
         //`latitude` varchar(30) default NULL COMMENT '纬度',
   	  // Map<String, String> re=new HashMap<String, String>();
         //获取送货地址的经度 维度 
       for (int i = 0; i < shops.size(); i++) {
    	   if ((((Map)shops.get(i)).get("longitude") != null) && (((Map)shops.get(i)).get("longitude") != ""))
    	      {
    	        lat2 = Double.valueOf(Double.parseDouble(((Map)shops.get(i)).get("longitude").toString()));
    	      }

    	      if ((((Map)shops.get(i)).get("latitude") != null) && (((Map)shops.get(i)).get("latitude") != "")) {
    	        lng2 = Double.valueOf(Double.parseDouble(((Map)shops.get(i)).get("latitude").toString().toString()));
    	      }
       	 //经纬度 距离的计算
       	   jl=DistanceUtil.Distance(lat1, lng1, lat2, lng2);
	        	shops.get(i).put("shopjl",Double.toString( jl));
           
		}
       
       Collections.sort(shops,new MapComparator());
		 List<Map<String, String>> shops1= new  ArrayList<Map<String, String>> () ;
      
		  
		if(shops.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
			res.put("date", shops);
		}else{
			res.put("code", "10");
			res.put("msg", "无新信息");
		}
		return  res;
	}
	
	
	  /***
     *  城市字典 wma 20160314
     * @return
     * @throws Exception
     */
	@RequestMapping(value="cityList")
	@ResponseBody
 	public Map<String, Object> cityList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
		List<FormData> list = userservice.cityList(form);
		res.put("data", list);
		if(list.size()>0 ){
			res.put("code", "00");
			res.put("msg", "查询成功");
			return res;
		}
		else{
			res.put("code", "10");
			res.put("msg", "查询失败");
			return res;
		}
		 
  	
 		}
	
	
	
	/***
     * 
     * @author list<Map> 距离排序 
     *
     */
    public  class MapComparator implements Comparator<Map<String, Object>> {
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


