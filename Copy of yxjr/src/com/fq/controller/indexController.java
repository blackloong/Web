package com.fq.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.LoginAuth;
import com.fq.controller.base.BaseController;
import com.fq.entity.Message;
import com.fq.entity.Version;
import com.fq.service.VersionService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

@Controller
public class indexController extends BaseController {
 
	
	@Autowired
	private VersionService versionService;
	
	 
	
	
	//版本管理
	@RequestMapping(value="veisonList")		
		public String veisonList(PageInfo page, Model model, HttpServletResponse response)
				throws Exception {
			if (page == null)
				page = new PageInfo();
			FormData pd = this.getFormData();
			page.setFormData(pd);
			List<FormData> data = versionService.getAllPage(page);
			model.addAttribute("pageData", data);
			model.addAttribute("page", page);	
		return "version/versionList";
		
		
		
	}
	
	//版本管理
	@RequestMapping(value="MessageList")		
		public String MessageList(PageInfo page, Model model, HttpServletResponse response,String uname)
				throws Exception {
			if (page == null)
				page = new PageInfo();
			FormData pd = this.getFormData();
			page.setFormData(pd);
			List<FormData> data = versionService.getMessageAllPage(page);
			model.addAttribute("pageData", data);
			model.addAttribute("page", page);	
			model.addAttribute("uname", uname);	
		return "message/messageList";
		
		
	}
	
	
	/***
	 * 消息详情
	 * @return
	 * @throws Exception
	 */
	@LoginAuth
	@RequestMapping(value = "editMessage")
	public ModelAndView editMessage() throws Exception {
		FormData page = this.getFormData();
		String id = page.getString("id");
		ModelAndView mv = new ModelAndView("message/messageEdit");
		if (id != null && !id.trim().equals("")) {
			mv.addObject("data", versionService.selectMsByPrimaryKey(Integer.parseInt(id)));
		}
		
		mv.addObject("datauser", versionService.getuserAll());
 
		return mv;
	}
	
	
	/**
	 * 版本详情
	 * @return
	 * @throws Exception
	 */
	@LoginAuth
	@RequestMapping(value = "editVersion")
	public ModelAndView editUser() throws Exception {
		FormData page = this.getFormData();
		String id = page.getString("id");
		ModelAndView mv = new ModelAndView("version/versionEdit");
		if (id != null && !id.trim().equals("")) {
			mv.addObject("data", versionService.selectByvePrimaryKey(Integer.parseInt(id)));
		}
		
 
		return mv;
	}
 
	@LoginAuth
	@RequestMapping(value = "saveMessage")
	public String saveUser(Message me) throws Exception {
		 versionService.saveMeorUpdate(me);
		return "redirect:MessageList";
	}
 
	
	@LoginAuth
	@RequestMapping(value = "saveVesion")
	public String saveUser(Version ve) throws Exception {
		 versionService.saveVeorUpdate(ve);

		return "redirect:veisonList";
	}
	
	@LoginAuth
	@RequestMapping(value = "delMessage")
 	public String delUser() throws Exception {
		FormData form = this.getFormData();
 		String id = form.get("id").toString();
	 
		versionService.deleteByMePrimaryKey (Integer.parseInt(id));
		return "redirect:MessageList";
		
	}

	@LoginAuth
	@RequestMapping(value = "delVerSion")
	 
	public String delVerSion() throws Exception {
		FormData form = this.getFormData();
 		String id = form.get("id").toString();
		versionService.deleteByVerPrimaryKey(Integer.parseInt(id));
		return "redirect:veisonList";
	}

	

	
}
