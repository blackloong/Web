

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
  <title>后台管理系统</title>







  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="<%=basePath%>assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="<%=basePath%>assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  
  
  <link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=basePath%>assets/css/admin.css">

<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script src="<%=basePath%>assets/js/app.js"></script>
  <script type="text/javascript" src="<%=basePath%>assets/js/jquery1.11.1.min.js"></script>
  <script type="text/javascript"  src="<%=basePath%>assets/js/layer.js"></script>

<script src="<%=basePath%>assets/js/extend/layer.ext.js"></script>







  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
  <script type="text/javascript">
  	function login () {
  		if($("input[name=mobile]").val()==""){
  			layer.tips('用户名不能为空', '#mobile', {
    						tips:[2, '#0e90d2']
						});
			$("input[name=mobile]").focus();
			return;
  		}
  		if($("input[name=password]").val()==""){
  			layer.tips('密码不能为空', '#password', {
    						tips:[2, '#0e90d2']
						});
			$("input[name=password]").focus();
			return;
  		}
  		$("#form").submit();
  	}
  	$(function (){
  		var errorMsg = '${errorMsg}';
  		if(errorMsg != null && errorMsg != '' && errorMsg != 'null'){
  			layer.msg(errorMsg);
  		}
  		
  	} );
  </script>
</head>
<body>
<div class="header"> 
 <div class="am-g">
    <h1>翼勋金融后台管理</h1>
    <p></p>
  </div>
 <!--  <hr /> -->
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
 <!--    <h3></h3>
    <hr> --> <hr> 
    <div class="am-btn-group">
     <!--  <a href="#" class="am-btn am-btn-secondary am-btn-sm"><i class="am-icon-github am-icon-sm"></i> Github</a>
      <a href="#" class="am-btn am-btn-success am-btn-sm"><i class="am-icon-google-plus-square am-icon-sm"></i> Google+</a>
      <a href="#" class="am-btn am-btn-primary am-btn-sm"><i class="am-icon-stack-overflow am-icon-sm"></i> stackOverflow</a> -->
    </div>
    <br>
    <br>
    <form method="post" action="log" id="form" class="am-form">
      <label for="mobile">手机号:</label>
      <input type="text" name="mobile" id="mobile" value="" required>
      <br>
      <label for="password">密码:</label>
      <input type="password" name="password" id="password" value="" required>
      <br>
      <br />
      <div class="am-cf">
        <input type="button" onclick="login()" name=""  value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
        <input type="button" name="" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr">
      </div>
    </form>
    <hr>
    <p>版权所有.  ©2015沪企 </p>
  </div>
</div>
   
</body>
</html>