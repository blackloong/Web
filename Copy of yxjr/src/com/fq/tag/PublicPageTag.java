package com.fq.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.fq.util.PageInfo;

public class PublicPageTag extends SimpleTagSupport {
	/*
	 *          
	<div class="paging t-c"><a href="javascript:">1</a><a href="javascript:" class="current">2</a><span class="p-l-15 p-r-15">共356条记录</span></div>
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
			
			
			
			/*
			 <div class="paging t-c"><a href="<%=basePath %>public_gm_sp_list?type=${type}&currentPage=1">首页</a>
			<c:choose>
				<c:when test="${page.currentPage>1 }"><a href="<%=basePath %>public_gm_sp_list?type=${type}&currentPage=${page.currentPage-1 }">上一页</a></c:when>
				<c:otherwise><a href="javascript:; ">上一页</a></c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${page.totalPage }" var="number">
					  <a href="<%=basePath %>public_gm_sp_list?type=${type}&currentPage=${number }"  <c:if test="${page.currentPage==number }">class="current" </c:if> >${number }</a>
			</c:forEach>
			<c:choose>
				<c:when test="${page.currentPage<page.totalPage}"><a href="<%=basePath %>public_gm_sp_list?type=${type}&currentPage=${page.currentPage+1 }">下一页</a></c:when>
				<c:otherwise><a href="javascript:;">下一页</a></c:otherwise>
			</c:choose>
			
			<a href="<%=basePath %>public_gm_sp_list?type=${type}&currentPage=${page.totalPage }">尾页</a><span class="p-l-15 p-r-15">共${page.totalPage }页</span>
			<div class="inline-block paging-select m-t-30 m-b-40">
			
				<select  onchange="fenye(this)">
					<c:forEach begin="1" end="${page.totalPage }" var="number">
					  <option  <c:if test="${page.currentPage==number}">selected="selected" </c:if> value="${number }">${number }</option>
					</c:forEach>
				</select>
				</div>
			</div>
			*/
			
			
			String content = "<div class=\"paging t-c\">";
			int totalPage = page.getTotalPage();
			for(int i = 0 ; i < totalPage ; i ++){
				if(page.getCurrentPage() == (i+1))content +="<a href=\"#\">"+(i+1)+"</a>";
				else content +="<a href=\"javascript:goPage("+(i+1)+");\" class=\"current\">"+(i+1)+"</a>";
			}
			content +="<span class=\"p-l-15 p-r-15\">共"+page.getTotalSize()+"条记录</span></div>";
			jspWriter.print(content);
			super.doTag();
		}

		
		
	}
