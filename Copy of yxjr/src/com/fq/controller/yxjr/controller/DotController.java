package com.fq.controller.yxjr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fq.controller.base.BaseController;
import com.fq.controller.yxjr.service.DotService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

@Controller
public class DotController extends BaseController{

	@Autowired
	DotService service;
	
	
	
	                         
	@RequestMapping(value = "dotlist")
	public String index(PageInfo page, Model model, HttpServletResponse response)
			throws Exception {
		if (page == null)
			page = new PageInfo();
		FormData pd = this.getFormData();
		page.setFormData(pd);
		List<Map<String, Object>> data = service.findDotPage(page);
		model.addAttribute("pageData", data);
		model.addAttribute("page", page);
		return "dot/list";
	}

	@RequestMapping(value = "dotedit")
	public ModelAndView edit()
			throws Exception {
		FormData page = this.getFormData();
		String id = page.getString("id");
		ModelAndView mv = new ModelAndView("dot/edit");
		if (id != null && !"".equals(id)) {
			mv.addObject("data", service.findDotId(page));
		}
		return mv;
	}
	
	@RequestMapping(value = "dotsave")
	public String save()
			throws Exception {
		FormData page = this.getFormData();
	
		String id = page.getString("id");
		
		if (id != null && !"".equals(id)) {
			service.updateDot(page);
		}else{
			service.insertDot(page);
		}
		return "redirect:dotlist";
	}

	@ResponseBody
	@RequestMapping(value="dotdelete")
	public Map<String, Object> deletedic ()throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			service.deleteDot(this.getFormData());
			map.put("boo", true);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("boo", false);
		}
		
		return map;
	}
	
	
}
