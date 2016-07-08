package com.fq.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fq.dao.DaoSupport;
import com.fq.service.DictionaryService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Resource(name="daoSupport")
	private DaoSupport daoSupport;

	@Override
	public List<FormData> findDictionaryPage(PageInfo page) throws Exception {
		// TODO Auto-generated method stub
		return (List<FormData>) daoSupport.findForList("DictionaryMapper.findDictionaryPage", page);
	}

	@Override
	public Map<String, Object> findDictionaryId(FormData form) throws Exception {
		// TODO Auto-generated method stub
		return (Map<String, Object>) daoSupport.findForObject("DictionaryMapper.findDictionaryId", form);
	}

	@Override
	public void updateDictionary(FormData form) throws Exception {
		// TODO Auto-generated method stub
		 daoSupport.update("DictionaryMapper.updateDictionary", form);
	}

	@Override
	public void insertDictionary(FormData form) throws Exception {
		// TODO Auto-generated method stub
		 daoSupport.save("DictionaryMapper.insertDictionary", form);
	}

	@Override
	public	List<Map<String, String>> findDictionaryPid(FormData form) throws Exception {
		// TODO Auto-generated method stub
		return (	List<Map<String, String>>) daoSupport.findForList("DictionaryMapper.findDictionaryPid", form);
		}

	@Override
	public void deleteDictionary(FormData formData) throws Exception {
		// TODO Auto-generated method stub
		List<String> ids = Arrays.asList(formData.getString("ids").split(","));
		daoSupport.delete("DictionaryMapper.deleteDictionary", ids);
	}

	@Override
	public List<FormData> findtypePage(PageInfo page) throws Exception {
		return (List<FormData>) daoSupport.findForList("DictionaryMapper.findtypePage", page);

	}

	@Override
	public Map<String, Object> findtypeId(FormData form) throws Exception {
 		return (Map<String, Object>) daoSupport.findForObject("DictionaryMapper.findtypeId", form);

	}

	@Override
	public void updatetype(FormData form) throws Exception {
		 daoSupport.update("DictionaryMapper.updatetype", form);

		
	}

	@Override
	public void inserttype(FormData form) throws Exception {
		 daoSupport.save("DictionaryMapper.inserttype", form);
		
	}

	@Override
	public List<Map<String, String>> findtypePid(FormData form)
			throws Exception {
		return (	List<Map<String, String>>) daoSupport.findForList("DictionaryMapper.findtypePid", form);

	}

	@Override
	public void deletetype(FormData formData) throws Exception {
		List<String> ids = Arrays.asList(formData.getString("ids").split(","));
		daoSupport.delete("DictionaryMapper.deletetype", ids);
	}

	
	
	
}
