package com.fq.form;

public class WeixinForm {
	String  access_token;// 	是 	调用接口凭证
	
	String openid;// 	是 普通用户的标识，对当前公众号唯一
	String lang ;// 	返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语 

	String next_openid ;//	是 	第一个拉取的OPENID，不填默认从头开始拉取 
	
	String appid;
 
	String  secret;

	String  grant_type;
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}

	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
}
