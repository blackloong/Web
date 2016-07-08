package com.fq.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtil {
	//jpush官方 Portal上注册应用时生成的 masterSecret
	public final static String masterSecret = "804a7a1f7004e666c7aacae0";
	//jpush官方Portal上注册应用时生成的 appKey
	public final static String appKey = "a206339d1de8e7b1caa3587d";
	
	public final static JPushClient client = new JPushClient(masterSecret, appKey);
		
/*	public static void sendMessageByAlias(String title,String content,Map<String, String> extras,String...alias ){
	 	try {
			String uid = "";
			for (String a:alias ){
				uid +=a+",";
			}
			if(uid.length()>0){
				uid = uid.substring(0, uid.length()-1);
			}
			if(uid.length()<=0)return;
			List<Map<String, String>> pushList = DBUtil.queryList("select * from yw_jpush where uid  in  ("+uid+") ");
			List<String> aliasForAnd = new ArrayList<String> ();
			List<String>aliasForiOS = new ArrayList<String>();
			for (Map<String, String> m : pushList ){
				if(m.get("type").equals("a")){
					aliasForAnd.add(m.get("uid"));
				}else{
					aliasForiOS.add(m.get("uid"));
				}
			}
			if(aliasForAnd.size()>0)
				sendMessageToAdroidByAlias(title, content, extras ,aliasForAnd.toArray(new String[aliasForAnd.size()]));
			if(aliasForiOS.size()>0)
				sendMessageToiOSByAlias(content, extras, aliasForiOS.toArray(new String[aliasForiOS.size()]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}*/
	
	public static void sendMessageByAlias(String title,String content,Map<String, String> extras,String...alias ){
	 	try {
			String uid = "";
			for (String a:alias ){
				uid +=a+",";
			}
			if(uid.length()>0){
				uid = uid.substring(0, uid.length()-1);
			}
			if(uid.length()<=0)return;
			//List<Map<String, String>> pushList = DBUtil.queryList("select * from yw_jpush where uid  in  ("+uid+") ");
			 List<Map<String, String>> pushList = DBUtil.queryList("SELECT * from tb_message where userid in ("+uid+") and pushtype='app推送' ORDER BY id desc");

			List<String> aliasForAnd = new ArrayList<String> ();
			//List<String>aliasForiOS = new ArrayList<String>();
			for (Map<String, String> m : pushList ){
				aliasForAnd.add(m.get("userid"));
			}
			if(aliasForAnd.size()>0)
				sendMessageToAdroidByAlias(title, content, extras ,aliasForAnd.toArray(new String[aliasForAnd.size()]));
			    sendMessageToiOSByAlias(content, extras, aliasForAnd.toArray(new String[aliasForAnd.size()]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * 给android 发送消息
	 * @param title
	 * @param content
	 * @param extras 别名
	 * @param alias
	 * @throws Exception
	 */
	public static void sendMessageToAdroidByAlias (String title,String content,Map<String, String> extras,String... alias) throws Exception{
		PushResult pushResult = client.sendAndroidNotificationWithAlias(title, content,extras, alias);
		System.out.println(pushResult.toString());
	}
	/**
	 * 给ios发送指定消息
	 * @param content
	 * @param extras
	 * @param alias 别名
	 * @throws Exception
	 */
	public static void sendMessageToiOSByAlias(String content,Map<String, String> extras,String... alias)throws Exception{
		PushResult pushResult = client.sendIosNotificationWithAlias(content, extras, alias);
		System.out.println(pushResult.toString());
	}
	/**
	 * 给所有用户发送消息
	 * @param content
	 * @throws Exception
	 */
	public static void sendToAll (String content)throws Exception{
		PushResult pushResult = client.sendMessageAll(content);
		System.out.println(pushResult.toString());
	}
	
	public static void main(String[] args) throws Exception{
		sendToAll("title");
	}
	  public static PushPayload buildPushObject_all_alias_alert() {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.all())
	                .setAudience(Audience.alias("001"))
	                .setNotification(Notification.alert("jpush002"))
	                .build();
	    }
}
