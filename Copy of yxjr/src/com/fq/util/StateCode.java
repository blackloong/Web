package com.fq.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateCode {

	
	/**字段类型**/
	public static final String[] fieldType_key={"text","textarea","select","checkbox","radio","file"}; 
	public static final String[] fieldType_value={"文本","大文本","下拉框","多选框","单选框","附件上传"}; 
	
	
	
	/** 封装成list */
	public static List<Map<String, Object>> getStateList(String[] key,String[] value){
		
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(key!=null&&value!=null&&key.length==value.length){
			for(int i=0;i<key.length;i++){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("key", key[i]);
				map.put("value", value[i]);
				list.add(map);
			}
		}
		return list;
	}
 
	
}
