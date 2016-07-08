package com.fq.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fq.dao.DaoSupport;
import com.fq.service.RoleService;
import com.fq.util.FormData;

@Service
public class RoleServiceImple implements RoleService {

	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
	@Override
	public List<Map<String, String>> getAllRole() throws Exception {
		
		return (List<Map<String, String>>) daoSupport.findForList("RoleMapper.getAllRole", null);
	}

	@Override
	public void insertRole(FormData formData) throws Exception {
		formData.put("name", formData.getString("name"));
		daoSupport.save("RoleMapper.insertRole", formData);
	}

	@Override
	public void updateRole(FormData formData) throws Exception {
		formData.put("name",formData.getString("name"));
		daoSupport.update("RoleMapper.updateRoleById", formData);
	}

	@Override
	public void deleteRoleByIds (FormData formData)throws Exception {
		List<String> ids = Arrays.asList(formData.getString("ids").split(","));
		daoSupport.delete("RoleMapper.deleteRoleByIds", ids);
	}

	@Override
	public Map<String, Object> showAllProgram(FormData formData) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> program = (List<Map<String, Object>>) daoSupport.findForList("RoleMapper.getAuthDetailByRoleId", formData);
		List<Map<Object,Object>> teams = duplicateData(program, "teamid", "team_name", null,null);
		result.put("teams", teams);
		List<Map<Object,Object>> items = duplicateData(program, "itemid", "item_name", "teamid",null);
		result.put("items", items);
		List<Map<Object, Object>> details =  duplicateData(program, "detailid", "detail_name", "itemid","program_detail_id");
		result.put("details", details);
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
	public static void main(String[] args) {
		System.out.println("123213".split("-")[0]);
	}

	@Override
	public void deleteRoleAuthByRoleId(FormData formData) throws Exception {
		daoSupport.delete("RoleMapper.deleteRoleAuthByRoleId", formData);
	}

	@Override
	public void insertRoleAuthByRoleId(FormData formData) throws Exception {
		List<Map<String, String>> res = new ArrayList<Map<String,String>> ();
		String ids = formData.getString("ids");
		String roleId = formData.getString("roleId");
		String[] id = ids.split(",");
		for (String i :id) {
			Map<String, String> r = new HashMap<String, String> ();
			r.put("roleId", roleId);
			r.put("detailid", i);
			res.add(r);
		}
		daoSupport.batchSave("RoleMapper.insertRoleAuthByRoleId", res);
	}
}










