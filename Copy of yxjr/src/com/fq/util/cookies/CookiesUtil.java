package com.fq.util.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtil {

	/**
	 * 添加cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void addCookie (HttpServletResponse response , String name, String value , Integer maxAge) {
		
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if(maxAge != null ){
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}
	/**
	 * 获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request ,String name){
		Cookie[] cookies = request.getCookies();
		if(cookies == null ){
			return "";
		}
		for (Cookie cookie : cookies ){
			if (cookie.getName().equals(name)){
				return cookie.getValue();
			}
		}
		return "";
	}
	/**
	 * 删除cookie
	 * @param response
	 * @param name
	 */
	public static void delCookie (HttpServletResponse response,String... name){
		for(String m : name ){
			Cookie c = new Cookie(m, null);
			c.setMaxAge(0);
			c.setPath("/");
			response.addCookie(c);
		}
	} 
}
