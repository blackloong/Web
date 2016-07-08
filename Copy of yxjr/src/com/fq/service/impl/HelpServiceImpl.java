package com.fq.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fq.dao.DaoSupport;
import com.fq.service.HelpService;
import com.fq.util.FormData;
import com.fq.util.PageInfo;
/***
 * 帮助中心
 * @author Admincx
 *
 */
@Service
@SuppressWarnings("unchecked")
public class HelpServiceImpl implements HelpService {
	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
	@Override
	public List<Map<String, Object>> getAllProductByPage(PageInfo page)
			throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("helpMapper.helpList", page);
	}

	@Override
	public void saveProduct(FormData formData) throws Exception {
		daoSupport.save("helpMapper.inserthelp", formData);
	}

	@Override
	public void updateProductById(FormData formData) throws Exception {
		daoSupport.update("helpMapper.updatehelp", formData);
	}

	@Override
	public void deleteProductById(FormData formData) throws Exception {
		daoSupport.delete("helpMapper.delhelp", formData);
	}

	@Override
	public Map<String, Object> getProductById(FormData formData)
			throws Exception {
 		return (Map<String, Object>) daoSupport.findForObject("helpMapper.byIdBrand", formData);
	}

	@Override
	public List<Map<String, String>> brandList(FormData form) throws Exception {
   		return (List<Map<String, String>>) daoSupport.findForList("helpMapper.brandList", form);
	}

	@Override
	public List<Map<String, Object>> feedbackList(FormData form)
			throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("helpMapper.feedbackList", form);

	}

	@Override
	public List<Map<String, Object>> mediaList(FormData form) throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("helpMapper.mediaList", form);

	}
	
	@Override
	public List<FormData> getMessageAllPage(PageInfo page) throws Exception {
 		return (List<FormData>) daoSupport.findForList("MessageMapper.getAllLoginPage", page);
	}


	@Override
	public Map<String, Object> messageCount(FormData formData) throws Exception {
		return (Map<String, Object>) daoSupport.findForObject("MessageMapper.messageCount", formData);
	}


	
}
