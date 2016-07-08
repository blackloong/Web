package com.fq.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fq.util.Const;
import com.fq.util.FormData;
import com.fq.util.PageInfo;/*
import com.sun.xml.internal.ws.client.ResponseContext;
import com.sun.xml.internal.ws.client.ResponseContextReceiver;*/
import com.fq.util.cookies.CookiesUtil;

public class BaseController {

	
	public FormData getFormData(){
		return new FormData(this.getRequest());
	}
	
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}


	
	public PageInfo getPage(){
		
		return new PageInfo();
	}
	
	public String userId;
	public String getUserId(){
		return CookiesUtil.getCookie(getRequest(), Const.LOGIN_USER);
	}
	public String getUserName(){
		return CookiesUtil.getCookie(getRequest(), Const.LOGIN_USER_NAME);
	}
	public String getUserUname(){
		return CookiesUtil.getCookie(getRequest(), Const.LOGIN_NAME);
	}
	public String getUserRole(){
		return CookiesUtil.getCookie(getRequest(), Const.LOGIN_ROLE);
	}
	protected void resposeWrite(String message,HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html;charset=utf-8");   
		response.setHeader("Cache-Control", "no-cache");   

		PrintWriter writer = response.getWriter();
		writer.write(message);
		writer.flush();
		writer.close();
	}
	public String  getDateLoanCode() throws IOException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String str=sdf.format(new Date())+""+(int)(Math.random()*9000+1000);
		
		 return str;	
	}
	public String  getDateProjectCode() throws IOException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String str="T"+sdf.format(new Date());
		 return str;	
	}
	
	public String getBasePath () {
		String path = this.getRequest().getContextPath();
		int port = this.getRequest().getServerPort();
		String basePath = null;
		if(port == 80)
			basePath = this.getRequest().getScheme()+"://"+this.getRequest().getServerName()+path;
		else{
			basePath = this.getRequest().getScheme()+"://"+this.getRequest().getServerName()+":"+this.getRequest().getServerPort()+path;
		}
		return basePath;
	}
}
