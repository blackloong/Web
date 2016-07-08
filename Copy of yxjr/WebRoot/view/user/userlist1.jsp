<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/dic" prefix="dic" %>
<%@taglib prefix="FQ" uri="/fq" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% response.setContentType("application/vnd.ms-excel;charset=utf-8");
   response.setHeader("Content-disposition","attachment; filename=user.xls");  

 %> 
<!doctype html>
<html class="no-js" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel">
<head>
    <meta name=ProgId content=Excel.Sheet>  
    <meta name=Generator content="Microsoft Excel 11"> 
   
 <head>
 <body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<div class="am-cf admin-main" style="margin-left: 5px;">
 
  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户信息</strong> </div>
    </div>

    
    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table   border="1"  width="100%">
            <thead>
              <tr>
               <!--  <th class="table-check"><input type="checkbox" id="selectAllCheck"   /></th> -->
                <th class="table-title">姓名</th>  <th class="table-title">用户名</th><th class="table-title">手机号</th><th class="table-title">注册时间</th>
                <th class="table-type"> 角色</th><!-- <th class="table-set">操作</th> -->
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageData }" var="item">
          	<tr>
             <%--  <td ><input type="checkbox" value="${item.id }" /></td> --%>
              <td>${item.name }</td>
              <td>${item.username }</td>
              <td>${item.mobile }</td>
              <td>${item.regtime}</td>
              <td>${item.role_name }</td>
              <%-- <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="editUser?id=${item.id }"><span class="am-icon-pencil-square-o"></span>编辑</a>
                  </div>
                </div>
              </td> --%>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      <!--  	<FQ:page/> -->
          <hr />
        </form>
      </div>

    </div>
  </div>
  <!-- content end -->
</div>
<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
<footer>
  <hr>
  <p class="am-padding-left"><!--© 2014 AllMobilize, Inc. Licensed under MIT license. <a href="http://www.mycodes.net/" target="_blank">源码之家</a> --></p>
</footer>


</body>
</html>