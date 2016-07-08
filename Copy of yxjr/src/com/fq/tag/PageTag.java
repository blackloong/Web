package com.fq.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.fq.util.PageInfo;

public class PageTag extends SimpleTagSupport {
/*
 *          
<div class="am-cf">
  共 15 条记录
  <div class="am-fr">
    <ul class="am-pagination">
      <li class="am-disabled"><a href="#">«</a></li>
      <li class="am-active"><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li><a href="#">»</a></li>
    </ul>
  </div>
</div>
(non-Javadoc)
 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
 */
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) ((PageContext) this.getJspContext()).getRequest();
		PageInfo page = (PageInfo) request.getAttribute("page");
		if (page == null) {
			return;
		}
		PageContext pc = (PageContext) getJspContext();
		JspWriter jspWriter = pc.getOut();
		int totalPage = page.getTotalPage();
		int currentPage = page.getCurrentPage();
		if (currentPage < 1) {
			currentPage = 1;
		}
		String content = "<div class=\"am-cf\">  共 " + page.getTotalSize() + " 条记录&nbsp;共" + totalPage + "页&nbsp;当前第"
				+ currentPage + "页  <div class=\"am-fr\"> " + "<ul class=\"am-pagination\">      ";
		if (currentPage > 1) {
			content += "  <li  > <a href=\"javascript:goPage(" + (currentPage - 1) + ")\">上一页</a></li>";
		}
		for (int i = 0; i < totalPage; i++) {
			if (1 == totalPage) {
				content += "  <li  class=\"am-active\"> <a href=\"javascript:void(0);\">" + (i + 1) + "</a></li>";
			} else {
				if (0 == i) {
					if (currentPage == (i + 1)) {
						content += "  <li  class=\"am-active\"> <a href=\"javascript:void(0);\">首页</a></li>";
					} else {
						content += "  <li > <a href=\"javascript:goPage(" + (i + 1) + ")\">首页</a></li>";
					}
				} else if (i == totalPage - 1) {
					if (currentPage == (i + 1)) {
						content += "  <li  class=\"am-active\"> <a href=\"javascript:void(0);\">末页</a></li>";
					} else {
						content += "  <li > <a href=\"javascript:goPage(" + (i + 1) + ")\">末页</a></li>";
					}
				} /*else if (currentPage == (i + 1)) {
					content += "  <li  class=\"am-active\"> <a href=\"javascript:void(0);\">" + (i + 1) + "</a></li>";
				}*/
				 else if (currentPage == (i + 1)) {
				content += "  <li  class=\"am-active\"> <a href=\"javascript:void(0);\">" + (i + 1) + "</a></li>";
			  } 
			}
		}
		if (currentPage < totalPage) {
			content += "  <li  > <a href=\"javascript:goPage(" + (currentPage + 1) + ")\">下一页</a></li>";
		}
		content += " </ul>" + "  </div>" + "</div>";
		jspWriter.print(content);
		super.doTag();
	}

	
	
}
