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
<script src="<%=basePath%>assets/js/common.js"></script>


<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>翼勋金融</strong> <small>后台管理</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
<!--     <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li> 
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li> 
          <li><a href="logout"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li> -->
        <li><a href="logout"><span class="am-icon-power-off"></span> 退出</a></li>
      <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>



</header>





















<%-- <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fuqi" uri="/fq"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="FQ" uri="/fq" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate icon" type="image/png"
	href="<%=basePath%>assets/i/favicon.png">
<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/admin.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.flat.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/jquery.datetimepicker.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/css/amazeui.flat.min.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/app.css" />
<script src="<%=basePath%>assets/js/jquery1.11.1.min.js"></script>
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script src="<%=basePath%>assets/js/app.js"></script>
<script src="<%=basePath%>assets/js/layer.js"></script>
<script src="<%=basePath%>assets/js/common.js"></script>
<script src="<%=basePath%>assets/js/extend/layer.ext.js"></script>
<script src="<%=basePath%>assets/js/ajaxfileupload.js"></script>
<script src="<%=basePath%>assets/js/jquery.datetimepicker.js"></script>
<script type="text/javascript">
	var base ="<%=basePath%>";
</script>
<script src="<%=basePath%>assets/js/system.js"></script>

<script type="text/javascript">
	function logout () {
		window.location.href = "logout";
	}
</script>
<title>翼勋后台管理系统</title>
<header class="am-topbar admin-header" style="background-color:#0e90d2;">
	<div class="am-topbar-brand" style="color: #fff">
		<strong>p2p</strong> <small>后台管理模板</small>
	</div>

	<button
		class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: '#topbar-collapse'}">
		<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
	</button>

	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			<!-- <li><a href="javascript:;"><span class="am-icon-envelope-o"></span>
					收件箱 <span class="am-badge am-badge-warning">5</span>
			</a>
			</li> -->
			<li><a href="<%=basePath%>adminlogin"><span class="am-icon-power-off"></span>
					退出 
			</a>
			</li>
			<!-- <li class="am-dropdown" data-am-dropdown><a
				class="am-dropdown-toggle" data-am-dropdown-toggle
				href="javascript:;"> <span class="am-icon-users"></span> 管理员 <span
					class="am-icon-caret-down"></span> </a>
				<ul class="am-dropdown-content">
					<li><a href="#"><span class="am-icon-user"></span> 资料</a>
					</li>
					<li><a href="#"><span class="am-icon-cog"></span> 设置</a>
					</li>
					<li><a href="#"><span class="am-icon-power-off"></span> 退出</a>
					</li>
				</ul></li> -->
			<li><a href="javascript:;" id="admin-fullscreen"><span
					class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span>
			</a>
			</li>
		</ul>
	</div>
</header> --%>