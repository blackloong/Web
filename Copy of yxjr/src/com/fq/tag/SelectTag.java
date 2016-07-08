package com.fq.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.fq.util.db.DBUtil;


public class SelectTag extends SimpleTagSupport {

	private String sql ;
	
	private String value;
	
	private String css ;
	
	private String name;
	
	private String onChange;
	
	private String id ;
	
	private String isQuery ;
	
	private String style;
	
	
	@Override
	public void doTag() throws JspException, IOException {
		String content = "<select name =\""+name+"\" id=\""+id+"\" onchange=\""+onChange+"\" class=\""+css+"\" style=\""+style+"\">";
		PageContext pc = (PageContext) getJspContext();
		if(isQuery != null && !"".equals(isQuery)){
			content += "<option value=\"\">全部 </option>";
		}
		JspWriter out = pc.getOut();
		List<List<String>> data;
		try {
			data = DBUtil.queryList(sql);
			for (int i =  0 ; i < data.size() ; i++ ){
				List<String> row  =  data.get(i);
				String selected = "";
				String v = row.get(0);
				String c = row.get(1);
				if(v != null ){
					if(v.equals(value)){
						selected = " selected=\"selected\"";
					}
				}
				content +="<option "+selected+" value=\""+v.trim()+"\">"+c.trim()+"</option>";
			}
			content  += "</select>";
			out.print(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getSql() {
		return sql;
	}


	public void setSql(String sql) {
		this.sql = sql;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getCss() {
		return css;
	}


	public void setCss(String css) {
		this.css = css;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOnChange() {
		return onChange;
	}


	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIsQuery() {
		return isQuery;
	}


	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}

	
	
	
}
