package com.fq.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fq.dao.DaoSupport;
import com.fq.service.AuthService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

@Service
public class AuthServiceImpl implements AuthService {

	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
	
	/**
	 * 获取功能组
	 */
	@Override
	public List<Map<String, String>> getAllProgramTeamByPage(PageInfo page)throws Exception {
		return (List<Map<String, String>>) daoSupport.findForList("TeamMapper.getAllProgramTeamByPage",page );
	}

	/**
	 * 添加功能组
	 */
	@Override
	public void insertProgramTeam(String name) throws Exception {
		daoSupport.save("TeamMapper.insertProgramTeam", name);
	}

	/**
	 * 删除功能组
	 */
	@Override
	public void updateProgramTeamById(FormData formData) throws Exception {
		daoSupport.update("TeamMapper.updateProgramTeamById", formData);
	}

	@Override
	public List<Map<String, String>> getAllProgramItem() throws Exception {
		return (List<Map<String, String>>) daoSupport.findForList("TeamMapper.getAllProgramItem", null);
	}
	/**
	 * 添加子功能
	 */
	@Override
	public void insertProgramItemByTeamId(FormData formData) throws Exception {
		daoSupport.save("TeamMapper.insertProgramItemByTeamId", formData);
	}

	@Override
	public void updateProgramItemById(FormData formData) throws Exception {
		daoSupport.update("TeamMapper.updateProgramItemById", formData);
	}

	@Override
	public List<Map<String, String>> getALLDetailByItemId(FormData formData) throws Exception{
		return (List<Map<String, String>>) daoSupport.findForList("TeamMapper.getALLDetailByItemId", formData);
	}

	@Override
	public void insertProgramDetailByItemId(FormData formData) throws Exception {
		daoSupport.save("TeamMapper.insertProgramDetailByItemId", formData);
	}

	@Override
	public void updateDetailById(FormData formData) throws Exception {
		daoSupport.update("TeamMapper.updateDetailById", formData);
	}

	@Override
	public void deleteDetailById(FormData formData) throws Exception {
		List<String> ids = Arrays.asList(formData.getString("ids").split(","));
		daoSupport.delete("TeamMapper.deleteDetailById", ids);
	}

}
