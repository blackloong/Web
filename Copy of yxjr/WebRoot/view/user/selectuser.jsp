<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<link rel="stylesheet"
	href="<%=basePath%>assets/css/amazeui.flat.min.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/app.css" />
<link href="<%=basePath%>assets/js/umeditor/themes/default/css/umeditor.css" type="text/css"rel="stylesheet"/>
<script src="<%=basePath%>assets/js/jquery1.11.1.min.js"></script>
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script src="<%=basePath%>assets/js/app.js"></script>
<script src="<%=basePath%>assets/js/layer.js"></script>
<script src="<%=basePath%>assets/js/common.js"></script>
<script src="<%=basePath%>assets/js/extend/layer.ext.js"></script>
<script src="<%=basePath%>assets/js/ajaxfileupload.js"></script>
<script type="text/javascript">

	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	function select1 (id){
		var parentid = id;
		var url = '${url}&selUid='+id;
		$.ajax(
			{
				url:url,
				success:function(data){
					if(data){
						if(data.msg)
						{
							 parent.layer.msg(data.msg);
						}else{
							 parent.layer.msg('设置成功');
						}
						setTimeout('ref()', 1000);
					}
				}
			}
		);
	}
	//设置等待1s 后刷新
	function ref() {
		parent.location.href=parent.location.href;	
		parent.layer.close(index);
	}
</script>
<br>
<div class="am-g">
      <div class="am-u-md-3 am-cf" style="width: 100%">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
          		<form action="selectUser" method="post" id="queryForm"><table>
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
                <th class="table-check">&nbsp;</th><th class="table-title">姓名</th><th class="table-type">手机号码</th><th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${data }" var="item">
          	<tr>
              <td ><input type="radio" name="userid" value="${item.id }" onclick="select1('${item.id}');"/></td>
              <td>${item.uname }</td>
              <td>${item.mobile }</td>
              <td>${item.role_name }</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:editUser('editUser?id=${item.id }')"><span class="am-icon-pencil-square-o"></span>编辑</a>
					<a  class="am-btn am-btn-default am-btn-xs am-text-danger" style="background-color: #fff;"><span class="am-icon-trash-o"></span>删除</a>
                  </div>
                </div>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
       	<FQ:page/>
          <hr />
        </form>
      </div>

    </div>