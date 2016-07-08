package com.fq.controller.appinterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fq.controller.Weixin;
import com.fq.entity.Message;
import com.fq.form.Yxjrform;
import com.fq.service.BannerService;
import com.fq.service.DictionaryService;
import com.fq.service.UserService;
import com.fq.service.VersionService;
import com.fq.util.ApiQuery;
import com.fq.util.DateUtil;
import com.fq.util.FileUtil;
import com.fq.util.FormData;
import com.fq.util.PassWordUtil;
import com.fq.util.soursinterface;
import com.jianzhou.sdk.BusinessService;

/***
 * 首页滚动图
 * @author Admincx
 *
 */
@Controller
@RequestMapping(value="interface")
public class BannerAppController extends ResponseBaseController {
	private BannerService bannerService;

	public BannerService getBannerService() {
		return bannerService;
	}
	@Autowired
	public void setBannerService(BannerService bannerService) {
		this.bannerService = bannerService;
	}
	 
	@Autowired
	private VersionService versionService;
	
	@Autowired
	private DictionaryService dicservice;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="BannerList")
	@ResponseBody
	public Map<String, Object> BannerList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		List<Map<String, String>> Banners=bannerService.BannerList();
		if(Banners.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
			res.put("Banners", Banners);
		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}
	
	/***
	 * 首页常数统计 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "indexCount")
	@ResponseBody
	public Map<String, Object> indexCount() throws Exception{
		
		Map<String, Object> res=new HashMap<String, Object>();
		
		FormData page = this.getFormData();
		String id ="71";//咨询基数
		String id2="72";//贷款基数
		
		  page.put("id", id);
		  Map<String, Object> zxCount= dicservice.findDictionaryId(page);
	    
	     FormData page1 = this.getFormData();
		  page1.put("id", id2);
		  Map<String, Object> dkCount=dicservice.findDictionaryId(page1);
		  
		  Map<String, Object> dkCounts=bannerService.indexCount(page);
		  if(dkCounts!=null){
			  if(dkCounts.get("zxcount")!=null){
				  int zxcount=Integer.parseInt(zxCount.get("py").toString())+Integer.parseInt(dkCounts.get("zxcount").toString());
				  res.put("zxcount", zxcount);
				  
			  }
			  
                if(dkCounts.get("dkcount")!=null){
				  
                	  int zxcount=Integer.parseInt(dkCount.get("py").toString())+Integer.parseInt(dkCounts.get("dkcount").toString());
    				  res.put("dkcount", zxcount);
			  }
			  
            	res.put("code", "00");
    			res.put("msg", "成功");
		  }
		  else{
			  
				res.put("code", "10");
				res.put("msg", "失败");
		  }

	    
	    return res;
		 
	 }
		 
	 
	
	
	/***
	 * 更改申请编号  wma 20151125
	 */
	@RequestMapping(value="squpdataSqcode")
	@ResponseBody
	public Map<String, Object> squpdataSqcode() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
		formData.put("sq_date", new Date());//更新审核时间
 
		formData.put("state", "申请中");
		int Banners=bannerService.squpdataSqcode(formData);
		
		if(Banners>0){
	
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}
	
	
	/***
	 * 更改审核状态  wma 20151118
	 */
	@RequestMapping(value="squpdatastate")
	@ResponseBody
	public Map<String, Object> squpdatastate() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
		formData.put("sq_date", new Date());//更新审核时间

		Map<String, Object> states=bannerService.StateList(formData);//状态备注
		if(states!=null){
			formData.put("remark", states.get("content"));
		}
		int Banners=bannerService.squpdatastate(formData);
		
		if(Banners>0){
			Map<String, Object> sqlist=bannerService.sqcodeList(formData);//查询申请信息
			 if(sqlist!=null){
				 sqlist.put("id", "");
   				int sqICopy=bannerService.sqInsertCopys(sqlist);//添加审核流水
   				
   			//消息记录
   				Message message=new Message();

   				message.setUserid(sqlist.get("uid").toString());
   				
   				if(formData.get("state").equals("签约中")){
   					
   					formData.put("state", "签约通知");
   				    Map<String, Object> stateContent=bannerService.StateList(formData);//状态备注
   	   				//message.setContent(stateContent.get("content").toString());
   	   				message.setContent("您的订单已通过审核，请及时联系您的客户经理并到门店签约【翼勋金融】");

   	   			    message.setTitle("签约通知");
	   	   			message.setIsdeleted("2");
	   				message.setIsviewed("2");
	   				message.setMessagetype("签约通知");
					message.setPushdate(DateUtil.getTime());
	   				message.setPushtype("短信");
	   				
	   				Message message1=message;
	   				
	   				versionService.saveMeorUpdate(message);//增加消息记
	   				
	   			// String Content="尊敬的用户，您的验证码为"+ i +"，如有疑问，可拨打客服电话 "+Const.hotline;
	   				BusinessService bs = new BusinessService();
	   				bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");
	   				System.out.println("短信输出:" + bs.sendBatchMessage("sdk_yixun", "20150724", sqlist.get("telephone").toString(), message.getContent()));

	   		
	   				message1.setPushtype("app推送");//app推送
	   				versionService.saveMeorUpdate(message1);//增加消息记
	
	   			    
   				}
   				else if(formData.get("state").equals("放款完成")){
   					
   					formData.put("state", "放款通知");
   				    Map<String, Object> stateContent=bannerService.StateList(formData);//状态备注
   				    
   	   				//message.setContent(stateContent.get("content").toString()+"【翼勋金融】");
   				   //  您已成功通过翼勋XX贷产品申请借款XXXXX元，您的首次还款日期是XX月XX日、还款金额XXXX元，请您留意、祝您用款愉快
   				    String content="您已成功通过翼勋"+sqlist.get("pro_name")+"产品申请借款"+sqlist.get("sp_amount")+"元,请您留意、祝您用款愉快。【翼勋金融】";
   	   				message.setContent(content);
   	   			    message.setTitle("放款通知");
	   	   			message.setIsdeleted("2");
	   				message.setIsviewed("2");
	   				message.setMessagetype("放款通知");
					message.setPushdate(DateUtil.getTime());
	   				message.setPushtype("短信");
	   				
	   				Message message1=message;
	   				
	   				versionService.saveMeorUpdate(message);//增加消息记
	   				
	   			  // String Content="尊敬的用户，您的验证码为"+ i +"，如有疑问，可拨打客服电话 "+Const.hotline;
	   				BusinessService bs = new BusinessService();
	   				bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");
	   				System.out.println("短信输出:" + bs.sendBatchMessage("sdk_yixun", "20150724", sqlist.get("telephone").toString(), message.getContent()));
	   				
	   				message1.setPushtype("app推送");//app推送
	   				versionService.saveMeorUpdate(message1);//增加消息记
	
	   			    
	
   				}
   				
			 }
	
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}
	
	/***
	 * 申请回调 地址  wma 20151118
	 */

	@RequestMapping(value="sqInsert")
	@ResponseBody
	public Map<String, Object> sqInsert() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
		formData.put("state", "申请中");
	    Map<String, Object> states=bannerService.StateList(formData);//状态备注
		if(states!=null){
			formData.put("remark", states.get("content"));
		}
		
		int Banners=bannerService.sqInsert(formData);
		if(Banners>0){
			//添加申请流水
			int sqICopy=bannerService.sqInsertCopy(formData);
			
			if(formData.get("state").equals("申请中")){
				formData.put("state", "预约成功通知");
				 Map<String, Object> stateContent=bannerService.StateList(formData);//状态备注
				//消息记录
					Message message=new Message();
					message.setUserid(formData.get("uid").toString());
					
					//message.setContent("预约成功，请联系客服热线 400-920-1313 办理相关相关业务【翼勋金融】");
					 message.setContent("预约成功!温馨提醒：如有疑问，欢迎拨打客服热线，400-920-1313【翼勋金融】");

					message.setTitle("预约成功通知");
					message.setIsdeleted("2");
					message.setIsviewed("2");
					message.setMessagetype("预约成功通知");
					message.setPushdate(DateUtil.getTime());
					message.setPushtype("短信");
					
					Message message1=message;
					versionService.saveMeorUpdate(message);//增加消息记录
					
					BusinessService bs = new BusinessService();
					bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");
					System.out.println("短信输出:" + bs.sendBatchMessage("sdk_yixun", "20150724", formData.get("telephone").toString(), message.getContent()));
					
					message1.setPushtype("app推送");//app推送
					versionService.saveMeorUpdate(message1);//增加消息记录

				      // String Content="尊敬的用户，您的验证码为"+ i +"，如有疑问，可拨打客服电话 "+Const.hotline;
		 
				}

			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}

	
	/***
	 * 进度查询 wma   
	 * uid 用户
	 */
	@RequestMapping(value="sqjdList")
	@ResponseBody
	public Map<String, Object> sqjdList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
		List<Map<String, Object>> data=bannerService.sqList(formData);
		res.put("data", data);
		if(data.size()>0){	
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "暂无信息");
		}
		return  res;
	}
	
	
	/***
	 * 进度详情
	 * sq_code 申请号 
	 */
	@RequestMapping(value="sqjdDesList")
	@ResponseBody
	public Map<String, Object> sqjdDesList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
 		List<Map<String, Object>> data=bannerService.sqDesList(formData);
		res.put("data", data);

		if(data.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "暂无信息");
		}
		return  res;
	}
	
	/***
	 * 翼勋消息 添加  wma20151203
	 * mobile ,title,content,messagetype
	 * 
	 */
	@RequestMapping(value="yxmessage")
	@ResponseBody
	public Map<String, Object> yxmessage() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();

		List<FormData> checkMobile=userService.checkMobile(formData);//状态备注
		
		if(checkMobile.size()>0){
   				
   			//消息记录
   				Message message=new Message();

    			JSONObject js=JSONObject.fromObject(checkMobile.get(0))	;
   				String userId=js.getString("id");
   				
   				System.out.println(userId);
   				message.setUserid(userId);
   				
    			if(formData.get("messagetype").equals("还款提醒")){
    				
   	   				message.setContent(formData.get("content")+"【翼勋金融】");

   	   			    message.setTitle(formData.get("title").toString());
	   	   			message.setIsdeleted("2");
	   				message.setIsviewed("2");
	   				message.setMessagetype("还款提醒");
					message.setPushdate(DateUtil.getTime());
	   				message.setPushtype("短信");
	   				
	   				Message message1=message;
	   				
	   				versionService.saveMeorUpdate(message);//增加消息记
	   				
	   			// String Content="尊敬的用户，您的验证码为"+ i +"，如有疑问，可拨打客服电话 "+Const.hotline;
	   				BusinessService bs = new BusinessService();
	   				bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");
	   				System.out.println("短信输出:" + bs.sendBatchMessage("sdk_yixun", "20150724", formData.get("mobile").toString(), message.getContent()));

	   		
	   				message1.setPushtype("app推送");//app推送
	   				versionService.saveMeorUpdate(message1);//增加消息记
	
	   			    
   				}
   				else if(formData.get("messagetype").equals("逾期提醒")){
   					message.setContent(formData.get("content")+"【翼勋金融】");
   	   			    message.setTitle(formData.get("title").toString());
	   	   			message.setIsdeleted("2");
	   				message.setIsviewed("2");
	   				message.setMessagetype("逾期提醒");
					message.setPushdate(DateUtil.getTime());
	   				message.setPushtype("短信");
	   				
	   				Message message1=message;
	   				
	   				versionService.saveMeorUpdate(message);//增加消息记
	   				
	   			  // String Content="尊敬的用户，您的验证码为"+ i +"，如有疑问，可拨打客服电话 "+Const.hotline;
	   				BusinessService bs = new BusinessService();
	   				bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");
	   				System.out.println("短信输出:" + bs.sendBatchMessage("sdk_yixun", "20150724", formData.get("mobile").toString(), message.getContent()));
	   				
	   				message1.setPushtype("app推送");//app推送
	   				versionService.saveMeorUpdate(message1);//增加消息记
   				
			 }
	
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "此用户不存在");
		}
		return  res;
	}
	
	
	/***
	 * 授信申请成功后 回调 地址  wma 20160301
	 */

	@RequestMapping(value="creditInsert")
	@ResponseBody
	public Map<String, Object> creditInsert() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
		int Banners=bannerService.creditInsert(formData);
		if(Banners>0){
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}

	
	/***
	 * 授信申请成功后 回调 地址  wma 20160301
	 */

	@RequestMapping(value="creditupdata")
	@ResponseBody
	public Map<String, Object> creditupdataSqcode() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
		int Banners=bannerService.creditupdataSqcode(formData);
		if(Banners>0){
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}

	

	/***
	 * 授信列表
	 * mobile 手机号 
	 */
	@RequestMapping(value="creditList")
	@ResponseBody
	public Map<String, Object> creditList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData formData=this.getFormData();
 		List<Map<String, Object>> data=bannerService.creditList(formData);
		res.put("data", data);

		if(data.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
 		}else{
			res.put("code", "10");
			res.put("msg", "暂无信息");
		}
		return  res;
	}
	
	
	
	/***
	 * 商户订单，合同部分，上传pdf 文件
 
	 */
	@RequestMapping(value="qwdimg")
	@ResponseBody
	public Map<String, Object> yzqpwd (HttpServletRequest request,
			@RequestParam String uid,
			@RequestParam String mobile,
			@RequestParam String number,
			HttpSession session) throws Exception {		
 		  FormData formData=this.getFormData();
 		  formData.put("uid", uid);
 		  formData.put("mobile", mobile);
 		  formData.put("number", number);
			 Map<String , Object> res=new HashMap<String, Object>();	
 		  
			 
			 List<Map<String, Object>> pdfList=	 bannerService.pdfList(formData);
			 
 		  if(pdfList.size()>0){
 			   res.put("code", "10");
			   res.put("msg", "失败");
 		  }
 		  else{
 			 MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
 			Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
 			
 		 // if(img1!=null){
 			  
 			  String path = request.getContextPath();
 				String basePath = request.getScheme() + "://" + request.getServerName()
 						+ ":" + request.getServerPort() + path + "/";
 				String fileName = "";
 				int i1=0;
 				for (String key : files.keySet()) {
 					
 					i1++;
 					MultipartFile file = multipartHttpServletRequest.getFile(key);
 					fileName = FileUtil.fileUpload(file, multipartHttpServletRequest,"uploadpdf");
 					//res.put("file", basePath + fileName);
 					System.out.println(i1);
 					formData.put("pdf"+i1, basePath + fileName);
 				}
 				
 				  int i=bannerService.insertImg(formData);
 				   if(i>0){
 					   res.put("code", "00");
 					   res.put("msg", "成功");
 				   }
 				   else{
 					   res.put("code", "10");
 					   res.put("msg", "失败");
 				   }
 			  
 		  }
			
						
					 return res;
  }
	
	

	@RequestMapping(value="yxServicelist1")
 	public ModelAndView bannerlist (String number) throws  Exception {
		
		Yxjrform mf = new Yxjrform();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String nowdate=sdf.format(new Date());
        Map<String,String> map=new HashMap<String,String>();
        mf.setApplyCode(number);
        mf.setCheckMsg(PassWordUtil.toMD5String(false, "KoeIy12Ay~oEuN3"+nowdate));

      String url="http://121.40.185.130:8062/api/CreditKey/costRepaymentPlan";
      String responseStr = soursinterface.doPost(url, mf, "UTF-8",60000);
       System.out.println(responseStr);
       
         JSONObject data=JSONObject.fromObject(responseStr);
 		ModelAndView mv = new ModelAndView ("prodouct/yxlist1");
        mv.addObject("data", data)	;
		return mv;
	}
	
	
	@RequestMapping(value="yxbrank")
	@ResponseBody
	public Map<String, Object> yxbrank (HttpServletRequest request,String card_no){
		Map<String , Object> res=new HashMap<String, Object>();	
		 
		//{"sign":"1880EE4D8D0E12E2A43C3C1363D9ED7D","oid_partner":"201604011000792503","card_no":"6225882123174325","api_version":"1.0","sign_type":"MD5"}
      String RSA_PRIVATE ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJnSxvcHjcq8N+krkZFUnQ2yjiE3HuKKm8OdnJ/xPnLLYjfu7J/6AlenCoUi+joUyVlaFPoJ8yyQkNtbVClaGufasPOaLd9tUFXe06eTJzhww/opcAjU9gF7i4YrPVQAibrUGud17NJCtL9ScBXsLpeypcqJ7NH80h+y294crxwrAgMBAAECgYB4dE3J8+hc8bb2qF99Zrz1lPUnnXpZ/CCttiaIOOmmCw4GrtotDCH5cZ0TVKiOAukJRzlV86SxKSPGImZtW1nlLrgpisDBULC/FUXK2Gg/yGpiJsVQWp4EMF6TZlr4Y4HspGXmpXfPfFkKsiNFOAsEz+J3xTuSy8Y7TtGXYdUGwQJBAMw90T/dHeNvU3kmP9fOGW0OSo8KTAsqADTCXHJylgeWuxVxdlH46Rn2xxnv6lcCq2dbeWL/iSygvvvjm3yCxCcCQQDAzhRpMGlWjIdLrIbT9Hng0hYoMBFPfd/DXCjb9Ss+dGo5PesykPU65Zn8VPRqWcqPA+JA46q1Au5lvFRoKZZdAkAAs4GhZ3/vxPUTaGc1tXZu4mqtHQrvyuAfTj+FARG0yvavdylB1MnwFeHPG/zA+bhe7Xh4N4pgBdlCAiEJh2VnAkEAiG9MMkN3xy8abopfvo050cXYuAeLuFyyg2Qr5brQJxnqMSeHz/iUEGF5Xf4gF0IC/MEa0iBJJF42pPdoI22KYQJBAKJm6eKEH7fA2LUdga9DIibvy2yAPe7eb443VExh3KNGs4kAZ482Jgi8E6DaMiWBPX8UZEmfpDNLC2b0ff1SA6A=";
		    // 银通支付（RSA）公钥
     String RSA_YT_PUBLIC ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB";
		    
	    //String params= "{\'sign\':\'1880EE4D8D0E12E2A43C3C1363D9ED7D\',\'oid_partner\':\'201604011000792503\',\'card_no\':\'6225882123174325\',\'api_version\':\'1.0\',\'sign_type\':\'MD5\'}"; 
		JSONObject js=new JSONObject();
		//js.put("sign", "1880EE4D8D0E12E2A43C3C1363D9ED7D");

		js.put("oid_partner", "201604011000792503");
		js.put("card_no", card_no);
		js.put("api_version", "1.0");
		js.put("sign_type", "MD5");
		
		js.put("sign", "");
	
		String  sign=ApiQuery.genSign(js);
		js.put("sign", sign);
		System.out.println(sign);
                
	    String url="https://yintong.com.cn/queryapi/bankcardbin.htm";
	  
	    String data= soursinterface.postJson(url, js);
	    res.put("data", data);     
		return res;	
	  }
	
	
	
	@RequestMapping(value="yxjb")
	@ResponseBody
	public Map<String, Object> yxjb (HttpServletRequest request,String card_no){
		Map<String , Object> res=new HashMap<String, Object>();	
		 
		//{"sign":"1880EE4D8D0E12E2A43C3C1363D9ED7D","oid_partner":"201604011000792503","card_no":"6225882123174325","api_version":"1.0","sign_type":"MD5"}
      String RSA_PRIVATE ="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJnSxvcHjcq8N+krkZFUnQ2yjiE3HuKKm8OdnJ/xPnLLYjfu7J/6AlenCoUi+joUyVlaFPoJ8yyQkNtbVClaGufasPOaLd9tUFXe06eTJzhww/opcAjU9gF7i4YrPVQAibrUGud17NJCtL9ScBXsLpeypcqJ7NH80h+y294crxwrAgMBAAECgYB4dE3J8+hc8bb2qF99Zrz1lPUnnXpZ/CCttiaIOOmmCw4GrtotDCH5cZ0TVKiOAukJRzlV86SxKSPGImZtW1nlLrgpisDBULC/FUXK2Gg/yGpiJsVQWp4EMF6TZlr4Y4HspGXmpXfPfFkKsiNFOAsEz+J3xTuSy8Y7TtGXYdUGwQJBAMw90T/dHeNvU3kmP9fOGW0OSo8KTAsqADTCXHJylgeWuxVxdlH46Rn2xxnv6lcCq2dbeWL/iSygvvvjm3yCxCcCQQDAzhRpMGlWjIdLrIbT9Hng0hYoMBFPfd/DXCjb9Ss+dGo5PesykPU65Zn8VPRqWcqPA+JA46q1Au5lvFRoKZZdAkAAs4GhZ3/vxPUTaGc1tXZu4mqtHQrvyuAfTj+FARG0yvavdylB1MnwFeHPG/zA+bhe7Xh4N4pgBdlCAiEJh2VnAkEAiG9MMkN3xy8abopfvo050cXYuAeLuFyyg2Qr5brQJxnqMSeHz/iUEGF5Xf4gF0IC/MEa0iBJJF42pPdoI22KYQJBAKJm6eKEH7fA2LUdga9DIibvy2yAPe7eb443VExh3KNGs4kAZ482Jgi8E6DaMiWBPX8UZEmfpDNLC2b0ff1SA6A=";
		    // 银通支付（RSA）公钥
     String RSA_YT_PUBLIC ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB";
		    
	    //String params= "{\'sign\':\'1880EE4D8D0E12E2A43C3C1363D9ED7D\',\'oid_partner\':\'201604011000792503\',\'card_no\':\'6225882123174325\',\'api_version\':\'1.0\',\'sign_type\':\'MD5\'}"; 
		JSONObject js=new JSONObject();
		//js.put("sign", "1880EE4D8D0E12E2A43C3C1363D9ED7D");
		
		js.put("oid_partner", "201604011000792503");
		js.put("user_id", card_no);
		js.put("no_agree", card_no);
		js.put("sign_type", "MD5");
		js.put("sign", "");
	
		String  sign=ApiQuery.genSign(js);
		js.put("sign", sign);
		System.out.println(sign);
                
	    String url="https://yintong.com.cn/traderapi/bankcardunbind.htm";
	  
	    String data= soursinterface.postJson(url, js);
	    res.put("data", data);     
		return res;	
	  }
	
	
	@RequestMapping(value="wxtoken")
	@ResponseBody
	public Map<String, Object> wxtoken (HttpServletRequest request) throws Exception{
		 FormData form=new FormData();		
		form.put("id", 497);
          Map<String, Object>  tokens= dicservice.findDictionaryId(form);
	      System.out.println(tokens);
	      Weixin weixin=new Weixin();
			String token= Weixin.infcode();
			form.put("xtest", token);
	    
		return tokens;
	
	}
	
	}
 
