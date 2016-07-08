package com.fq.controller.yxjr.service;

import java.util.List;
import java.util.Map;

import com.fq.util.FormData;
import com.fq.util.PageInfo;

public interface DotService {

	
	void insertDot(FormData form)throws Exception;
	void updateDot(FormData form)throws Exception;
	void deleteDot(FormData form)throws Exception;
	List<Map<String, Object>> findDotPage(PageInfo page)throws Exception;
	Map<String, Object> findDotId(FormData form)throws Exception; 
	
}
