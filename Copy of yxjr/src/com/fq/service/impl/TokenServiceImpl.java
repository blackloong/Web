package com.fq.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fq.dao.DaoSupport;
import com.fq.service.TokenService;
import com.fq.util.FormData;

@Service
public class TokenServiceImpl implements TokenService {

	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
	@Override
	public boolean validateTokenByUidAndToken(FormData formData)
			throws Exception {
		Map<String, String> token = (Map<String, String>) daoSupport.findForObject("TokenMapper.getTokenByUidAndToken", formData);
		if(token == null || token.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Object> createTokenByUidAndTokenStr(FormData formData)
			throws Exception {
		daoSupport.delete("TokenMapper.deleteTokenByUid", formData);
		Map<String, Object> result = new HashMap<String, Object> ();
		daoSupport.save("TokenMapper.createTokenByUid", formData);
		result.put("token", formData.get("token"));
		return result;
	}

}
