package com.fq.controller.auth;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.LoginAuth;
import com.fq.controller.base.BaseController;
import com.fq.service.AuthService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

/**
 * 功能权限设置
 * @author P
 * @date d2015-4-27
 */
@Controller
public class ProgramController extends BaseController {

	private AuthService service;

	@Autowired
	public void setService(AuthService service) {
		this.service = service;
	}


	/**
	 * 列出所有功能组
	 * @param page
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="programTeam")
	@LoginAuth
	public ModelAndView programTeam (PageInfo page) throws Exception{
		List<Map<String, String>> programs = service.getAllProgramTeamByPage(page);
		ModelAndView mv = new ModelAndView ("auth/programlist") ;
		mv.addObject("data", programs);
		mv.addObject("items", service.getAllProgramItem());
		mv.addObject("page", page);
		return mv;
	}
	/**
	 * 功能组添加
	 * @return
	 * @throws Exception
	 */
	@LoginAuth
	@RequestMapping(value="programTeamAdd",method=RequestMethod.POST)
	@ResponseBody
	public String programTeamAdd()throws Exception{
		FormData formData = this.getFormData();
		String name = formData.getString("name");
		service.insertProgramTeam(name);
		return "1";
	}
	/**
	 * 功能组修改
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="programTeamUp")
	@LoginAuth
	@ResponseBody
	public String programTeamUp ()throws Exception{
		FormData formData = this.getFormData();
		
		String name = formData.getString("name");
		formData.put("name", name);
		service.updateProgramTeamById(formData);
		return "1";
	}
	@RequestMapping(value="programItemEidt")
	@LoginAuth
	@ResponseBody
	public String programItemEidt ()throws Exception{
		FormData formData = this.getFormData();
		String name = formData.getString("name");
		formData.put("name", name);
		if(formData.get("id")!= null && !formData.getString("id").equals("")){
			service.updateProgramItemById(formData);
		}else{
			service.insertProgramItemByTeamId(formData);
		}
		return "1";
	}
	@RequestMapping(value="programDetailList")
	@LoginAuth
	public ModelAndView programDetailList ()throws Exception{
		ModelAndView mv = new ModelAndView ("auth/programdetaillist");
		mv.addObject("id", this.getFormData().getString("id"));
		return mv;
	}
	@RequestMapping(value="programDetailSub")
	@ResponseBody
	@LoginAuth
	public String programDetailSub ()throws Exception{
		FormData formData = this.getFormData();
		formData.put("detail_name",  formData.getString("detail_name"));
		service.insertProgramDetailByItemId(formData);
		return "1";
	}
	@RequestMapping(value="programDetailListAjax")
	@LoginAuth
	@ResponseBody
	public List<Map<String, String>> programDetailListAjax (String id)throws Exception{
		List<Map<String, String>> details = service.getALLDetailByItemId(this.getFormData());
		return details;
	}
	@RequestMapping(value="programDetailEdit")
	@ResponseBody
	@LoginAuth
	public String programDetailEdit ()throws Exception{
		FormData formData = this.getFormData();
		formData.put("detail_name", formData.getString("detail_name"));
		formData.put("detail_url", formData.getString("detail_url") );
		service.updateDetailById(formData);
		return "1";
	}
	@ResponseBody
	@RequestMapping(value="deleteDetail")
	@LoginAuth
	public String deleteDetail ()throws Exception {
		service.deleteDetailById(this.getFormData());
		return "1";
	}
}
