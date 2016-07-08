package com.fq.controller.appinterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fq.util.FormData;
import com.fq.util.PageInfo;

public class ResponseBaseController {

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
	@ExceptionHandler
	public String exception (HttpServletRequest request,Exception e){
		e.printStackTrace();
		return "apperror500";
	}
}
