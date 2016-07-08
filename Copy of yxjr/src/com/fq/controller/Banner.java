package com.fq.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.fq.annotations.LoginAuth;
import com.fq.controller.base.BaseController;
import com.fq.service.BannerService;
/**
 * banner controller
 * @author P
 * @date d2015-5-27
 */
@Controller
public class Banner extends BaseController {

	@Autowired
	private BannerService service;
	
	@RequestMapping(value="bannerlist")
	@LoginAuth
	public ModelAndView bannerlist () throws  Exception {
		ModelAndView mv = new ModelAndView ("banner/banner");
		return mv;
	}
	@RequestMapping(value="bannerAjax")
	@ResponseBody
	public List<Map<String, String>> bannerAjax ()throws Exception {
		return service.BannerList();
	}
	@RequestMapping(value="bannerSub")
	@ResponseBody
	public String bannerSub (@RequestParam(required=false) MultipartFile img,HttpServletRequest request) throws Exception {
		service.bannerSub(img, request, getUserId());
		return "1";
	}
	@RequestMapping(value="bannerDel")
	@ResponseBody
	public String bannerDel () throws Exception {
		service.bannerDel(getFormData());
		return "1";
	}
	
	
	
}
