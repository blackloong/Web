package com.fq.service;

import java.util.List;
import java.util.Map;

import com.fq.util.FormData;
import com.fq.util.PageInfo;

public interface HelpService {

	/**
	 * 帮助 中心查询
	 * @param formData
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getAllProductByPage(PageInfo page)throws Exception;
	
	Map<String, Object> getProductById (FormData formData) throws Exception;
 	/**
 	 * 新增
 	 * @param formData
 	 * @throws Exception
 	 */
	void saveProduct(FormData formData) throws Exception;
	/**
	 * 更新 帮助中心 文章
	 * @param formData
	 * @throws Exception
	 */
	void updateProductById(FormData formData) throws Exception;
	/**
	 * 删除 帮助 中心 文章  
	 * @param formData
	 * @throws Exception
	 */
	void deleteProductById (FormData formData) throws Exception;
	

	/***
	 * 
	 * 帮助中心 
	 * @throws Exception
	 */
	
	List<Map<String, String>> brandList(FormData form) throws Exception;
	
	List<Map<String, Object>> feedbackList(FormData form) throws Exception;

	List<Map<String, Object>> mediaList(FormData form) throws Exception;

 
	List<FormData> getMessageAllPage(PageInfo page) throws Exception;
	
	 Map<String, Object> messageCount(FormData formData) throws Exception;//维度消息


}
