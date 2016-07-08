package com.fq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fq.service.DictionaryService;
import com.fq.service.UserService;
import com.fq.util.FormData;

@Controller
public class Yjprice {
	@Autowired
     UserService service;
	@Autowired
	DictionaryService dirservice;
	
	/***
	 * 定时更新微信token
	 * @return
	 * @throws Exception
	 */
 	public void updatekyprice() throws Exception{
  		Weixin weixin=new Weixin();
		String token= Weixin.merchant_introducebymid();
		FormData form=new FormData();	
		String id="497";
        form.put("id", id);
        form.put("value", token);
        form.put("type", "微信token(勿动)");

		if (id != null && !"".equals(id)) {
			dirservice.updateDictionary(form);
		}
		
		
		System.out.println("更新token开始"+token);
 	}
	
	
	
}
