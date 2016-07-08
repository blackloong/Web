package com.fq.controller.appinterface;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fq.annotations.InterfaceValidate;

/**
 * 登陆接口
 * @author P
 * @date d2015-4-30
 */
@Controller
@RequestMapping(value="interface")
public class AppLoginController extends ResponseBaseController{
	
	@RequestMapping(value="logForVip")
	@InterfaceValidate(tokenValidate=true,vliadateRequestData={"mobile","psd"})
	@ResponseBody
	public Map<String, String> log ()throws Exception {
		
		
		return null;
	}
	
}
