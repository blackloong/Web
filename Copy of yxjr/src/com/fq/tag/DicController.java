package com.fq.tag;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fq.controller.base.BaseController;
import com.fq.util.FormData;
import com.fq.util.PageInfo;


@Controller
public class DicController  extends BaseController{

	
	
	@RequestMapping(value = "dicloadSelect")
	public String index(PageInfo page, Model model, HttpServletResponse response)
			throws Exception {
		FormData pd = this.getFormData();
	    String message=MyFunctions.loadSelect(pd.getString("tablename"),pd.getString("key"), pd.getString("text"),pd.getString("where"),pd.getString("id"));
		this.resposeWrite(message, response);
		return null;
	}
	
}
