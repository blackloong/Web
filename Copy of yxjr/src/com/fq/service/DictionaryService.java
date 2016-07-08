package com.fq.service;

import java.util.List;
import java.util.Map;

import com.fq.util.FormData;
import com.fq.util.PageInfo;

public interface DictionaryService {


	List<FormData> findDictionaryPage(PageInfo page) throws Exception ;
	Map<String, Object> findDictionaryId(FormData form) throws Exception ;
	void updateDictionary(FormData form)throws Exception;
	void insertDictionary(FormData form)throws Exception;
	
	List<Map<String, String>>findDictionaryPid(FormData form) throws Exception ;
	void deleteDictionary (FormData formData) throws Exception; 
	
	
	List<FormData> findtypePage(PageInfo page) throws Exception ;//分类维护
	Map<String, Object> findtypeId(FormData form) throws Exception ;
	
	void updatetype(FormData form)throws Exception;
	void inserttype(FormData form)throws Exception;
	
	List<Map<String, String>> findtypePid(FormData form) throws Exception ;
	void deletetype(FormData formData) throws Exception;
	
	 
}
