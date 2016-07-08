<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
<link href="assets/js/umeditor/themes/default/css/umeditor.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript"
	src="assets/js/umeditor/third-party/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="assets/js/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="assets/js/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets/js/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" >

	function saveUser () {
		$("#saveUser").submit();
	}	
	function ajaxUpload () {
	if(error != ""){
		layer.alert(error);
		return ;
	}
	 $.ajaxFileUpload
            (
                {
                    url: 'userImgUpload?id='+$("input[name=id]").val(), //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'file1', //文件上传域的ID
                    success: function (data, status)  //服务器成功响应处理函数
                    {	
                    	$("#headimg").src=data;
                    	error="请选择图片文件";
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                        layer.msg(data.errorMsg);
                    }
                }
            );
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">版本管理</strong> / <small>Personal information</small></div>
    </div>

    <hr/>

    <div class="am-g">

      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      </div>
     

      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">																			
        <form class="am-form am-form-horizontal" action="saveVesion" method="post" id="form" enctype="multipart/form-data">
        	<input name="id" type="hidden" value="${data.id }"/>
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">版本号</label>
            <div class="am-u-sm-9">
              <input type="text"  value="${data.number }" name="number"  >
            </div>																				    
          </div>
      
  
             <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">更新说明</label>
            <div class="am-u-sm-9">
              <textarea class="article_tip"   name="reason" style="width: 790px; height: 252px;" name="reason">${data.reason }</textarea> 
            </div>
          </div>
      
         <div class="am-form-group">
      
            <label for="user-name" class="am-u-sm-3 am-form-label">url</label>
            <div class="am-u-sm-9">
              <input type="text"  value="${data.url }" name="url"  >
             </div>	
            																			    
          </div>   
          
          
         <div class="am-form-group">
      
            <label for="user-name" class="am-u-sm-3 am-form-label">类型</label>
            <div class="am-u-sm-9">
              ios <input type="radio"  required="required" value="ios" name="type"  <c:if test="${data.type=='ios' }">checked="checked" </c:if>>
              android  <input type="radio"  required="required" value="android" name="type"  <c:if test="${data.type=='android' }">checked="checked" </c:if>>
              
             </div>	
            																			    
          </div> 
          
    
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <a href="javascript:$('#form').submit();"  class="am-btn am-btn-primary">保存修改</a>
            </div>
          </div>
          
        </form>
      </div>
      
    </div>
  </div>
  <!-- content end -->
</div>
 
<script type="text/javascript">
    var um = UM.getEditor('myEditor');
</script>
</body>
</html>
