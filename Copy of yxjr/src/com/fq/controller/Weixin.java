package com.fq.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fq.controller.base.BaseController;
import com.fq.form.WeixinConst;
import com.fq.form.WeixinForm;
import com.fq.service.DictionaryService;
import com.fq.util.FormData;
import com.fq.util.soursinterface;

@Controller
public class Weixin extends BaseController {
	
	@Autowired
	DictionaryService dirservice;

 	static String tokens;
	
	//微信测试
	@RequestMapping("weixin1")
	@ResponseBody
	public  String checkWeixinSign () {
		FormData data = this.getFormData();
 		return data.getString("echostr");
	}
	 
	//获取用token
  	public static  String merchant_introducebymid() throws Exception{
 		WeixinForm mf=new WeixinForm();
 		mf.setGrant_type("client_credential");
 		mf.setAppid(WeixinConst.AppId);
 		mf.setSecret(WeixinConst.appsecret);
  		String url="https://api.weixin.qq.com/cgi-bin/token"; 
 		String access_token = soursinterface.doPost(url, mf, "UTF-8", 1000);
		JSONObject b=JSONObject.fromObject(access_token);
		String token=b.getString("access_token") ;  
 		return token;
	}
	
	 
	//获取用户列表
 	public static  String info() throws Exception{
		WeixinForm mf=new WeixinForm();
   		String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+tokens; 
		String access_token = soursinterface.doPost(url, mf, "UTF-8", 1000);
		JSONObject b=JSONObject.fromObject(access_token);
		return b.toString();
	}
 	
	//获取用户基本信息
 	public static  String infode() throws Exception{
		WeixinForm mf=new WeixinForm();
		mf.setAccess_token(tokens);
		mf.setOpenid("oTaLHvqfIj5pHrldzrL7yTEQNNBg");
		mf.setLang(WeixinConst.setLang);
   		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+tokens+"&openid=oTaLHvqfIj5pHrldzrL7yTEQNNBg&lang=zh_CN"; 
		String access_token = soursinterface.doPost(url, mf, "UTF-8", 1000);
		JSONObject b=JSONObject.fromObject(access_token);
		return b.toString();
	}
 	
	//创建菜单
 	public static  String infodess() throws Exception{
 
       String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+tokens; 
		//String access_token = soursinterface.doPost(url, mf, "UTF-8", 1000);
         JSONArray bs=new JSONArray();
          Map<String ,Object> map=new HashMap<String, Object>();
          map.put("type", "click");
          map.put("name", "今日歌曲");
          map.put("key", "V1001_TODAY_MUSIC");
          bs.add(0, map);

            		   
		JSONObject b=new JSONObject();
		b.put("button",bs);
		  
		System.out.println(b);
	    //  String data= soursinterface.postJson(url, b);
	      String data= soursinterface.doPostWithBody(url, bs.toString());

 
		return data;
	}
 	
 	public String token() throws Exception{
 		
  		FormData form=new FormData();		
 		form.put("id", 497);
	    Map<String, Object>  tokens= dirservice.findDictionaryId(form);
 	      System.out.println(tokens);
 	     return tokens.toString();
	}

 	
	//用户同意授权，获取code
 	public static  String infcode() throws Exception{
		WeixinForm mf=new WeixinForm();
		mf.setAccess_token(tokens);
		mf.setOpenid("oTaLHvqfIj5pHrldzrL7yTEQNNBg");
		mf.setLang(WeixinConst.setLang);
   		String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WeixinConst.AppId+
   				"&redirect_uri=http://mingan21.6655.la/yxjr/weixin" +
   				"&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"; 
   		
		String access_token = soursinterface.doPost(url, mf, "UTF-8", 1000);
		JSONObject b=JSONObject.fromObject(access_token);
		return b.toString();
	}
 	
 	//接口token
	public static void main(String[] args) throws Exception {
	  //可以保存token在库，也可以每次请求
	   String token= merchant_introducebymid();
		 System.out.println("token  "+token);
		/*tokens=token;
	
		String infolist=info();
 	    System.out.println("用户列表"+infolist);
		System.out.println("用户信息"+infode());    */
		//System.out.println("创建菜单"+infodess());
 	
 
		//infcode();
	}
	
	
	
}
