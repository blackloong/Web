package com.fq.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fq.controller.base.BaseController;
import com.fq.service.UserService;
import com.fq.util.Const;
import com.fq.util.FormData;
import com.fq.util.PassWordUtil;
import com.fq.util.cookies.CookiesUtil;

/**
 * 当未取到用户信息时提示用户登录Controller
 * @author P
 * @date d2015-4-22
 */
@Controller
public class OpenLoginController extends BaseController{

	private UserService userService;
	
	
	
	public UserService getIndexService() {
		return userService;
	}

	@Autowired
	public void setIndexService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="loginAjax",method=RequestMethod.POST)
	@ResponseBody
	public String loginAjax (String mobile,String password,HttpServletResponse response)throws Exception{
		password = PassWordUtil.MD5(password);
		FormData formData = this.getFormData();
		Map<String, Object> user = userService.getUserByMobileAndPassword(mobile, password);
		formData.put("roleId", user.get("roleid"));
		List<Map<String, String>> allowUrl = userService.getAllowUrlByRoleId(formData);
		String allow = "";
		for(Map<String, String> map : allowUrl ){
				allow +=map.get("detail_url");
		}
		CookiesUtil.addCookie(response, Const.allowURL, allow, 4*60*60);
		if(user == null || user.isEmpty()){
			return "1";
		}else{
			CookiesUtil.addCookie(response, Const.LOGIN_USER, user.get("id").toString(), 4*3600);
			return "0";
		}
	}
}
