package com.fq.service;

import java.util.List;
import com.fq.entity.Message;
import com.fq.entity.Version;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

/***
 * 首页滚动图接口
 * 
 * @author Admincx
 * 
 */
public interface VersionService {
    //版本管理列表	
	List<FormData> getAllPage(PageInfo page) throws Exception;
	//消息管理
	List<FormData> getMessageAllPage(PageInfo page) throws Exception;
	
    Message selectMsByPrimaryKey(Integer id);
    Version selectByvePrimaryKey(Integer id);
    
    int deleteByMePrimaryKey(Integer id);
    int deleteByVerPrimaryKey(Integer id);
    int saveMeorUpdate (Message me) throws Exception;
    int saveVeorUpdate (Version ve) throws Exception;
    
	List<FormData> getuserAll() throws Exception;//查询所有用户

 }
