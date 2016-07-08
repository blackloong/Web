package com.fq.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fq.controller.base.BaseController;
import com.fq.service.UserService;
import com.fq.util.Const;
import com.fq.util.FormData;
import com.fq.util.PassWordUtil;
import com.fq.util.cookies.CookiesUtil;

/**
 * 登陆controller 
 * @author P
 * @date d2015-4-28
 */
@Controller
public class LoginController extends BaseController{

	private UserService service;
	
	
	public UserService getService() {
		return service;
	}
	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	@RequestMapping(value="/")
	public String toLog () {
		System.out.println("11111111111111111111111");
		return "login/login";
	}
	@RequestMapping(value="/log")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response)throws Exception {
		FormData formData = this.getFormData();
		 String mobile=formData.getString("mobile");
		 //formData.put("password", PassWordUtil.MD5(mobile.substring(mobile.length()-6, mobile.length())));
		 
		 formData.put("password", PassWordUtil.MD5(formData.getString("password")));

		 
		Map<String, Object> user = service.getUserByMobileAndPassWord(formData);
		
		ModelAndView mv = new ModelAndView ();
		if(user == null || user.isEmpty()){
			mv.setViewName("login/login");
			mv.addObject("errorMsg", "用户名密码错误");
			return mv;
		}
		formData.put("roleId", user.get("roleid"));
		List<Map<String, String>> allowUrl = service.getAllowUrlByRoleId(formData);
		if(allowUrl.size()<=0){
			mv.setViewName("login/login");
			mv.addObject("errorMsg", "你没有后台操作系统的权限");
			return mv;
		}
		Map<String, Object> program = service.getRoleAuthByRoleId(formData);
		CookiesUtil.addCookie(response, Const.LOGIN_USER, user.get("id").toString(), 4*60*60);//单位秒
		CookiesUtil.addCookie(response, Const.LOGIN_ROLE, user.get("roleid").toString(), 4*60*60);//手机号
		CookiesUtil.addCookie(response, Const.LOGIN_USER_NAME, user.get("mobile").toString(), 4*60*60);//手机号
		CookiesUtil.addCookie(response, Const.LOGIN_NAME, URLEncoder.encode(user.get("username").toString(), "utf-8"), 4*60*60);

		String allow = "";
		for(Map<String, String> map : allowUrl ){
			if(map == null )continue;
			allow +=map.get("detail_url");
		}
		CookiesUtil.addCookie(response, Const.allowURL, allow, 4*60*60);
		request.getSession().setAttribute("program", program);
		//mv.setViewName("redirect:programTeam");
		//mv.setViewName("redirect:"+allowUrl.get(0).get("detail_url"));
		mv.setViewName("index/index");
		return mv;
	}
	@RequestMapping(value="logout")
	public String logout (HttpServletResponse response,HttpServletRequest request){
		CookiesUtil.delCookie(response, Const.allowURL,Const.LOGIN_USER,Const.LOGIN_USER_NAME,Const.LOGIN_ROLE,Const.allowURL,Const.LOGIN_NAME,Const.LOGIN_MOBILE);
		return "redirect:/";
	}
	
	
}
