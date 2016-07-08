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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">消息管理</strong> / <small>Table</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
            <!--   <a type="button" class="am-btn am-btn-default" href="editMessage"><span class="am-icon-plus"></span> 新增</a> -->
             </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
          
          <form action="MessageList" method="post" id="queryForm">
					<table style="margin-left: 10px;">
						<tr>
							<td nowrap="nowrap" style="padding-left: 10px;"><input
								type="text" name="uname" value="${uname }"
								class="am-form-field" placeholder="用户">
							</td>
							 
							<td nowrap="nowrap"
								style="padding-left: 100px;text-align: center;"><span
								class="am-input-group-btn">
									<button class="am-btn am-btn-default" type="button"
										onclick="$('#queryForm').submit();">搜索</button> </span></td>
						</tr>
					</table>
				</form>
 
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
                <th class="table-title">用户</th>
                <th class="table-type">内容</th>
                <th class="table-set">消息类型</th>
                <th class="table-set">推送类型</th>
                 <!-- <th class="table-set">操作</th> -->
                   
              </tr>
          </thead>
          <tbody>
          
          <c:forEach items="${pageData }" var="item">
          	<tr>
          	  <td>${item.id}</td> 
          	  <td>${item.uname}</td> 
              <td>${item.content}</td>
              <td>${item.messagetype}</td>
              <td>${item.pushtype}</td>
                <%--    <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="editMessage?id=${item.id }"><span class="am-icon-pencil-square-o"></span>编辑</a>
                    <a  class="am-btn am-btn-default" style="background-color: #fff;" href="delMessage?id=${item.id }"><span class="am-icon-trash-o"></span>删除</a>
                    
                  
                  </div>
                </div>
              </td> --%>
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