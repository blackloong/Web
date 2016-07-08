package com.fq.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.fq.util.PageInfo;

public class WebPageTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		HttpServletRequest request = (HttpServletRequest)((PageContext)this.getJspContext()).getRequest();
		PageInfo page = (PageInfo) request.getAttribute("page");
		if(page == null ){
			return;
		}
		PageContext pc = (PageContext) getJspContext();
		JspWriter jspWriter = pc.getOut();
		String html = "<a href=\"javascript:goPage("+1+")\">首页</a>";
		int currentPage = page.getCurrentPage();
		if(currentPage>1){
			html += "<a href=\"javascript:goPage("+(currentPage-1)+")\">上一页</a>";
		}
		int totalPage = page.getTotalPage();
		for (int i = 0 ; i < totalPage ; i ++ ){
			if(currentPage == (i +1) ) {
				html += "<a href=\"javascript:\" class=\"current\">"+(i+1)+"</a>";
			}else{
				html += "<a href=\"javascript:goPage("+(i+1)+")\">"+(i+1)+"</a>";
			}
		}
		if(currentPage<totalPage)html += "<a href=\"javascript:goPage("+(currentPage+1)+")\">下一页</a>";
		html +=  "<a href=\"javascript:goPage("+totalPage+")\">尾页</a>";
		html += "<span class=\"p-l-15 p-r-15\">共"+totalPage+"页</span>";
		html +="<div class=\"inline-block paging-select m-t-30 m-b-40\"><select class=\"js-select\" onchange=\"selectGoPage(this);\">";
		for (int i = 0 ; i <totalPage; i ++ ){
			if(currentPage == (i+1))
				html += "<option value=\""+(i+1)+"\" selected=\"selected\">"+(i+1)+"</option>";
			else
				html += "<option value=\""+(i+1)+"\" >"+(i+1)+"</option>";
		}
		html +="</select></div>";
		jspWriter.print(html);
		super.doTag();
	}
/*
 * 
				</select>
			</div>
 */
	
}
