package com.fq.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fq.util.FormData;

@Controller
public class RequestController {

	@SuppressWarnings("unchecked")
	@RequestMapping(value="requestParam")
	@ResponseBody
	public Map<String, Object> requestParam(HttpServletRequest request) throws Exception{
		return new FormData(request);
	}
}
