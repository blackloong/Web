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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">申请详情</strong> </div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <!-- <a type="button" class="am-btn am-btn-default" href="prodouct_edit"><span class="am-icon-plus"></span> 新增</a>
              <a type="button" class="am-btn am-btn-default" href="javascript:prodouct_delete();"><span class="am-icon-trash-o"></span> 删除</a> -->
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
			  <th class="table-title">申请编号</th>			  		  
			  <th class="table-title">预约编号</th>
              <th class="table-title">产品名称</th>
              <th class="table-title">借款用途</th>
              <th class="table-type">货款金额</th>
               <th class="table-type">预约人姓名 </th>
              <th class="table-type">申请日期 </th>
              <th class="table-type">门店名字</th>
              <th class="table-type">流程状态 </th>
               </tr>
          </thead>
          <tbody>
          <c:forEach items="${data}" var="item">
          	<tr>
              <td >${item.id }</td>
              <td>${item.sq_code}</td>
              <td>${item.loan_code}</td>
              <td>${item.pro_name}</td>              
              <td>			   
               
               <c:if test="${item.loan_use == 1  }">购物</c:if>
               <c:if test="${item.loan_use == 2  }">教育</c:if>
               <c:if test="${item.loan_use == 3 }">结婚</c:if>
               <c:if test="${item.loan_use == 4  }">经营周转</c:if>
               <c:if test="${item.loan_use == 5  }">旅游</c:if>
               <c:if test="${item.loan_use == 6 }">其他</c:if>
               <c:if test="${item.loan_use == 7  }">医疗</c:if>
               <c:if test="${item.loan_use == 8  }">装修</c:if>
                </td>
              <td>${item.loan_amount }</td>
              <td>${item.loan_name}</td>
              <td>${item.sq_date}</td>
              <td>${item.shopName}</td>
              <td>${item.state}</td>
       
                
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