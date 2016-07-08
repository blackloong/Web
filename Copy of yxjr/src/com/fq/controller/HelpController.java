package com.fq.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.LoginAuth;
import com.fq.controller.base.BaseController;
import com.fq.service.HelpService;
import com.fq.service.VersionService;
import com.fq.util.Const;
import com.fq.util.FormData;
import com.fq.util.PageInfo;
import com.fq.util.cookies.CookiesUtil;
import com.fq.util.file.FileUtil;


/**
 * 
 * 帮助中心   wma 0703 
 *
 */
@Controller
public class HelpController extends BaseController{
	@Autowired
	private HelpService  helpEditSub;

	@Autowired
	private VersionService versionService;
	
	@RequestMapping(value="helplist")
	@LoginAuth
	public ModelAndView productlist (PageInfo page,String id)throws Exception{
		
		ModelAndView mv=new ModelAndView();
		 mv = new ModelAndView ("help/helplist");
		FormData formData = this.getFormData();
		page.setFormData(formData);
		mv.addObject("data", helpEditSub.getAllProductByPage(page));
		mv.addObject("page", page);
		mv.addObject("type", id);
 
		List<Map<String, String>> brandList = helpEditSub.brandList(this.getFormData());
		mv.addObject("brandList", brandList);


		return mv;
	}
	@RequestMapping(value="helpEdit")
	@LoginAuth
	public ModelAndView productEdit ()throws Exception {
		
		Map<String, Object> product = helpEditSub.getProductById(this.getFormData());
		ModelAndView mv = new ModelAndView ("help/helpedit");
		mv.addObject("data", product);
		return mv;
	}

	
	@RequestMapping(value="helpEditSub")
	@LoginAuth
	public ModelAndView productEditSub (MultipartFile productimg,HttpServletRequest request)throws Exception {
		ModelAndView mv = new ModelAndView ("redirect:helplist");
		FormData formData = new FormData(request);
		if(productimg != null ){
			String productimgSrc = FileUtil.fileUpload(productimg, this.getRequest(), "upload");
			formData.put("imgsource", productimgSrc);
		}
		formData.put("uid", CookiesUtil.getCookie(getRequest(),Const.LOGIN_USER));
		if(formData.getString("id") == null || formData.getString("id").trim().length()<=0){
			helpEditSub.saveProduct(formData);
		}else{
			helpEditSub.updateProductById(formData);
		}
		return mv;
		
	}
	
	@RequestMapping(value="delhelp")
	@LoginAuth
	public ModelAndView delbrand(HttpServletRequest request)throws Exception {
		ModelAndView mv = new ModelAndView ("redirect:helplist");
		FormData formData = new FormData(request);
		helpEditSub.deleteProductById(formData);
		return mv;
		
	}
	
	
   /**
    * 意见返回列表 wma 20150908
    * @param page
    * @param type
    * @return
    * @throws Exception
    */
	@RequestMapping(value="feedbackList")
	@LoginAuth
	public ModelAndView feedbackList ()throws Exception{
		ModelAndView mv=new ModelAndView();
		 mv = new ModelAndView ("help/feedbackList");   
		List<Map<String, Object>> brandList = helpEditSub.feedbackList(this.getFormData());
		mv.addObject("data", brandList);


		return mv;
	}
	

}
