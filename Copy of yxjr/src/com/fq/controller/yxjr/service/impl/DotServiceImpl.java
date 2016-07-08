package com.fq.controller.yxjr.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fq.controller.yxjr.service.DotService;
import com.fq.dao.DaoSupport;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

@Service
public class DotServiceImpl implements DotService {

	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	@Override
	public void insertDot(FormData form) throws Exception {
		// TODO Auto-generated method stub
		 daoSupport.save("DotMapper.insertDot", form);
	} 

	@Override
	public void updateDot(FormData form) throws Exception {
		// TODO Auto-generated method stub
		 daoSupport.update("DotMapper.updateDot", form);
	}

	@Override
	public List<Map<String, Object>> findDotPage(PageInfo page) throws Exception {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) daoSupport.findForList("DotMapper.findDotPage", page);
		}

	@Override
	public Map<String, Object> findDotId(FormData form) throws Exception {
		// TODO Auto-generated method stub
		return (Map<String, Object>) daoSupport.findForObject("DotMapper.findDotId", form);
       }

	@Override
	public void deleteDot(FormData form) throws Exception {
		// TODO Auto-generated method stub
		List<String> ids = Arrays.asList(form.getString("ids").split(","));
		daoSupport.delete("DotMapper.deleteDot", ids);
	}

}
