package com.fq.controller.yxjr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fq.annotations.LoginAuth;
import com.fq.controller.base.BaseController;
import com.fq.controller.yxjr.service.ProdouctService;
import com.fq.service.BannerService;
import com.fq.service.DictionaryService;
import com.fq.util.Const;
import com.fq.util.FormData;
import com.fq.util.PageInfo;
import com.fq.util.UploadUitl;

@Controller
public class ProdouctController extends BaseController {

	@Autowired
	ProdouctService server;
	@Autowired
	DictionaryService typeservice;
	@Autowired
	 BannerService bannerService;

	@RequestMapping(value = "prodouct_list")
	@LoginAuth
	public String prodouct_list(PageInfo page, Model model,
			HttpServletResponse response) throws Exception {
		if (page == null)
			page = new PageInfo();
		FormData pd = this.getFormData();
		page.setFormData(pd);
		List<Map<String, Object>> data = server.findProdouctPage(page);
		model.addAttribute("pageData", data);
		model.addAttribute("page", page);
		return "prodouct/list";
	}

	@RequestMapping(value = "prodouct_edit")
	@LoginAuth
	public ModelAndView prodouct_edit(PageInfo page) throws Exception {
		FormData pd = this.getFormData();
		String id = pd.getString("id");
		ModelAndView mv = new ModelAndView("prodouct/edit");
		pd.put("pid", 0);
	    page.setFormData(pd);	
		List<FormData> data = typeservice.findtypePage(page);
		mv.addObject("datauser", data);
		
		if (id != null && !"".equals(id)) {
			mv.addObject("data", server.findProdouctId(pd));
			
		}
		return mv;
	}

	@RequestMapping(value = "prodouct_save")
	@LoginAuth
	public String product_save(@RequestParam("id") String id,
			@RequestParam("title") String title,
			@RequestParam("procode") String procode,
			@RequestParam("pro_rate") String pro_rate,
			@RequestParam("pro_cycle") String pro_cycle,
			@RequestParam("pro_logo") MultipartFile pro_logo,
			@RequestParam("pro_max_amount") String pro_max_amount,
			@RequestParam("pro_min_amount") String pro_min_amount,
			@RequestParam("sort") String sort,
			@RequestParam("description") String description,
 			@RequestParam("ptype1") String ptype1,
			@RequestParam("ptype2") String ptype2,
			//@RequestParam("ptype3") String ptype3
			HttpServletRequest request
			) throws Exception {
		FormData pd = this.getFormData();
		pd.put("id", id);
		pd.put("title", title);
		pd.put("procode", procode);
		pd.put("pro_rate", pro_rate);
		pd.put("pro_cycle", pro_cycle);
		pd.put("pro_max_amount", pro_max_amount);
		pd.put("pro_min_amount", pro_min_amount);
		pd.put("description", description);
		pd.put("sort", sort);
		pd.put("ptype1", ptype1);
		pd.put("ptype2", ptype2);
		pd.put("ptype3", 0);

		
		if (null != pro_logo && !pro_logo.isEmpty()) {
			String img="";
			try {
				img = UploadUitl.upload(pro_logo, request, Const.PROIMAGEPATH,true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pd.put("pro_logo", img);
			pd.put("pro_logo_value", img);
			 
		} 
		
	/*	if (pro_logo != null && pro_logo.getBytes() != null
				&& pro_logo.getBytes().length > 0) {
			pd.put("pro_logo", pro_logo.getBytes());
			pd.put("pro_logo_value", pro_logo.getOriginalFilename());
		}*/

		if (id != null && !"".equals(id)) {
			server.updateProdouct(pd);
		} else {
			pd.put("status", "0");
			pd.put("joinnum", "0");
			server.insertProdouct(pd);
		}
		return "redirect:prodouct_list";
	}

	@RequestMapping(value = "prodouct_delete")
	@ResponseBody
	@LoginAuth
	public Map<String, Object> product_delete(Model model,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FormData form = this.getFormData();

			server.deleteProdouctIds(form);
			map.put("boo", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("boo", false);
		}
		return map;

	}

	// proid
	@RequestMapping(value = "fieldshow")
	public String fieldshow(Model model, HttpServletResponse response)
			throws Exception {
		FormData pd = this.getFormData();
		model.addAttribute("proid", pd.get("proid"));
		model.addAttribute("retype", pd.getString("retype"));
		return "prodouct/field";
	}

	@RequestMapping(value = "fieldlist")
	@ResponseBody
	public List<Map<String, Object>> fieldlist(Model model,
			HttpServletResponse response) throws Exception {
		FormData pd = this.getFormData();
		List<Map<String, Object>> list = server.findField(pd);
		return list;
	}

	@RequestMapping(value = "savefield")
	public String savefield(HttpServletRequest request) throws Exception {
		FormData form = new FormData(request);
		try {
			
			String fieldtype = form.getString("fieldtype");
			if ("text".equals(fieldtype)) {
				form.put("datas", null);
				form.put("value_text", null);
				form.put("value_blod", null);
				form.put("value_blod_name", null);
			} else if ("textarea".equals(fieldtype)) {
				form.put("datas", null);
				form.put("value", null);
				form.put("value_blod", null);
				form.put("value_blod_name", null);
			} else if ("select".equals(fieldtype)) {
				form.put("value_text", null);
				form.put("value_blod", null);
				form.put("value_blod_name", null);
			} else if ("radio".equals(fieldtype)) {
				form.put("value_text", null);
				form.put("value_blod", null);
				form.put("value_blod_name", null);
			} else if ("file".equals(fieldtype)) {
				
				
				//Map<String, Object> map=new FormData(request);
				
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				Map<String, MultipartFile> fieldvalue = multipartRequest
						.getFileMap();
				if (fieldvalue != null && fieldvalue.get("value_blod") != null) {
					MultipartFile file = fieldvalue.get("value_blod");
					if (file != null && file.getBytes() != null
							&& file.getBytes().length > 0) {
						form.put("value_blod", file.getBytes());
						form.put("value_blod_name", file.getOriginalFilename());
						form.put("datas", null);
						form.put("value_text", null);
						form.put("value", null);
					}
				}
			}
			if(form.get("id")!=null&&!"".equals(form.get("id"))){
				server.updateField(form);
			}else{
				server.insertField(form);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "redirect:fieldshow?proid="+form.getString("proid")+"&retype="+form.getString("retype");
	}

	
	@RequestMapping(value = "delfield")
	@ResponseBody
	public 	Map<String, Object> delfield(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FormData form = this.getFormData();
			server.deleteField(form);
			map.put("boo", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("boo", false);
		}
		return map;
	}

	@RequestMapping(value = "prodouct_fbxj")
	@ResponseBody
	public 	Map<String, Object> prodouctFbxj(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FormData form = this.getFormData();
			server.prodouctFbXj(form);
			map.put("boo", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("boo", false);
		}
		return map;
	}
	
	@RequestMapping(value = "prodouct_info")
	public 	ModelAndView prodouct_info(HttpServletRequest request) throws Exception {
		
		FormData pd = this.getFormData();
		String id = pd.getString("id");
		ModelAndView mv = new ModelAndView("prodouct/info");
		if (id != null && !"".equals(id)) {
			mv.addObject("data", server.findProdouctId(pd));
			pd.put("retype", 1);
			pd.put("proid", id);
			List<Map<String, Object>> dataList = server.findField(pd);
			mv.addObject("dataList", dataList);


		}
		return mv;
	}
	
	/***
	 * 进度查询 wma   
	 * uid 用户
	 */
	@RequestMapping(value="sqjdList")
	@ResponseBody
	public ModelAndView sqjdList() throws Exception{
 		FormData formData=this.getFormData();
		ModelAndView mv = new ModelAndView ("prodouct/sqjdList");
		List<Map<String, Object>> data=bannerService.sqList(formData);
		mv.addObject("data", data);
		return mv;

	}
	
	
	/***
	 * 进度详情
	 * sq_code 申请号 
	 */
	@RequestMapping(value="sqjdDesList")
	@ResponseBody
	public  ModelAndView sqjdDesList(String loan_name) throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
		ModelAndView mv = new ModelAndView ("prodouct/sqjdDesList");
 		List<Map<String, Object>> data=bannerService.sqDesList(formData);
		mv.addObject("data", data);
		mv.addObject("loan_name", loan_name);

		return  mv;
	}
	
	/***
	 * 2 ，3 级分类
	 * @param model
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "prodouctTypelist")
	@ResponseBody
 	public Map<String, Object> prodouctTypelist(Model model,
			HttpServletResponse response,PageInfo page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			List<Map<String, String>> data=new ArrayList<Map<String,String>>();
			FormData pd = this.getFormData();
			if(pd.get("id").equals("1")){
				
			}
			
			else{
				data = typeservice.findtypePid(pd);

			}
			
			
			System.out.println("");
			if(data.size()>0){
				map.put("data", data);
				map.put("code","00");
				map.put("msg","成功");

			}
			else{
				map.put("data", data);
				map.put("code","10");
				map.put("msg","暂无信息");
			}
		} catch (Exception e) {
 			e.printStackTrace();
 		}
		return map;

	}
	
	
	/***
	 * 一级分类
	 * @param model
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "Typelist")
	@ResponseBody
 	public Map<String, Object> Typelist(Model model,
			HttpServletResponse response,PageInfo page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FormData pd = this.getFormData();
			pd.put("pid", 0);
		    page.setFormData(pd);	
			List<FormData> data = typeservice.findtypePage(page);
			if(data.size()>0){
				map.put("data", data);
				map.put("code","00");
				map.put("msg","成功");

			}
			else{
				map.put("data", data);
				map.put("code","10");
				map.put("msg","暂无信息");
			}
		} catch (Exception e) {
 			e.printStackTrace();
 		}
		return map;

	}
	
	
	/***
	 * 分类下 商品 
	 *  参数 id 分类id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "interface/prodouctlist")
	@ResponseBody
 	public Map<String, Object> prodouctlist(Model model,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FormData pd = this.getFormData();
			List<Map<String, Object>> data = server.prodouctlist(pd);
			if(data.size()>0){
				map.put("data", data);
				map.put("code","00");
				map.put("msg","成功");

			}
			else{
				map.put("data", data);
				map.put("code","10");
				map.put("msg","暂无信息");
			}
		} catch (Exception e) {
 			e.printStackTrace();
 		}
		return map;

	}
	
	
	/***
	 *产品 详情  产品 id  
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "interface/prodeslist")
	@ResponseBody
 	public Map<String, Object> prodeslist(Model model,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FormData pd = this.getFormData();
			pd.put("proid",pd.get("id"));
			pd.put("retype", 1);
			Map<String, Object> pro = server.findProdouctId(pd);
		
			List<Map<String, Object>> data = server.findField(pd);
 
			if(pro!=null){
				map.put("pro", pro);//产品 详情 
				map.put("data", data);//产品扩展字段
				
				map.put("code","00");
				map.put("msg","成功");

			}
			else{
				map.put("pro", pro);//产品 详情 
				map.put("data", data);
				map.put("code","10");
				map.put("msg","暂无信息");
			}
		} catch (Exception e) {
 			e.printStackTrace();
 		}
		return map;

	}
	
	
	/***
	 * 申请数据下载 wma20160118  
	 * uid 用户
	 */
	@RequestMapping(value="sqjdListdown")
	@ResponseBody
	public ModelAndView sqjdListdown() throws Exception{
 		FormData formData=this.getFormData();
		ModelAndView mv = new ModelAndView ("prodouct/sqjdList1");
		List<Map<String, Object>> data=bannerService.sqList(formData);
		mv.addObject("data", data);
		return mv;

	}
	
	
	/***
	 * 授信申请列表
	 * uid 用户
	 */
	@RequestMapping(value="pcreditList")
	@ResponseBody
	public ModelAndView pcreditList() throws Exception{
 		FormData formData=this.getFormData();
		ModelAndView mv = new ModelAndView ("prodouct/pcreditList");
		List<Map<String, Object>> data=bannerService.creditList(formData);
		mv.addObject("data", data);
		return mv;

	}
	
	
	/***
	 * 在线订单合同 pdf 预览
	 */
	@RequestMapping(value="pdfList")
	@ResponseBody
	public ModelAndView pdfList(String number) throws Exception{
 		FormData formData=this.getFormData();
		ModelAndView mv = new ModelAndView ("prodouct/pdfList");
		List<Map<String, Object>> data=bannerService.pdfList(formData);
		mv.addObject("data", data);
		mv.addObject("number", number);
		return mv;

	}
	
	
	 
}
