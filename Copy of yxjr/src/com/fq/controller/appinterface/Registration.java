package com.fq.controller.appinterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fq.annotations.InterfaceValidate;
import com.fq.form.moblieform;
import com.fq.service.TokenService;
import com.fq.service.UserService;
import com.fq.util.Const;
import com.fq.util.FormData;
import com.fq.util.PassWordUtil;
import com.fq.util.StringUtils;
import com.jianzhou.sdk.BusinessService;

/***
 * 翼勋APP接口-会员注册
 * @author Admincx
 *
 * mobile 手机号
 * pwd    密码
 * sours  验证码
 * rmobileNo 推荐人手机号
 * token 手机唯一标识编号
 */
@Controller
@RequestMapping(value="interface")
public class Registration extends ResponseBaseController {
	private UserService userservice;
	private TokenService tokenservice;
	public TokenService getTokenservice() {
		return tokenservice;
	}
	@Autowired
	public void setTokenservice(TokenService tokenservice) {
		this.tokenservice = tokenservice;
	}
	public UserService getUserservice() {
		return userservice;
	}
	@Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	@RequestMapping(value="registration")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"mobile","pwd","sours"})
	public Map<String, Object> registration() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
		//这里 暂时 不加密  ,以后根据需求 在进行加密 
		//form.put("password", PassWordUtil.MD5(form.getString("password")));
		 String mobile=form.getString("mobile");
		 
		 form.put("password",form.get("pwd"));
		  
		List<FormData>  user= userservice.checkMobile(form);
		if(user.size()>0){
			res.put("code", "10");
			res.put("msg", "此用户已存在");
			return res;
		}
		
	
		String sours=(String) form.get("sours");

		Map<String, Object> soursMap=userservice.MChecksours(form);
	 
		if(soursMap==null || soursMap.equals("") ){
			res.put("code", "20");
			res.put("msg", "验证码不正确");
			return res;
		}
	 
		else{
			 //验证码失效
			 if(StringUtils.timeOut(soursMap.get("SendTime").toString(), 1200000)){
				    res.put("code", "20");
					res.put("msg", "验证码失效");
					return res;
			  }
				
			 
			
		}
		 form.put("username", mobile);
		 form.put("roleid", 2);
	     userservice.saveUser(form);
		
		Long id=(Long) form.get("id");
		form.put("uid", id);
		/*form.put("token", PassWordUtil.MD5(id+form.getString("token")));
		Map<String,Object> token=tokenservice.createTokenByUidAndTokenStr(form);
		if(token==null){
			res.put("code", "30");
			res.put("msg", "token创建失败");
			return res;
		}
		*/
		
		
		if(id>0){
			/*String rmobileNo=(String) form.get("rmobileNo");
			if(rmobileNo!="" && rmobileNo!=null){
				userservice.updatecredit(form);
				userservice.savecreditdetail(form);
			}*/
			res.put("code", "00");
			res.put("msg", "注册成功");
			return res;
		}
		res.put("code", "80");
		res.put("msg", "注册失败");
		return res;
	}
	/***
	 * 重置密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="Resetpassword")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"mobile","password","sours"})
	public Map<String, Object> Resetpassword() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
		 String mobile=form.getString("mobile");
		 form.put("password", PassWordUtil.MD5(form.getString("password")));
		//form.put("password", PassWordUtil.MD5(mobile.substring(mobile.length()-6, mobile.length())));

		String sours=(String) form.get("sours");
		Map<String, Object> soursMap=userservice.MChecksours(form);
	 
		if(soursMap==null || soursMap.equals("") ){
			res.put("code", "20");
			res.put("msg", "验证码不正确");
			return res;
		}
		else{
			
			 //验证码失效
			 if(StringUtils.timeOut(soursMap.get("SendTime").toString(), 1200000)){
				    res.put("code", "20");
					res.put("msg", "验证码失效");
					return res;
			  }
			 
			userservice.UpdateUserPwd(form);
			res.put("code", "00");
			res.put("msg", "修改成功");
		}
	
		return res;
	}
	
	
	/***
	 * 修改密码 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="updatepwd")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"mobile","oldpwd","password"})
	public Map<String, Object> updatepwd() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
		 String mobile=form.getString("mobile");
		 //form.put("password", PassWordUtil.MD5(mobile.substring(mobile.length()-6, mobile.length())));
		  form.put("password", PassWordUtil.MD5(form.getString("password")));
		  
		  FormData form1=this.getFormData();
		  
		  form1.put("password", PassWordUtil.MD5(form.getString("oldpwd")));
		  Map<String, Object>  mss=userservice.getUserByMobileAndPassWord(form1);
		  if(mss!=null){
			  
			  if(mobile!=null && form.get("password")!=null){
				  userservice.UpdateUserPwd(form);
					res.put("code", "00");
					res.put("msg", "修改成功");
				  
			  }
			  else{
				  res.put("code", "10");
				  res.put("msg", "修改失败");
			  }
		  }
		  else{
			  res.put("code", "10");
			  res.put("msg", "旧密码不正确");
			  
		  }

		
			
	
		return res;
	}
	

	/***
	 * 获取短信验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="interfaceyzm")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"mobile"})
	public Map<String, Object> interfacescbdsoursyzmbyphone() throws Exception {
		
		Map<String, Object> res=new HashMap<String, Object>();
		FormData forms=this.getFormData();
		List<FormData>  user=userservice.checkMobile(forms);
		 
			moblieform mf = new moblieform();
			
			FormData form=this.getFormData();	
	        Random rd = new Random();
	        int i = rd.nextInt(9999);
	        for (int j = 0; j >= 0; j++) {
			    if (i >= 1000)
				break;
			     i = rd.nextInt(9999);
		   }
	        
	        
	        String Content="尊敬的用户，您的验证码为"+ i +"，如有疑问，可拨打客服电话 "+Const.hotline;
	    	 
			BusinessService bs = new BusinessService();
			bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");

			/***
			类别	参数名称	类型	说明
			输入	account	string	企业的登录账号
			输入	password	string	账号对应的密码
			输入	destmobile	string	目标手机号，
			1、	多个手机号码用;分割
			2、	建议一次最多提交3000左右的号码 
	         */
  	    
		System.out.println("短信输出:" + bs.sendBatchMessage("sdk_yixun", "20150724", form.getString("mobile"), Content));

	     form.put("Verificationcode", i);
			userservice.delYzm(form);
			userservice.saveyzm(form);
			res.put("code", "00");
		 		
		
		
		return res;
	}
	
	
	
	/***
	 *  发送短信验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="interfaceorderyzm")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"mobile"})
	public Map<String, Object> interfaceyzm() throws Exception {
		
		Map<String, Object> res=new HashMap<String, Object>();
		FormData forms=this.getFormData();
			moblieform mf = new moblieform();
			FormData form=this.getFormData();	
			
			List<FormData> user=userservice.checkMobile(forms);
			if(user.size()>0){
				res.put("code", "10");
				res.put("msg", "此用户已存在");
				return res;
			}
			else{
			
		        Random rd = new Random();
		        int i = rd.nextInt(9999);
		        for (int j = 0; j >= 0; j++) {
				    if (i >= 1000)
					break;
				     i = rd.nextInt(9999);
			   }
		        String Content="尊敬的用户，您的验证码为"+ i +"，如有疑问，可拨打客服电话 "+Const.hotline;
		    	 
				BusinessService bs = new BusinessService();
				bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");
	
				/***
			类别	参数名称	类型	说明
			输入	account	string	企业的登录账号
			输入	password	string	账号对应的密码
			输入	destmobile	string	目标手机号，
			1、	多个手机号码用;分割
			2、	建议一次最多提交3000左右的号码 
	         */
  	    
			System.out.println("短信输出:" + bs.sendBatchMessage("sdk_yixun", "20150724", form.getString("mobile"), Content));
	
		     form.put("Verificationcode", i);
				userservice.delYzm(form);
				userservice.saveyzm(form);
				res.put("code", "00");
			
			}
		 
		return res;
	}
	

	/***
	 * 微信绑定 手机号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="wxregistration")
	@ResponseBody
	@InterfaceValidate(vliadateRequestData={"mobile","pwd","sours","wx"})
	public Map<String, Object> wxregistration() throws Exception
	{
		
		Map<String, Object> res=new HashMap<String, Object>();
		FormData form=this.getFormData();
		//这里 暂时 不加密  ,以后根据需求 在进行加密 
		//form.put("password", PassWordUtil.MD5(form.getString("password")));
		//String mobile=form.getString("mobile");
		 
		form.put("password", PassWordUtil.MD5(form.getString("pwd")));
         //form.put("password",form.get("pwd"));
	
		//String sours=(String) form.get("sours");
		Map<String, Object> soursMap=userservice.MChecksours(form);
		if(soursMap==null || soursMap.equals("") )
		{
			res.put("code", "20");
			res.put("msg", "验证码不正确");
			return res;
		}
	 	else
	 	{
			 //验证码失效
			 if(StringUtils.timeOut(soursMap.get("SendTime").toString(), 1200000))
			 {
				 res.put("code", "20");
				 res.put("msg", "验证码失效");
				 return res;
			 }					
		}
				  
	    int id = 0;//数据库操作id，标识是否成功绑定
		 
		//获得手机号的数据
		FormData mobform = new FormData();
		mobform.put("mobile", form.getString("mobile"));			
		List<FormData> mobileUser = userservice.checkMobile(mobform);//检测手机号是否存在
		if(mobileUser.size() > 0)
		{
			//该手机号注册，请登录
			res.put("code", "90");
			res.put("msg", "invalid mobile");
			return res;
		}
		//不存在该手机号，则把手机号绑定到微信数据上
		FormData wxform = new FormData();
		wxform.put("wx", form.getString("wx"));			
		List<FormData> wxUser = userservice.checkMobile(wxform);//检测微信号是否已授权
		if(wxUser.size() <= 0)
		{
			 res.put("code", "21");
			 res.put("msg", "invalid openid");//内部错误，未找到对应的微信数据（在用户访问页面时应该通过access_token保存过微信数据)
			 return res;
		}
		JSONObject wxUserJson = JSONObject.fromObject(wxUser.get(0));
		wxform.put("mobile", form.getString("mobile"));
		wxform.put("password", form.getString("password"));
		wxform.put("roleid", 2);
		id = userservice.updatewxUser(wxform);
	  	if(id>0)
	  	{
			res.put("code", "00");
			res.put("msg", "绑定成功");
			return res;
		}
		res.put("code", "80");
		res.put("msg", "绑定失败");
		return res;
	}
	
	

	
}
