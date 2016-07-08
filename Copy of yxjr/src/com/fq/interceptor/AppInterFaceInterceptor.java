package com.fq.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.InterfaceValidate;
import com.fq.service.TokenService;
import com.fq.util.FormData;

public class AppInterFaceInterceptor implements HandlerInterceptor {
	
	private TokenService tokenService;
	
	@Autowired
	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
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
		if(arg2.getClass().isAssignableFrom(HandlerMethod.class)){
			InterfaceValidate interfaceValidate = ((HandlerMethod)arg2).getMethodAnnotation(InterfaceValidate.class);
			if(interfaceValidate != null ){
				if(!request.getMethod().toUpperCase().equals("POST")){
					response.setCharacterEncoding("utf-8");
					response.setContentType("application/json");
					response.getWriter().write("{\"code\":\"01\",\"msg\":\"请求方式为POST\"}");
					return false;
				}
				boolean tokenValue = interfaceValidate.tokenValidate();
				FormData formData = new FormData(request);
				String[] dataValidate = interfaceValidate.vliadateRequestData();
				List<String> dVList = Arrays.asList(dataValidate);
				dVList = new ArrayList<String>(dVList);
				if(tokenValue){
					dVList.add("uid");
					dVList.add("token");
				}
				if(dVList != null && dVList.size() >0 ){
					for(String key:dVList){
						if(!formData.containsKey(key)){
							response.setCharacterEncoding("utf-8");
							response.setContentType("application/json");
							response.getWriter().write("{\"code\":\"01\",\"msg\":\""+key+"值取缺失\"}");
							return false;
						}else{
							String value = formData.getString(key);
							if(value==null || value.trim().equals("")){
								response.setCharacterEncoding("utf-8");
								response.setContentType("application/json");
								response.getWriter().write("{\"code\":\"01\",\"msg\":\""+key+"值不可为空\"}");
								return false;
							}
						}
					}
				}
				if(tokenValue){
					boolean flagTokenValidate = tokenService.validateTokenByUidAndToken(formData);
					if(!flagTokenValidate){
						response.setCharacterEncoding("utf-8");
						response.setContentType("application/json");
						response.getWriter().write("{\"code\":\"30\",\"msg\":\"token失效\"}");
						return false;
					}
				}
				return true;
			}else{
				return true;
			}
		}
		return true;
	}

}
