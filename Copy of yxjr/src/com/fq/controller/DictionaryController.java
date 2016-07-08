package com.fq.controller;

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

import com.fq.annotations.LoginAuth;
import com.fq.controller.base.BaseController;
import com.fq.service.DictionaryService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

@Controller
public class DictionaryController extends BaseController{

	
	 private DictionaryService service;

	public DictionaryService getService() {
		return service;
	}
	@Autowired
	public void setService(DictionaryService service) {
		this.service = service;
	}
	 
	
	@RequestMapping(value = "diclist")
	public String index(PageInfo page, Model model, HttpServletResponse response)
			throws Exception {
		if (page == null)
			page = new PageInfo();
		FormData pd = this.getFormData();
		page.setFormData(pd);
		List<FormData> data = service.findDictionaryPage(page);
		model.addAttribute("pageData", data);
		model.addAttribute("page", page);
		return "dictionary/list";
	}

	@RequestMapping(value = "dicedit")
	public ModelAndView edit()
			throws Exception {
		FormData page = this.getFormData();
		String id = page.getString("id");
		ModelAndView mv = new ModelAndView("dictionary/edit");
		if (id != null && !"".equals(id)) {
			mv.addObject("data", service.findDictionaryId(page));
		}
		return mv;
	}
	
	@RequestMapping(value = "dicsave")
	public String save()
			throws Exception {
		FormData page = this.getFormData();
		String id = page.getString("id");
		
		if (id != null && !"".equals(id)) {
			service.updateDictionary(page);
		}else{
			service.insertDictionary(page);
		}
		return "redirect:diclist";
	}

	
	@RequestMapping(value="dicshow")
	@LoginAuth
	public ModelAndView programDetailList ()throws Exception{
		ModelAndView mv = new ModelAndView ("dictionary/show");
		mv.addObject("id", this.getFormData().getString("id"));
		return mv;
	}
	
	@RequestMapping(value="dicShowAjax")
	@ResponseBody
	public List<Map<String, String>> dicShowAjax (String id)throws Exception{
		List<Map<String, String>> details = service.findDictionaryPid(this.getFormData());
		return details;
	}
	
	

	@RequestMapping(value = "dicEditSub")
	@ResponseBody
	public Map<String, Object> dicEditSub() {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			FormData page = this.getFormData();
			String id = page.getString("id");
			
			if (id != null && !"".equals(id)) {
				service.updateDictionary(page);
			}else{
				service.insertDictionary(page);
			}
			map.put("boo", true);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("boo", false);
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="dicdelete")
	public Map<String, Object> deletedic ()throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			service.deleteDictionary(this.getFormData());
			map.put("boo", true);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("boo", false);
		}
		
		return map;
	}
	
	
	
}
