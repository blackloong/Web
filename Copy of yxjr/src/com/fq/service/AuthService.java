package com.fq.service;

import java.util.List;
import java.util.Map;

import com.fq.util.FormData;
import com.fq.util.PageInfo;

public interface AuthService {
	
	List<Map<String, String>> getAllProgramTeamByPage(PageInfo page)throws Exception;

	List<Map<String, String>> getAllProgramItem()throws Exception;
	
	List<Map<String, String>> getALLDetailByItemId(FormData formData)throws Exception;
	
	void insertProgramTeam(String name) throws Exception;
	
	void updateProgramTeamById (FormData formData) throws Exception;
	
	void insertProgramItemByTeamId (FormData formData) throws Exception;
	
	void updateProgramItemById (FormData formData) throws Exception;
	
	void insertProgramDetailByItemId (FormData formData)throws Exception;
	
	void updateDetailById (FormData formData)throws Exception;
	
	void deleteDetailById (FormData formData) throws Exception;
	
	
	
}
