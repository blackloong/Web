package com.fq.controller.yxjr.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fq.controller.yxjr.service.ProdouctService;
import com.fq.dao.DaoSupport;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

@Service
public class ProdouctServiceImpl implements ProdouctService {

	@Resource(name="daoSupport")
	private DaoSupport daoSupport;

	@Override
	public List<Map<String, Object>> findProdouctPage(PageInfo page)
			throws Exception {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) daoSupport.findForList("ProdouctMapper.findProdouctPage", page);
	}

	@Override
	public Map<String, Object> findProdouctId(FormData form) throws Exception {
		// TODO Auto-generated method stub
		return (Map<String, Object>) daoSupport.findForObject("ProdouctMapper.findProdouctId", form);
	}

	@Override
	public void insertProdouct(FormData form) throws Exception {
		// TODO Auto-generated method stub
		 daoSupport.save("ProdouctMapper.insertProdouct", form);
	}

	@Override
	public void updateProdouct(FormData form) throws Exception {
		// TODO Auto-generated method stub
		 daoSupport.update("ProdouctMapper.updateProdouct", form);
	}

	@Override
	public void deleteProdouctIds(FormData formData) throws Exception {
		// TODO Auto-generated method stub
		List<String> ids = Arrays.asList(formData.getString("ids").split(","));
		daoSupport.delete("ProdouctMapper.deleteProdouctIds", ids);
	}

	
	
	
	
	@Override
	public List<Map<String, Object>> findField(FormData form) throws Exception {
		 
		return (List<Map<String, Object>>) daoSupport.findForList("ProdouctMapper.findField", form);
	}

	@Override
	public Map<String, Object> findFieldId(FormData form) throws Exception {
		 
		return (Map<String, Object>) daoSupport.findForObject("ProdouctMapper.findFieldId", form);
	}

	@Override
	public void updateField(FormData form) throws Exception {
		 
		 daoSupport.update("ProdouctMapper.updateField", form);
	}

	@Override
	public void insertField(FormData form) throws Exception {
		 
		 daoSupport.save("ProdouctMapper.insertField", form);
	}

	@Override
	public void deleteField(FormData formData) throws Exception {
		 
		List<String> ids = Arrays.asList(formData.getString("ids").split(","));
		daoSupport.delete("ProdouctMapper.deleteFieldIds", ids);
	}

	@Override
	public void prodouctFbXj(FormData form) throws Exception {
		 
		 daoSupport.update("ProdouctMapper.prodouctFbXj", form);
	}

	@Override
	public List<Map<String, Object>> prodouctlist(FormData form)
			throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("ProdouctMapper.prodouctlist", form);

	}
 
	
}
