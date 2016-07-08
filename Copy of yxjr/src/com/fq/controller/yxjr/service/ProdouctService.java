package com.fq.controller.yxjr.service;

import java.util.List;
import java.util.Map;

import com.fq.util.FormData;
import com.fq.util.PageInfo;

public interface ProdouctService {

	List<Map<String, Object>> findProdouctPage(PageInfo page)throws Exception;
	
	Map<String, Object> findProdouctId(FormData form)throws Exception;
	
	void insertProdouct(FormData form)throws Exception;
	
	void updateProdouct(FormData form)throws Exception;
	
	void deleteProdouctIds(FormData formData)throws Exception;
	
	void prodouctFbXj(FormData form)throws Exception;
	
	
	/**
	 * 扩展字段
	 */
	List<Map<String, Object>> findField(FormData form)throws Exception;
	Map<String, Object> findFieldId(FormData form)throws Exception;
    void updateField(FormData form)throws Exception;
    void insertField(FormData form)throws Exception;
	void deleteField(FormData formData)throws Exception;
	
	List<Map<String, Object>>prodouctlist(FormData form) throws Exception ;//产品 下商品
	
	
}
