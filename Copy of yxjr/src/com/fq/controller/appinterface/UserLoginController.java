package com.fq.controller.appinterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fq.annotations.InterfaceValidate;
import com.fq.service.TokenService;
import com.fq.service.UserService;
import com.fq.util.Const;
import com.fq.util.FormData;
import com.fq.util.PassWordUtil;
import com.fq.util.cookies.CookiesUtil;
@Controller
@RequestMapping(value="interface")
public class UserLoginController extends ResponseBaseController {
	
	private UserService userservice;
	private TokenService tservice;
	
	public TokenService getTservice() {
		return tservice;
	}
	@Autowired
	public void setTservice(TokenService tservice) {
		this.tservice = tservice;
	}
	public UserService getUserservice() {
		return userservice;
	}
	@Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	@RequestMapping(value="userlogin")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"mobile","password"})
	public Map<String, Object> userLogin() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
		String mobile=form.getString("mobile");
		String password=form.getString("password");
		
 		 List<FormData> list=userservice.checkMobile(form);
 		 if(list.size()==0){
 			res.put("code", "003");
			res.put("msg", "用户不存在");
			return res;
 		 }
			 
		//String password1= PassWordUtil.MD5(mobile.substring(mobile.length()-6, mobile.length()));
 		 String password1= PassWordUtil.MD5(form.getString("password"));

 		 //form.put("password", PassWordUtil.MD5(form.getString("password")));

		Map<String, Object> u = userservice.getUserByMobileAndPassword(mobile, password1);
		if(u == null || u.isEmpty() ){
			res.put("code", "001");
			res.put("msg", "密码与用户名不符");
			return res;
		}
		//String token  = PassWordUtil.MD5(u.get("id")+form.getString("tokenStr"));
		//form.put("token", token);
		//res = tservice.createTokenByUidAndTokenStr(form);
		res.put("code", "002");
		res.put("msg", "登陆成功");
		res.put("uid", u.get("id"));
		return res;
//		Map<String, Object> user= userservice.getUserByMobileAndPassword(mobile, password);
//		if(user == null || user.isEmpty()){
//			res.put("code", "001");
//			res.put("msg", "用户名密码错误");
//			return res;
//		}else{
//			String uid= String.valueOf(user.get("id"));
//			FormData f=new FormData();
//			f.put("uid", uid);
//			f.put("token", form.getString("token"));
//			boolean token=tservice.validateTokenByUidAndToken(f);
//			if(token){
//				res.put("code", "003");
//				res.put("msg", "登录失败，只能在一台手机上登录");
//				return res;
//			}else{
//				res.put("code", "002");
//				res.put("msg", "登录成功");
//				res.put("id", uid);
//				return res;	
//			}
//			
//			
 		}
		
		
	@RequestMapping(value="loginout")
	public Map<String, Object> logout (HttpServletResponse response){
		CookiesUtil.delCookie(response, Const.allowURL,Const.LOGIN_USER,Const.LOGIN_USER_NAME,Const.allowURL,Const.LOGIN_NAME);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", "004");
		map.put("msg", "注销成功");
		return map;
	}

	
	/** 获取用户定位坐标
	 * `userid` '用户id',
	  `acq_date` '获取时间',
	  `longitude`  '经度',
	  `latitude`  '纬度',
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="insertLat")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"userid","acq_date","longitude","latitude"})
	public Map<String, Object> insertLat() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
  		Integer u = userservice.insertLat(form);
		if(u >0 ){
			res.put("code", "00");
			res.put("msg", "添加成功");
			return res;
		}
		else{
			res.put("code", "00");
			res.put("msg", "添加失败");
			return res;
		}
		 
  	
 		}

	/***
	 * 微信登陆  绑定 手机号 wma20160530
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="wxuserlogin")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"mobile","password","wx"})
	public Map<String, Object> wxuserLogin() throws Exception
	{
		
		Map<String, Object> res=new HashMap<String, Object>();
		
		FormData form=this.getFormData();
		FormData mobform = new FormData();
		mobform.put("mobile", form.getString("mobile"));
		
 		List<FormData> list = userservice.checkMobile(mobform);
 		if(list.size()==0)
 		{
 			res.put("code", "003");
			res.put("msg", "用户不存在");//该手机号未注册，请先注册
			return res;
 		}
		
 		String password1= PassWordUtil.MD5(form.getString("password"));
 		JSONObject mobleUserJson = JSONObject.fromObject(list.get(0));
 		if(!password1.equals(mobleUserJson.get("password")))
 		{
 			res.put("code", "001");
 			res.put("msg", "密码与用户名不符");//密码错误，请重新输入密码
 			return res;
 		}
 		
 		int id = 0;//数据库操作id，标识是否成功绑定
 		if(mobleUserJson.containsKey("wx"))
 		{
			//该手机号已绑定过微信，重复绑定，返回已绑定的微信昵称
			res.put("code", "101");
			res.put("msg", mobleUserJson.get("username"));
			return res;
 		}
		//未绑定微信的手机号,缓存从access_token存储的微信数据，删除access_token保存的数据，把缓存的数据存储到手机号对应的字段上
		FormData wxform = new FormData();
		wxform.put("wx", form.getString("wx"));
		List<FormData> wxlist = userservice.checkMobile(wxform);
		if(wxlist.size() <= 0)
		{
	 		res.put("code", "100");
	 		res.put("msg", "invalid_openid");//数据异常，未找到access_token保存的数据
	 		return res;
		}
		userservice.delwx(wxform);
		JSONObject wxUserJson = JSONObject.fromObject(wxlist.get(0));
		mobform.put("wx", wxUserJson.get("wx"));
		mobform.put("username", wxUserJson.get("username"));
		mobform.put("headimg", wxUserJson.get("headimg"));
		id = userservice.updateMobUser(mobform);
		
	  	if(id <= 0)
	  	{			
			res.put("code", "80");
			res.put("msg", "登陆失败");//数据更新失败
			return res;
		}
		res.put("code", "002");
		res.put("msg", "登陆成功");
		return res;
	}
}
