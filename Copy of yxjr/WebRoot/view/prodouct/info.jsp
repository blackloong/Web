<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://com.supwisdom/jsp/jstl/myfunctions" prefix="myfn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>



<script type="text/javascript" >

	function prodouct_save () {
		$("#prodouct_save").submit();
	}	
	
</script>

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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">产品管理</strong> </div>
    </div>

    <hr/>

    <div class="am-g">

    <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      	<div class="">
          <div class="">
            <div class="am-g">
              <div class="am-u-md-4" id="imgDiv">
                  </div>
              <div class="am-u-md-8"><p> </p><div class="am-form-group"> <p class="am-form-help"></p></div>
              </div>
            </div>
          </div>
        </div>
      </div>







      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
      <div >
      <table>

      <tr><th style="padding: 5px;">产品名称:</th><td width="30%"> ${data.title }</td>
      <th  style="padding: 5px;">产品编号:</th><td  width="30%">${data.procode }</td></tr>
      <tr><th  style="padding: 5px;">产品利率:</th><td>${data.pro_rate}</td>
      <th  style="padding: 5px;">产品周期:</th><td>${data.pro_cycle}</td></tr>
      <tr><th  style="padding: 5px;">app端背景:</th><td>${data.pro_logo_value}</td>
      <th style="padding: 5px;">产品申请最大额度:</th><td>${data.pro_max_amount } </td></tr>
      <tr><th style="padding: 5px;">产品申请最小额度:</th><td>${data.pro_min_amount } </td>
      <th style="padding: 5px;">排序:</th><td>${data.sort }</td></tr> 
      <tr><th style="padding: 5px;">产品描述:</th><td colspan="3">	${data.description } </td></tr> 
         
      </table>
       <hr>
      扩展字段
       <table  border="1">
       <thead>
              <tr>
                
              <th >字段名:</th>
              <th >字段描述</th>
              <th>普通值</th>
              <th >大文本值</th>
              <th >value_blod</th>
              <th >value_blod_name </th>
          
               </tr>
          </thead>
     <c:forEach items="${dataList }" var="item"> 
      <tr> 
	     <td width="15%"> ${item.fieldname }</td> 
	     <td width="20%">${item.fieldvalue }</td> 
	     <td>${item.value }</td> 
	     <td >${item.value_text }</td> 
 	     <td >${item.value_blod_name } </td> 
	     <td >${item.fieldvalue } </td>
	       </tr> 
	      
      </c:forEach>
      </table>
      </div>
      <div>
         
				
      
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