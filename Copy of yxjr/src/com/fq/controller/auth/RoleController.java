package com.fq.controller.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.LoginAuth;
import com.fq.controller.base.BaseController;
import com.fq.service.RoleService;

/**
 * 角色controller
 * @author P
 * @date d2015-4-27
 */
@Controller
public class RoleController extends BaseController {

	private RoleService service;

	public RoleService getService() {
		return service;
	}


	@Autowired
	public void setService(RoleService service) {
		this.service = service;
	}

	@RequestMapping(value="rollList")
	@LoginAuth
	public  ModelAndView getAllRole () throws Exception{
		ModelAndView mv = new ModelAndView ("auth/rolelist");
		mv.addObject("data", service.getAllRole());
		return mv;
	}
	@RequestMapping(value="addRole")
	@ResponseBody
	@LoginAuth
	 public String addRole ()throws Exception{
		service.insertRole(this.getFormData());
		return "1";
	}
	@RequestMapping(value="editRole")
	@ResponseBody
	@LoginAuth
	 public String editRole ()throws Exception{
		service.updateRole(this.getFormData());
		return "1";
	}
	@RequestMapping(value="delRoleByIds")
	@ResponseBody
	@LoginAuth
	public String deleteRoleByIds()throws Exception {
		service.deleteRoleByIds(this.getFormData());
		return "1";
	}
	/**
	 *  显示所有功能
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="programShow")
	@LoginAuth
	public ModelAndView programShow (String roleId )throws Exception {
		ModelAndView mv = new ModelAndView ("auth/programShow");
		Map<String, Object> program = service.showAllProgram(this.getFormData());
		System.out.println(program);
		mv.addObject("program", program);
		mv.addObject("roleId", roleId);
		return mv;
	}	
	/**
	 * 根据detailid 修改角色权限
	 * @return
	 */
	@RequestMapping(value="editeRoleAuth")
	@ResponseBody
	@LoginAuth
	public String editeRoleAuth ()throws Exception{
		service.deleteRoleAuthByRoleId(this.getFormData());
		service.insertRoleAuthByRoleId(this.getFormData());
		return "1";
	}
	
	
}
