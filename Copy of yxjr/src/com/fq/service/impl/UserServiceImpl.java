package com.fq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fq.dao.DaoSupport;

import com.fq.service.UserService;

import com.fq.util.DateUtil;
import com.fq.util.FormData;
import com.fq.util.PageInfo;
import com.fq.util.PassWordUtil;

@Service("userService")
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

	@Resource(name="daoSupport")
	private DaoSupport daoSupport;

	
	@Override
	public Map<String, Object> getUserByMobileAndPassWord(
			FormData formData) throws Exception {
		//formData.put("password", PassWordUtil.MD5(formData.getString("password")));
		return  (Map<String, Object>) daoSupport.findForObject("UserMapper.getUserByMobileAndPassWord", formData);
	}

	@Override
	public Map<String, Object> getRoleAuthByRoleId(FormData formData)
			throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> program = (List<Map<String, Object>>) daoSupport.findForList("UserMapper.getRoleAuthByRoleId", formData);
		List<Map<Object,Object>> teams = duplicateData(program, "teamid", "team_name",null,null);
		result.put("teams", teams);
		List<Map<Object,Object>> items = duplicateData(program, "itemid", "item_name", "item_url","teamid");
		result.put("items", items);
		return result;
	}
	private static List<Map<Object,Object>> duplicateData (List<Map<String, Object>> program,String key1 ,String key2 ,String key3,String key4){
		List<Map<Object, Object>> result = new ArrayList<Map<Object,Object>> ();
		Map<Object, Object> reultMap = new HashMap<Object, Object>();
		if(program != null ){
			for (Map<String, Object> map : program){
				String value = "";
				if(map.get(key1)== null || map.get(key1).toString().equals(""))continue;
				if(map.get(key2)!= null){
					value +=map.get(key2);
				}
				if(map.get(key3)!= null){
					value +="-"+map.get(key3);
				}
				if(map.get(key4)!= null){
					value +="-"+map.get(key4);
				}
				reultMap.put(map.get(key1), value);
			} 
		}
		for(Object key : reultMap.keySet()){
			Map<Object, Object> addMap = new HashMap<Object, Object> ();
			Object value = reultMap.get(key);
			addMap.put(key1, key);
			String[] vals = value == null?new String[]{""}:value.toString().split("-"); 
			if(vals.length>=1)addMap.put(key2, vals[0]);
			if(vals.length >= 2 ){
				addMap.put(key3, vals[1]);
			}
			if(vals.length>=3){
				addMap.put(key4, vals[2]);
			}
			result.add(addMap);
		}
		return result;
	}

	@Override
	public List<Map<String, String>> getAllowUrlByRoleId(FormData formData)
			throws Exception {
		return (List<Map<String, String>>) daoSupport.findForList("UserMapper.getAllowUrlByRoleId", formData);
	} 
	@Override
	@SuppressWarnings("unchecked")
	public List<FormData> getAllPage(PageInfo page) throws Exception{
		return (List<FormData>) daoSupport.findForList("UserMapper.getAllLoginPage", page);
	}

	@Override
	public Map<String, Object> getUserByMobileAndPassword(String mobile,
			String password) throws Exception {
		Map<String, String> data = new HashMap<String, String>();
		data.put("mobile", mobile);
		data.put("password", password);
		return (Map<String, Object>) daoSupport.findForObject("UserMapper.getUserByMobileAndPassword", data);
	}

	@Override
	public Map<String, String> getUserById(String id) throws Exception {
		return (Map<String, String>) daoSupport.findForObject("UserMapper.getUserById", id);
	}

	@Override
	public int saveUser(FormData form) throws Exception {
		String mobile=form.getString("mobile");
		 List<FormData> list=checkMobile(form);
			if(list!=null && list.size()>0){//该手机号存在
		}else{
			 form.put("password", PassWordUtil.MD5(form.getString("password")));
			 form.put("regtime", DateUtil.getTime());//设置默认密码 
			//form.put("password", PassWordUtil.MD5(mobile.substring(mobile.length()-6, mobile.length())));
		  daoSupport.save("UserMapper.saveUser", form);
		}
		 return 0;
	}
	
	@Override
	public int saveWeixinUser(FormData form) throws Exception {
		 List<FormData> list=checkMobile(form);
			if(list!=null && list.size()>0){//该手机号存在
				
		}else{
			 //form.put("password", PassWordUtil.MD5(form.getString("password")));
			 form.put("regtime", DateUtil.getTime());//设置默认密码 
			//form.put("password", PassWordUtil.MD5(mobile.substring(mobile.length()-6, mobile.length())));
		  daoSupport.save("UserMapper.saveUser", form);
		}
		 return 0;
	}
	
	@Override
	public void updateUser (FormData form)throws Exception{
		daoSupport.update("UserMapper.updateUserById", form);
	//	daoSupport.update("UserMapper.updateUserInfoById", form);
	}
	
	@Override
	public int updatewxUser (FormData form)throws Exception{
 	//	daoSupport.update("UserMapper.updateUserInfoById", form);
		return (Integer) daoSupport.update("UserMapper.updateUserBywx", form);
	}
	
	@Override
	public int updateMobUser (FormData form)throws Exception{
	 	//	daoSupport.update("UserMapper.updateUserInfoById", form);
			return (Integer) daoSupport.update("UserMapper.updateUserByMob", form);
		}
	
	public void updateUserInfo (FormData form)throws Exception{
		daoSupport.update("UserMapper.updateUser", form);
		//daoSupport.update("UserMapper.updateUserInfo", form);
	}

	@Override
	public void delUser(List<Integer> ids) throws Exception {
		daoSupport.delete("UserMapper.deleteUserById", ids);
	}

	@Override
	public void updateUserImg(String id, String fileSrc) throws Exception {
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", id);
		data.put("filesrc", fileSrc);
		daoSupport.update("UserMapper.updateUserImg", data);
	}
	
	//验证手机号码是否唯一
	@Override
	@SuppressWarnings("unchecked")
	public List<FormData> checkMobile(FormData form) throws Exception {
		
		List<FormData>  data=(List<FormData>) daoSupport.findForList("UserMapper.checkMobile", form);
		return data;
	}


	public Map<String, Object> getUserInfoById(String id) throws Exception {
		// TODO Auto-generated method stub
		return (Map<String, Object>) daoSupport.findForObject("UserMapper.getUserInfoById", id);
	}

	

	
	@Override
	public List<Map<String, Object>> getSelectUser(PageInfo page)
			throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("UserMapper.getSelectUserByPage", page);
	}
	@Override
	public Map<String, String> Checksours(FormData form) throws Exception {
		return (Map<String, String>) daoSupport.findForObject("UserMapper.Checksours", form);
	}
	//重置用户密码
		@Override
		public void UpdateUserPwd(FormData form) throws Exception {
			daoSupport.update("UserMapper.UpdateUserPwd", form);
		}
		
		//删除验证码
		@Override
		public void delYzm(FormData form) throws Exception {
			daoSupport.delete("UserMapper.deleteYzm", form);
		}
		//存储验证码
		@Override
		public int saveyzm(FormData form) throws Exception {
			daoSupport.save("UserMapper.AddYzm", form);
			return 0;
		}

		@Override
		public int insertLat(FormData form) throws Exception {
			return (Integer) daoSupport.save("UserMapper.insertLat", form);
			
		}

		@Override
		public List<FormData> dotList(FormData form) throws Exception {
			List<FormData>  data=(List<FormData>) daoSupport.findForList("UserMapper.dotList", form);

			return data;
		}

		@Override
		public List<FormData> dotderList(FormData form) throws Exception {
			List<FormData>  data=(List<FormData>) daoSupport.findForList("UserMapper.dotderList", form);

			return data;
		}

		@Override
		public List<FormData> getAllEmp(FormData form) throws Exception {
			List<FormData>  data=(List<FormData>) daoSupport.findForList("UserMapper.getAllEmp", form);
			return data;
		}
		@Override
		public List<Map<String, Object>> dotLists(FormData form)
				throws Exception {
			List<Map<String, Object>>  data=(List<Map<String, Object>>) daoSupport.findForList("UserMapper.dotderList", form);
			return data;
		}

		@Override
		public Map<String, Object> MChecksours(FormData form) throws Exception {
			return (Map<String, Object>) daoSupport.findForObject("UserMapper.Checksours", form);
		}

		@Override
		public List<Map<String, Object>> getAllowDown(FormData formData)
				throws Exception {
			return (List<Map<String, Object>>) daoSupport.findForList("UserMapper.getAllowDown", formData);

		}

		@Override
		public List<FormData> cityList(FormData form) throws Exception {
			List<FormData>  data=(List<FormData>) daoSupport.findForList("UserMapper.cityList", form);
			return data;
		}
		
	 
		//删除验证码
				@Override
				public void delwx(FormData form) throws Exception {
					daoSupport.delete("UserMapper.deletewx", form);
				}
		
}
