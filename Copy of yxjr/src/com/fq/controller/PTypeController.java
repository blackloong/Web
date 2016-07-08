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

/***
 * 分类查询维护
 */
@Controller
public class PTypeController extends BaseController{
	 private DictionaryService service;

	public DictionaryService getService() {
		return service;
	}
	@Autowired
	public void setService(DictionaryService service) {
		this.service = service;
	}
	 
	
	@RequestMapping(value = "typediclist")
	public String index(PageInfo page, Model model, HttpServletResponse response)
			throws Exception {
		if (page == null)
			page = new PageInfo();
		FormData pd = this.getFormData();
		page.setFormData(pd);
		List<FormData> data = service.findtypePage(page);
		model.addAttribute("pageData", data);
		model.addAttribute("page", page);
		return "type/list";
	}

	@RequestMapping(value = "typedicedit")
	public ModelAndView edit()
			throws Exception {
		FormData page = this.getFormData();
		String id = page.getString("id");
		ModelAndView mv = new ModelAndView("type/edit");
		if (id != null && !"".equals(id)) {
			mv.addObject("data", service.findtypeId(page));
		}
		return mv;
	}
	
	@RequestMapping(value = "typedicsave")
	public String save()
			throws Exception {
		FormData page = this.getFormData();
		String id = page.getString("id");
		
		if (id != null && !"".equals(id)) {
			service.updatetype(page);
		}else{
			service.inserttype(page);
		}
		return "redirect:typediclist";
	}

	
	@RequestMapping(value="typedicshow")
	@LoginAuth
	public ModelAndView programDetailList ()throws Exception{
		ModelAndView mv = new ModelAndView ("type/show");
		mv.addObject("id", this.getFormData().getString("id"));
		return mv;
	}
	
	@RequestMapping(value="typedicShowAjax")
	@ResponseBody
	public List<Map<String, String>> dicShowAjax (String id)throws Exception{
		List<Map<String, String>> details = service.findtypePid(this.getFormData());
		return details;
	}
	
	

	@RequestMapping(value = "typedicEditSub")
	@ResponseBody
	public Map<String, Object> dicEditSub() {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			FormData page = this.getFormData();
			String id = page.getString("id");
			
			if (id != null && !"".equals(id)) {
				service.updatetype(page);
			}else{
				service.inserttype(page);
			}
			map.put("boo", true);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("boo", false);
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="typedicdelete")
	public Map<String, Object> deletedic ()throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			service.deletetype(this.getFormData());
			map.put("boo", true);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("boo", false);
		}
		
		return map;
	}
	
	
	
}
