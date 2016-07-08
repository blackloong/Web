package com.fq.service;

import java.util.List;
import java.util.Map;

import com.fq.util.FormData;

public interface RoleService {

	List<Map<String, String>> getAllRole()throws Exception;
	
	void insertRole (FormData formData) throws Exception;
	
	void updateRole (FormData formData)throws Exception;
	
	void deleteRoleByIds(FormData formData)throws Exception;

	 Map<String, Object>   showAllProgram (FormData formData) throws Exception;
	
	 void deleteRoleAuthByRoleId(FormData formData) throws Exception;
	 
	 void insertRoleAuthByRoleId(FormData formData) throws Exception;
}
