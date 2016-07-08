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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">授信申请列表</strong> </div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
<%--                <a type="button" class="am-btn am-btn-default" href="<%=basePath %>sqjdListdown"><span class="am-icon-plus"></span>下载</a>
 --%>           <!--    <a type="button" class="am-btn am-btn-default" href="javascript:prodouct_delete();"><span class="am-icon-trash-o"></span> 删除</a> -->
            </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
          <%-- 	<form action="sqjdList" method="post" id="queryForm">
	            <input type="text" name="loan_name" placeholder="预约人姓名" value="${loan_name }" class="am-form-field">
	        </form>
	                <span class="am-input-group-btn">
	                  <button class="am-btn am-btn-default" type="button" onclick="$('#queryForm').submit();">搜索</button>
	                </span> --%>
       
          </div>
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main"  >
            <thead>
              <tr> 
			  <th class="table-title">id</th>
			  <th class="table-title">手机号</th>			  		  
			  <th class="table-title">授信编号</th>
              <th class="table-title">状态</th>
              <th class="table-title">申请额度</th>
              <th class="table-type">申请日期</th>
               <th class="table-type">授信额度</th>
               <th class="table-type">有效开始时间</th>
              <th class="table-type">有效结束时间 </th>
               </tr>
          </thead>
          <tbody>
          <c:forEach items="${data}" var="item">
          	<tr>
              <td >${item.id }</td>
              <td>${item.mobile}</td>
              <td>${item.number}</td>
              <td>
                ${item.state}
                 </td>              
              <td>			   
              ${item.applyMoney}
                </td>
              <td>${item.applyDate }</td>
              <td> 
                 ${item.money} 
              </td>
              <td>${item.startDate}</td>
              <td>${item.endDate}</td>
              
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

<footer>
  <hr>
  <p class="am-padding-left"><!--© 2014 AllMobilize, Inc. Licensed under MIT license. <a href="http://www.mycodes.net/" target="_blank">源码之家</a> --></p>
</footer>


</body>
</html>