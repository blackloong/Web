<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<div class="am-cf admin-main" style="margin-left: 5px;">
  <!-- sidebar start -->
  <%@include file = "../leftMenu.jsp"%>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表格</strong> / <small>Table</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <a type="button" class="am-btn am-btn-default" href="helpEdit"><span class="am-icon-plus"></span> 新增</a>
             </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
          	<form action="helplist" method="post" id="queryForm">
          	<table style="margin-left: 10px;">
          	  <tbody>
					<tr >
					<td nowrap="nowrap" style="padding-left: 10px;">
					
					<select name="type">
				          <option   value="">全部</option>
					
		                <c:forEach items="${brandList }" var="item">
		                       <option <c:if test="${type==item.id }">selected="selected"</c:if>  value="${item.id }">${item.title }</option>
		                
		                </c:forEach>
                   </select>
 				    </td>
		 
					</tr>
				 
					</tbody>
			</table>
	        </form>
	                <span class="am-input-group-btn">
	                  <button class="am-btn am-btn-default" type="button" onclick="$('#queryForm').submit();">搜索</button>
	                </span>
       
			  
          </div>
        </div>
      </div>
    </div>

      
	 
    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          类型说明  1 电动车/自行车  2 摩托车  3 公共交通   4汽车
          
          <table class="am-table am-table-striped am-table-hover table-main"  >
            <thead>
              <tr>
                <th class="table-title">id</th>
                <th class="table-title">标题</th>
                <th class="table-title">类型</th>
               </tr>
          </thead>
          <tbody>
          <c:forEach items="${data }" var="item">
          	<tr>
          	  <td>${item.id}</td> 
              <td>${item.uname}</td>
              <td>${item.type}</td>
              
               
            </tr>
          </c:forEach>
          </tbody>
        </table>
       	<FQ:page/>
          <hr />
        </form>
      </div>

    </div>
  </div>
  <!-- content end -->
</div>

 


</body>
</html>