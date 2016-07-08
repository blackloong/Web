<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://com.supwisdom/jsp/jstl/myfunctions" prefix="myfn" %>

<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
<script type="text/javascript" >

	function saveDic () {
		$("#saveDic").submit();
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据字典</strong> </div>
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
        <form class="am-form am-form-horizontal" action="dicsave" method="post" id="saveDic">
        	<input name="id" type="hidden" value="${data.id }"/>
        
      <input name="pid" type="hidden" value="0"/>
        
		
          <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">值 </label>
            <div class="am-u-sm-9" style="width: 70%">
              <input type="text"  value= "${data.value }" name="value"> 
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">类型</label>
            <div class="am-u-sm-9" style="width: 70%">
              <input type="text"  value= "${data.type }" name="type" > 
            </div>
          </div>
            <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">拼音缩写</label>
            <div class="am-u-sm-9" style="width: 70%">
              <input type="text"  value="${data.py }" name="py"> 
            </div>
          </div>
             <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">排序</label>
            <div class="am-u-sm-9" style="width: 70%">
              <input type="text"  value= "${data.orderby }" name="orderby" > 
            </div>
          </div>
          </div>
          
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <a href="javascript:saveDic();"  class="am-btn am-btn-primary">保存修改</a>
            </div>
          </div>
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