package com.fq.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.LoginAuth;
import com.fq.controller.base.BaseController;
import com.fq.service.UserService;
import com.fq.util.FormData;
import com.fq.util.InterestUtil;
import com.fq.util.PageInfo;
import com.fq.util.file.FileUtil;

/**
 * 用户管理
 * 
 * @author P
 * @date d2015-4-28
 */
@Controller
public class UserController extends BaseController {

	private UserService service;

	public UserService getService() {
		return service;
	}

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	
	@LoginAuth
	@RequestMapping(value = "userlist")
	public String index(PageInfo page, Model model, HttpServletResponse response)
			throws Exception {
		if (page == null)
			page = new PageInfo();
		FormData pd = this.getFormData();
		page.setFormData(pd);
		List<FormData> data = service.getAllPage(page);
		model.addAttribute("pageData", data);
		model.addAttribute("page", page);
		model.addAttribute("keyWord", pd!=null&&pd.get("keyWord")!=null?pd.get("keyWord"):"");
		return "user/userlist";
	}

	@LoginAuth
	@RequestMapping(value = "editUser")
	public ModelAndView editUser() throws Exception {
		FormData page = this.getFormData();
		String id = page.getString("id");
		ModelAndView mv = new ModelAndView("user/edituser");
		if (id != null && !id.trim().equals("")) {
			mv.addObject("data", service.getUserById(id));
		}
		return mv;
	}

	@LoginAuth
	@RequestMapping(value = "saveUser")
	public String saveUser() throws Exception {
		FormData formData = this.getFormData();
		String id = formData.getString("id");
		if (id == null || id.trim().length() <= 0) {
			if(formData.get("roleid").equals("2")){
				formData.put("password", "888888");//设置默认密码 

			}
			else{
				formData.put("password", "888888");//设置默认密码 
			}

			
			service.saveUser(formData);
			 
		} else {
			service.updateUser(formData);
		}
		return "redirect:userlist";
	}

	@LoginAuth
	@RequestMapping(value = "delUser")
	@ResponseBody
	public String delUser() throws Exception {
		FormData form = this.getFormData();
		List<Integer> ids = new ArrayList<Integer>();
		String[] id = form.get("ids").toString().split(",");
		for (String s : id) {
			ids.add(Integer.parseInt(s));
		}
		service.delUser(ids);
		return "1";
	}
	
	
	@RequestMapping(value = "ismobile")
	@ResponseBody
	@LoginAuth
	public Map<String, Object> ismobile(){
		 Map<String, Object> map=new HashMap<String, Object>();
		 try {
			 FormData form = this.getFormData();
			 List<FormData> list=service.checkMobile(form);
				if(list!=null && list.size()>0){//该手机号存在
				map.put("boo", false);
				}else{
					map.put("boo", true);
				}
		} catch (Exception e) {
			// TODO: handle exception
			map.put("boo", false);
		}
		 return map;
	}

	@RequestMapping(value = "userImgUpload")
	@ResponseBody
	@LoginAuth
	public String fileupload(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> files = multipartHttpServletRequest
				.getFileMap();
		Map<String, String> res = new HashMap<String, String>();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		String fileName = "";
		for (String key : files.keySet()) {
			MultipartFile file = multipartHttpServletRequest.getFile(key);
			fileName = FileUtil.fileUpload(file, multipartHttpServletRequest,
					"upload");
			res.put("file", basePath + fileName);
		}
		String id = request.getParameter("id");
		service.updateUserImg(id, fileName);
		return fileName;
	}
	@RequestMapping(value="selectUser")
	public ModelAndView  selectUser (PageInfo page) throws Exception{
		page.setFormData(getFormData());
		ModelAndView mv = new ModelAndView("user/selectuser");
		mv.addObject("data",service.getSelectUser(page));
		mv.addObject("formData", getFormData());
		mv.addObject("url", page.getFormData().get("url"));
		return mv;
	}
	/***
	 * 还款计算器
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "public_product_index")
	public String public_product_index(Model model) throws Exception {
		 
		return "message/hdjs";
	}
	
	@RequestMapping(value = "public_product_index_lx")
	@ResponseBody
	public Map<String, Object> public_product_index_lx(Model model) throws Exception {
		FormData fd = this.getFormData();
	      String sqje=  fd.getString("sqje");
	      String jkqx=	fd.getString("jkqx");
	      String jkll=	fd.getString("jkll");
	      String hkfs=	fd.getString("hkfs");
		List<Map<String, Object>> data = InterestUtil.getInterest(sqje, jkqx, jkll, hkfs, new Date());
		Map<String, Object> map=InterestUtil.getInterestNum(sqje, jkqx, jkll, hkfs);
		map.put("list", data);
		return map;
	}

	
	@LoginAuth
	@RequestMapping(value="amap")
	public String amap (Model model,HttpServletRequest request ) throws Exception{
		return "user/index";
	}
	@ResponseBody
	@RequestMapping(value="getLng")
	public List<FormData> getLng ()throws Exception {
		FormData form = this.getFormData();
		return service.getAllEmp(form);
 	}
	
	
	
	/**
	 * 
	 * 用户数据下载
	 * */
	//@LoginAuth
	@RequestMapping(value = "userlistdown")
	public String index(Model model, HttpServletResponse response)
			throws Exception {
		FormData pd = this.getFormData();
		List<Map<String, Object>> data = service.getAllowDown(pd);
		model.addAttribute("pageData", data);
 		return "user/userlist1";
	}

	
	
}
