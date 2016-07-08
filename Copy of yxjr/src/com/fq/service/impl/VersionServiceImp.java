package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DaoSupport;
import com.fq.entity.Message;
import com.fq.entity.Version;
import com.fq.service.VersionService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;
@Service
public class VersionServiceImp implements VersionService {

	 @Autowired
	 DaoSupport daoSupport;
	
	@Override
	public List<FormData> getAllPage(PageInfo page) throws Exception {
 		return (List<FormData>) daoSupport.findForList("VersionMapper.getAllLoginPage", page);
	}

	@Override
	public List<FormData> getMessageAllPage(PageInfo page) throws Exception {
 		return (List<FormData>) daoSupport.findForList("MessageMapper.getAllLoginPage", page);
	}

	@Override
	public Message selectMsByPrimaryKey(Integer id) {
	 
		try {
			return (Message) daoSupport.findForObject("MessageMapper.selectByPrimaryKey", id);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Version selectByvePrimaryKey(Integer id) {
		try {
			return (Version) daoSupport.findForObject("VersionMapper.selectByPrimaryKey", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public int deleteByMePrimaryKey(Integer id) {
 		try {
			return (Integer) daoSupport.delete("MessageMapper.deleteByPrimaryKey", id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int deleteByVerPrimaryKey(Integer id) {
		try {
			return (Integer) daoSupport.delete("VersionMapper.deleteByPrimaryKey", id);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int saveMeorUpdate(Message me) throws Exception {
		int i=0;
 		if(me.getId()!=null){
			i=(Integer) daoSupport.update("MessageMapper.updateByPrimaryKeySelective", me);
 			
		}
		
		else{
			i=(Integer) daoSupport.save("MessageMapper.insertSelective", me);
 		}
		
		return i;
	}

	@Override
	public int saveVeorUpdate(Version ve) throws Exception {
		
		if(ve.getId()!=null){
			daoSupport.update("VersionMapper.updateByPrimaryKeySelective", ve);
 			
		}
		
		else{
			Object save = daoSupport.save("VersionMapper.insertSelective", ve);
 		}
		
		return 0;
	}

	@Override
	public List<FormData> getuserAll() throws Exception {
 		return (List<FormData>) daoSupport.findForList("UserMapper.getUserAll", null);

	}

	

 
	
	
}
