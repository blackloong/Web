package com.fq.tag;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.fq.dao.DaoSupport;
import com.fq.util.FormData;


@Repository("myFunctionsService")
public class MyFunctionsService {

	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
	
	
	private static final String KEY="id";
	private static final String TEXT="value";
	
	
	public  String loadSelectMap(String tableName,String key,String text){
		return loadSelectMap(tableName, key, text, null);
	}
	
	
	public  String loadSelectMap(String tableName,String key,String text,String where){
	try {
		StringBuilder sql = new StringBuilder();
		sql.append("select ").append(key).append( " as key, ").append(text)
					.append(" as text ").append(" from ").append(tableName).append(" where 1=1");
		if(StringUtils.isNotBlank(where)){
			sql.append(" and ").append(where);
		}
		FormData formdata=new FormData();
		formdata.put("sql", sql);
		List<Map<String,String>> list= (List<Map<String, String>>) daoSupport.findForList("DictionaryMapper.tagDic", formdata);

		StringBuilder builder = new StringBuilder();
		if(!CollectionUtils.isEmpty(list)){
			builder.append("<option").append(" value='").append("").append("'>")
			.append("--请选择--").append("</option>");
			for(Map<String,String> map : list){
				builder.append("<option").append(" value='").append(map.get(KEY)).append("'>")
				.append(map.get(TEXT)).append("</option>");
			}
		}
		return builder.toString();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		return null;
	}

	@SuppressWarnings("unchecked")
	public  String loadSelectSql(String sql) {
		
		try {
			FormData formdata=new FormData();
			formdata.put("sql", sql);
			List<Map<String, String>> list =  (List<Map<String, String>>) daoSupport.findForList("DictionaryMapper.tagDic", formdata);
			StringBuilder builder = new StringBuilder();
			if(!CollectionUtils.isEmpty(list)){
				builder.append("<option").append(" value='").append("").append("'>")
				.append("--请选择--").append("</option>");
				for(Map<String,String> map : list){
					builder.append("<option").append(" value='").append(map.get(KEY)).append("'>")
					.append(map.get(TEXT)).append("</option>");
				}
			}
			return builder.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
}
