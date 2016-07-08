package com.fq.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fq.controller.base.BaseController;
import com.fq.form.WeixinConst;
import com.fq.service.DictionaryService;
import com.fq.service.UserService;
import com.fq.util.FormData;
import com.fq.util.HttpUtil;
import com.fq.util.Snippet;
 

@Controller
@RequestMapping("weixin")
public class WeixinController extends BaseController{
	@Autowired
	  UserService userservice;
	
	@Autowired
	DictionaryService dirservice;
	
	/**
	 * 微信验证
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public  String checkWeixinSign () {
		FormData data = getFormData();
 		return data.getString("echostr");
	}
	/**
	 * 处理微信发送消息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String receiveMsgFromWX () throws Exception{
		 ServletInputStream in = this.getRequest().getInputStream();  
		 StringBuilder xmlMsg = new StringBuilder();  
         byte[] b = new byte[4096];  
         for (int n; (n = in.read(b)) != -1;) {  
             xmlMsg.append(new String(b, 0, n, "UTF-8"));  
         } 
         //log.info("weixin : " + xmlMsg.toString());
        // appUserService.subscribeWX(xmlMsg.toString(),this.getBasePath());
         return "";
	}
	
	@RequestMapping("oauth")
	public String toWXOauth (Model model) throws Exception {
		FormData data =  getFormData();
		String appid = WeixinConst.AppId;
		String redirect_uri = this.getBasePath()+"/weixin/access_token";
		redirect_uri = URLEncoder.encode(redirect_uri,"utf-8");
		String response_type = "code";
		String scope = "snsapi_userinfo";
	//	String state = data.getString("rediect")+""+"#wechat_redirect";
		 String state ="#wechat_redirect";

		StringBuffer url = new StringBuffer();
		url.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
		url.append("appid="+appid);
		url.append("&redirect_uri="+redirect_uri);
		url.append("&response_type="+response_type);
		url.append("&scope="+scope);
		url.append("&state="+state);
		StringBuffer sb = new StringBuffer();
		sb.append("<form action=\""+url.toString()+"\" method=\"post\" id=\"weixinform\">");
		sb.append("</form>");
		model.addAttribute("form", sb);
		return "weixin/test";
	}

	@RequestMapping("access_token")
	public String access_token (Model model) throws Exception {
		FormData data = getFormData();
	    
		String code = data.getString("code");
		String appid = WeixinConst.AppId;
		String appsecret = WeixinConst.appsecret;
		String url ="https://api.weixin.qq.com/sns/oauth2/access_token?"
				+ "appid="+appid+"&"
				+ "secret="+appsecret+"&"
				+ "code="+code+"&"
			   // + "access_token="+tokens.get("value")+"&"//用户token
				+ "grant_type=authorization_code";
		
		String responseStr = HttpUtil.doGet(url, "utf-8");
		
		
		JSONObject jsonResponse = JSONObject.fromObject(responseStr);
 		//log.info("通过code换取网页授权access_token : " +jsonResponse);
		String openid = jsonResponse.getString("openid");
		String  access_token = jsonResponse.getString("access_token");
		String userDetailUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
		String respUserStr = HttpUtil.doGet(userDetailUrl, "utf-8");
		//log.info("微信用户信息:" + respUserStr);
		JSONObject userJSON = JSONObject.fromObject(respUserStr);
		//更新用户
		FormData formData = new FormData();
		String state = data.getString("state");
		String redirectURL = state;
		if(state != null ){
			if(state.indexOf("_parent_id_")>-1) {
				String [] infos = state.split("_parent_id_");
				formData.put("parent_id", infos[1]);
				redirectURL =  infos[0];
			}
		}
		//微信基本信息
		formData.put("subscribe", userJSON.get("subscribe"));
		formData.put("subscribe", userJSON.get("subscribe"));
		formData.put("openid", userJSON.get("openid"));
		//formData.put("nickname", EmojiFilter.removeNonBmpUnicode((userJSON.get("nickname")+"")));
		formData.put("sex", userJSON.get("sex"));
		formData.put("city", userJSON.get("city"));
		formData.put("country", userJSON.get("country"));
		formData.put("province", userJSON.get("province"));
		formData.put("headimgurl", userJSON.get("headimgurl"));
		formData.put("subscribe_time", userJSON.get("subscribe_time"));
		formData.put("remark", userJSON.get("remark"));
		
		FormData form = new FormData();
		form.put("wx",userJSON.get("openid"));
		form.put("roleid","2");
		form.put("username", /*userJSON.get("nickname") */Snippet.removeNonBmpUnicode((userJSON.get("nickname")+"")));
		form.put("headimg", userJSON.get("headimgurl"));//新增 微信头像
	
 		if(form.getString("wx")!=null&& form.getString("wx")!=""){	
 			userservice.saveWeixinUser(form);

 		}
 		
 		String urls="";
 		 
		List<FormData> checkMobile=userservice.checkMobile(form); 
		if(checkMobile.size()>0){
			 JSONObject ujson=JSONObject.fromObject(checkMobile.get(0));
			if(ujson.containsKey("mobile")){
				urls="redirect:http://keyofcredit.eifm.net/view/public/index.html?map="+userJSON;

			}
			else{
				urls="redirect:http://keyofcredit.eifm.net/view/public/register.html?map="+userJSON;

			}

		}
		 
 		
		/*String pageName = jsonResponse.getString("pageName");//跳转页面修改
		urls="redirect:http://keyofcredit.eifm.net/view/public/"+pageName+"?map="+userJSON;
		*/
 		//WeiXinUser weiXinUser = appUserService.updateByWeixin(formData,this.getBasePath());
		//this.getRequest().getSession().setAttribute(Const.WEIXIN_USER, weiXinUser);
		redirectURL = (redirectURL== null || redirectURL.equals(""))?"/":redirectURL;
		//return "redirect:"+redirectURL;
		// return "redirect:http://121.40.185.130:8079?map="+userJSON;
		return urls;

	}
	@RequestMapping(value="wxuname")
	@ResponseBody
 	public Map<String, Object> veisonList() throws Exception{
		Map<String, Object> res=new HashMap<String, Object>();
		List<FormData> checkMobile=userservice.checkMobile(this.getFormData()); 
		if(checkMobile.size()>0){
			res.put("code", "00");
			res.put("msg", "成功");
			res.put("date", checkMobile.get(0));
		}else{
			res.put("code", "10");
			res.put("msg", "失败");
		}
		return  res;
	}
	
	
}
