package com.fq.service;

import java.util.List;
import java.util.Map;


import com.fq.util.FormData;
import com.fq.util.PageInfo;
public interface UserService {

	Map<String, Object> getUserByMobileAndPassWord (FormData formData)throws Exception ;
	List<Map<String, String>> getAllowUrlByRoleId(FormData formData) throws Exception;
	Map<String, Object>  getRoleAuthByRoleId (FormData formData) throws Exception ;
	List<FormData> getAllPage(PageInfo page) throws Exception;
	Map<String, String> getUserById (String id)throws Exception;
	int saveUser (FormData form) throws Exception;
	int saveWeixinUser (FormData form) throws Exception;//微信授权
	void updateUser (FormData form) throws Exception;
	int updatewxUser (FormData form) throws Exception;//绑定手机号
	int updateMobUser (FormData form) throws Exception;//绑定手机号
	void delUser (List<Integer> ids) throws Exception;
	void updateUserImg(String id,String fileSrc)throws Exception;
	List<Map<String, Object>> getSelectUser (PageInfo page) throws Exception ; 
	
	List<FormData> checkMobile(FormData form) throws Exception;
	Map<String, Object> getUserByMobileAndPassword(String mobile,String password)throws Exception;
	

	Map<String, String> Checksours(FormData form) throws Exception;//检验验证码是否正确
	Map<String, Object> MChecksours(FormData form) throws Exception;//检验验证码是否正确
	
	void UpdateUserPwd(FormData form) throws Exception;

	void delYzm(FormData form) throws Exception;
	
	int saveyzm(FormData form) throws Exception;
	int insertLat(FormData form) throws Exception;//获取用户轨迹
	
	List<FormData> dotList(FormData form) throws Exception;//服务网点查询 
	List<Map<String, Object>> dotLists(FormData form) throws Exception;//服务网点查询 

	
	List<FormData> dotderList(FormData form) throws Exception;//服务网点详情查询
	
	List<FormData> getAllEmp(FormData form) throws Exception;//用户轨迹图
	
	
	List<Map<String, Object>> getAllowDown(FormData formData) throws Exception;

	
	List<FormData> cityList(FormData form) throws Exception;//字典数据查询

	void delwx(FormData form) throws Exception;

	
}
