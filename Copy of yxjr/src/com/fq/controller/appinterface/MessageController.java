package com.fq.controller.appinterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fq.annotations.InterfaceValidate;
import com.fq.entity.Message;
import com.fq.service.HelpService;
import com.fq.service.VersionService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

/***
 * 通知/个人消息 接口
 * @author Admincx
 *
 */
@Controller
@RequestMapping(value="interface")
public class MessageController extends ResponseBaseController {
 
	@Autowired
	private HelpService  helpEditSub;
	@Autowired
	private VersionService versionService;

	@RequestMapping(value="messageList")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"currentPage"})
	public Map<String, Object> BannerList(int currentPage) throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
 
		  PageInfo page=new PageInfo();
		if (page == null)
			page = new PageInfo();
		FormData pd = this.getFormData();
		page.setFormData(pd);
		page.setCurrentPage(currentPage);
		List<FormData> data = helpEditSub.getMessageAllPage(page);
		if(data.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
			res.put("date", data);
		}else{
			res.put("code", "10");
			res.put("msg", "暂无消息");
		}
		return  res;
	}
	
 
	
	@RequestMapping(value="messageupdate")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"id"})
	public Map<String, Object> messageupdate(Message me) throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		 int  i=versionService.saveMeorUpdate(me);
		if(i>0){
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "更新失败");
		}
		return  res;
	}
	
	
	@RequestMapping(value="helpList")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"type"})

 	public Map<String, Object> helpList() throws Exception{
 
		Map<String, Object> res=new HashMap<String, Object>();
  		List<Map<String, String>> brandList = helpEditSub.brandList(this.getFormData());
		 
 		if(brandList.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
			res.put("date", brandList);
		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}
	
	@RequestMapping(value="helpbyid")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"id"})

 	public Map<String, Object> helpbyid(String id) throws Exception{
		  PageInfo page=new PageInfo();

		Map<String, Object> res=new HashMap<String, Object>();
 		FormData formData = this.getFormData();
		page.setFormData(formData);
		Map<String, Object> product = helpEditSub.getProductById(this.getFormData());
		 
 		if(product.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
			res.put("date", product);
		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}


    /***
     * 未读消息
     * @param uid
     * @param type
     * @param currentPage
     * @return
     * @throws Exception
     */
	@RequestMapping(value="messageCount")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"uid"})
	public Map<String, Object> messageCount(String uid) throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();

		FormData formData = this.getFormData();
		Map<String, Object> count=helpEditSub.messageCount(formData);
	
		if(count!=null){
			res.put("code", "00");
			res.put("msg", "成功");
			res.put("data", count.get("count"));
		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}
	
	@RequestMapping(value="veisonList")
	@ResponseBody
 	public Map<String, Object> veisonList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
 
		  PageInfo page=new PageInfo();
		if (page == null)
			page = new PageInfo();
		FormData pd = this.getFormData();
 
		page.setFormData(pd);
		page.setCurrentPage(1);
		 	List<FormData> data = versionService.getAllPage(page);
		if(data.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
			res.put("date", data.get(0));
		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}
	

}
