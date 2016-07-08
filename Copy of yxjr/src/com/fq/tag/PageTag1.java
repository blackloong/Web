package com.fq.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.fq.util.PageInfo;

public class PageTag1 extends SimpleTagSupport {
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
		HttpServletRequest request = (HttpServletRequest)((PageContext)this.getJspContext()).getRequest();
		PageInfo page = (PageInfo) request.getAttribute("page");
		if(page == null ){
			return;
		}
		PageContext pc = (PageContext) getJspContext();
		JspWriter jspWriter = pc.getOut();
		String content = "<div class=\"am-cf\">  共 "+page.getTotalSize()+" 条记录  <div class=\"am-fr\"> " +
				"<ul class=\"am-pagination\">      " ;
		int totalPage = page.getTotalPage();
		for(int i = 0 ; i < totalPage ; i ++){
			if(page.getCurrentPage() == (i+1))content += 	"  <li  class=\"am-active\"> <a href=\"#\">"+(i+1)+"</a></li>";
			else content += 	"  <li > <a href=\"javascript:goPage("+(i+1)+")\">"+(i+1)+"</a></li>";
		}
		content +=			" </ul>" +
					"  </div>" +
					"</div>";
		jspWriter.print(content);
		super.doTag();
	}

	
	
}
