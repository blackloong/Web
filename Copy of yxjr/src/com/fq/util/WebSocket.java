package com.fq.util;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websorket")
public class WebSocket {

	@OnMessage
	public void onMessage(String message, Session session) throws Exception{
		session.getBasicRemote().sendText(message);
		
	}
	@OnOpen
    public void onOpen (Session session) throws Exception {
        System.out.println("客户端--已链接");
        session.getBasicRemote().sendText("加出了");
    }

	//客户端断开触发
    @OnClose
    public void onClose (Session session)throws Exception {
    	System.out.println("客户端--已关闭");
    	 session.getBasicRemote().sendText("加出了");
    }
}
