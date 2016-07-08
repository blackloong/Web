package com.fq.service;

import java.util.Map;

import com.fq.util.FormData;

public interface TokenService {
	/**
	 * 验证token
	 * @param formData
	 * @return
	 * @throws Exception
	 */
	boolean validateTokenByUidAndToken (FormData formData)throws Exception;
	/**
	 * 生成token
	 * @param formData
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> createTokenByUidAndTokenStr (FormData formData) throws Exception;
}
