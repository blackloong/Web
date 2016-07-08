package com.fq.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.fq.util.db.DBUtil;

public class WebChooseTag extends SimpleTagSupport {

	private String sql ;

	private String name;
	
	
	@Override
	public void doTag() throws JspException, IOException {
		String html = "";
		try {
			List<List<String>> data = DBUtil.queryList(sql);
			for (int i =  0 ; i < data.size() ; i++ ){
				List<String> row  =  data.get(i);
				String v = row.get(0);
				String c = row.get(1);
				html += "<a href=\"javascript:\"  onclick=\"query('"+v+"','"+name+"',this)\">"+c+"</a>";
			}
			PageContext pc = (PageContext) getJspContext();
			pc.getOut().print(html);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.doTag();
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
