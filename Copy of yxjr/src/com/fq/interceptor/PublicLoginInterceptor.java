package com.fq.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.PublicLoginAuth;
import com.fq.service.UserService;
import com.fq.util.Const;
import com.fq.util.cookies.CookiesUtil;

public class PublicLoginInterceptor implements HandlerInterceptor{

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		if(arg2.getClass().isAssignableFrom(HandlerMethod.class))
		{
			PublicLoginAuth authPassPort =((HandlerMethod)arg2).getMethodAnnotation(PublicLoginAuth.class);
			if(authPassPort == null || authPassPort.validate() == false){
				return true;
			}else{
				String userid = CookiesUtil.getCookie(request, Const.LOGIN_USER);
				String returnURL = request.getParameter("returnURL");
				if(userid  == null || userid.equals("") ){
					response.setCharacterEncoding("gb2312");
					String path = request.getContextPath();
					if(returnURL != null && !returnURL.equals(""))
						path += "?returnURL="+returnURL;
					String requestURI = request.getRequestURI();
					String loginOpen ="<script type=\"text/javascript\" charset=\"text/html; charset=utf-8\">	" +
							"alert('登录超时或登录有误,请重新登录');location.href='"+path+"/public_login';</script>";
					response.getWriter().write(loginOpen);
					return false;
				}else{
					return true;
				}
			}
		}
		else
		{
			return true;
		}
	}

}
