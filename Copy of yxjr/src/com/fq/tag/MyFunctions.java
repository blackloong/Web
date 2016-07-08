package com.fq.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.util.CollectionUtils;

import com.fq.util.db.DBUtil;

public class MyFunctions extends SimpleTagSupport {

	public static String loadSelect(String tableName, String key, String text,
			String where, String id) throws JspException, IOException {
		String content = "";

		List<List<String>> data;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("select ").append(key).append(",").append(text)
					.append(" from ").append(tableName).append(" where 1=1 ")
					.append(where);

			System.out.println(sql.toString());

			List<Map<String, Object>> list = DBUtil.query2List(sql.toString());
			StringBuilder builder = new StringBuilder();
			if (!CollectionUtils.isEmpty(list)) {
				builder.append("<option").append(" value='").append("")
						.append("'>").append("--请选择--").append("</option>");
				for (Map<String, Object> map : list) {
					builder.append("<option").append(" value='")
							.append(map.get(key)+"'");
					
					if (map.get(key).equals(id)) {
						builder.append(" selected=\"selected\" ");
					}
					builder.append(">").append(map.get(text))
							.append("</option>");
				}
			}
			return builder.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void doTag() throws JspException, IOException {

		try {
			PageContext pc = (PageContext) getJspContext();
			JspWriter jspWriter = pc.getOut();
			String content = "";
			List<List<String>> data;
			StringBuilder sql = new StringBuilder();
			sql.append("select ").append(key).append(",").append(text)
					.append(" from ").append(tableName).append(" where 1=1 ")
					.append(where);
			List<Map<String, Object>> list = DBUtil.query2List(sql.toString());
			String pid=strPid(tableName,id,key);
			StringBuilder builder = new StringBuilder();
			if (!CollectionUtils.isEmpty(list)) {
				builder.append("<option").append(" value='").append("")
						.append("'>").append("--请选择--").append("</option>");
				for (Map<String, Object> map : list) {
					builder.append("<option").append(" value='")
							.append(map.get(key)+"'");
					
					if (map.get(key).toString().equals(pid)||map.get(key)==pid) {
						builder.append(" selected=\"selected\" ");
					}
					builder.append(">").append(map.get(text))
							.append("</option>");
				}
			}
			jspWriter.print(builder.toString()+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String tableName;
	private String key;
	private String text;
	private String where;
	private String id;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String strPid(String tablename,String id,String key) throws Exception{
		String ss[] =null;
		if(id!=null&&!"".equals(id)){
			ss=id.split("_");
		}
		if(ss!=null&&ss.length==2&&!"".equals(ss[0])){
			if(key.equals(ss[1])){
				return ss[0];
			}
		String sql="SELECT * FROM "+tablename+" where id="+ss[0];
		List<Map<String, Object>> list = DBUtil.query2List(sql);
		if(list!=null&&list.size()>0){
			return list.get(0).get(ss[1])+"";
		}
		}
		return null;
	}
	
}
