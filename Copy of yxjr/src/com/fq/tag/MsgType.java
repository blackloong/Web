package com.fq.tag;

import java.util.HashMap;
import java.util.Map;

public class MsgType {
	public static Map<String , Map<String , String>> msgMap(){
		Map<String , Map<String , String>> res=new HashMap<String, Map<String,String>>();
		Map<String , String> obj =new HashMap<String, String>();
		//3 流标 4 退标5 收到回款6 债权转让成功7 满标8 借款标发生债权转让9 还款成功',
//		Integer msgType=0;//站内消息类型
//		String msgContent="";//站内消息内容
//		String msgTitle="";//站内消息标题
		
		
		obj.put("msgType", "3");//流标
		obj.put("msgTitle", "流标消息");
		obj.put("msgContent", "您好,很抱歉，您已流标");
		res.put("41", obj);
		
		obj =new HashMap<String, String>();
		obj.put("msgType", "4");//退标
		obj.put("msgTitle", "退标消息");
		obj.put("msgContent", "您好,很抱歉，您已退标");
		res.put("42", obj);
		
		obj =new HashMap<String, String>();
		obj.put("msgType", "5");//收到回款
		obj.put("msgTitle", "收到回款");
		obj.put("msgContent", "您好,收到回款");
		res.put("43", obj);
		
		obj =new HashMap<String, String>();
		obj.put("msgType", "6");//债权转让成功
		obj.put("msgTitle", "债权转让成功");
		obj.put("msgContent", "您好,债权转让成功");
		res.put("44", obj);
		
		obj =new HashMap<String, String>();
		obj.put("msgType", "7");//满标
		obj.put("msgTitle", "满标");
		obj.put("msgContent", "您好，您的标已满");
		res.put("45", obj);
		
		obj =new HashMap<String, String>();
		obj.put("msgType", "8");//借款标发生债权转让
		obj.put("msgTitle", "借款标发生债权转让");
		obj.put("msgContent", "您好，您的借款标发生债权转让");
		res.put("46", obj);
		
		obj =new HashMap<String, String>();
		obj.put("msgType", "9");//还款成功
		obj.put("msgTitle", "还款成功");
		obj.put("msgContent", "您好，您的还款成功");
		res.put("47", obj);
		return res;
	}
}
