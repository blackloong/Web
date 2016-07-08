package com.fq.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.LoginAuth;
import com.fq.service.UserService;
import com.fq.util.Const;
import com.fq.util.cookies.CookiesUtil;

public class LoginInterceptor implements HandlerInterceptor{

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
			LoginAuth authPassPort =((HandlerMethod)arg2).getMethodAnnotation(LoginAuth.class);
			if(authPassPort == null || authPassPort.validate() == false){
				return true;
			}else{
				String userid = CookiesUtil.getCookie(request, Const.LOGIN_USER);
				if(userid  == null || userid.equals("") ){
					response.setCharacterEncoding("gb2312");
					String path = request.getContextPath();
					String requestURI = request.getRequestURI();
					String loginOpen = "<script src=\""+path+"/assets/js/jquery1.11.1.min.js\"></script>";
					loginOpen  +="<script src=\""+path+"/assets/js/layer.js\"></script>";
					String html = "<form action=\"loginAjax\" method=\"post\" id=\"form1\"><table align=\"center\" style=\"margin-top:30px;\" ><tr><td align=\"right\" style=\"height:50px;\">用户名:</td><td><input name=\"mobile\" /><td></tr><tr><td align=\"right\" >密&nbsp;&nbsp;&nbsp;码:</td><td ><input name=\"password\" type=\"password\"/><td></tr><tr><td align=\"right\" colspan=\"2\" style=\"padding-top:15px;\"><a style=\"background:#0e90d2;color:#fff;text-decoration:none;display:inline-block; padding:4px 0px;width:158px; text-align:center\"  href=\"javascript:loginAjax();\">登录</a></td></tr></table></form>";
					loginOpen +="<script type=\"text/javascript\" charset=\"text/html; charset=utf-8\">	$(function(){ layer.open({type:1,shadeClose: false,offset: '270px',shade: 0.4,area:['420px', '240px'],title:'登录超时,请重新登录',	content:'"+html+"' });});";
					loginOpen +="function loginAjax(){if($('input[name=mobile]').val()=='') {layer.msg('用户名不能为空');return;}if($('input[name=password]').val()=='') {layer.msg('密码不能为空');return;}$.ajax({url:'loginAjax',type:'post',data:$('#form1').serialize(),success:function(data){if(data=='0'){window.location.href='"+requestURI+"'}else{layer.msg('用户名或密码错误')}}});}";
					loginOpen +="</script>";
					loginOpen +="<style type=\"text/css\">body{font-family:\"微软雅黑\"}</style><body></body>";
					response.getWriter().write(loginOpen);
					return false;//programDetailListprogramDetailListAjaxgm_product_order_list

				}else{
					String url = CookiesUtil.getCookie(request,  Const.allowURL);
					String uri = request.getRequestURI();
					String projectPath = request.getContextPath();
					uri = uri.replace(projectPath+"/", "");
					if(url.indexOf(uri)<=-1){
						ResponseBody responseBody =((HandlerMethod)arg2).getMethodAnnotation(ResponseBody.class);
						if(responseBody != null ){
							response.setCharacterEncoding("gb2312");
							response.setContentType("application/json");
							response.getWriter().write("{\"errorMsg\":\"你没有此操作权限"+uri+"\"}");
							return false;
						}
						response.setCharacterEncoding("gb2312");
						String path = request.getContextPath();
						String loginOpen = "<script src=\""+path+"/assets/js/jquery1.11.1.min.js\"></script>";
						loginOpen  +="<script src=\""+path+"/assets/js/layer.js\"></script>";
						loginOpen +="<script type=\"text/javascript\" charset=\"text/html; charset=utf-8\">	$(function(){ layer.alert('您没有当前功能的操作权限"+uri+"',{offset:'300px;'},function(){history.go(-1);});});";
						loginOpen +="</script>";
						loginOpen +="<style type=\"text/css\">body{font-family:\"微软雅黑\"}</style><body></body>";
						response.getWriter().write(loginOpen);
						return false;
					}
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
