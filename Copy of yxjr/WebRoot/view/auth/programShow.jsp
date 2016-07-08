<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html class="no-js">
  <title>系统管理</title>
  <meta name="description" content="系统管理">
  <meta name="keywords" content="系统管理">
   


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

<script type="text/javascript" >
	function showUL (id) {
		$("#" + id).css("display",$("#" + id).css("display") == 'none' ? '' : "none");
	}
	function save () {
		var checks = $("input[type=checkbox]");
		var id ="";
		for(var i = 0 ; i <checks.length ; i ++ ){
			if(checks[i].checked){
				if(!isNaN( $(checks[i]).val()))
					id += $(checks[i]).val()+",";
			}
		}
		if(id== ""){
			layer.msg("是否要清空此角色的权限?");
		}
		$.ajax({
			url:"editeRoleAuth?roleId="+'${roleId}'+"&ids="+id,
			type:"post",
			success:function(data){
				if(data.errorMsg){
					layer.msg(data.errorMsg);
					return ;
				}
				if(data == "1"){
					layer.msg("保存成功");
				}else{
					layer.msg("保存失败");
				}
			},
			error:function (data){
				layer.msg(data.errorMsg);
			}
		
		});
	}
</script></head>
<body>

  <!-- content start -->
  <div class="admin-content">
    <div class="am-g">
      <div class="am-u-sm-12" align="" style="padding-top:15px;">
        <form class="am-form" >
        	<c:forEach items="${program.teams }" var="team">
      		       <div style="cursor: pointer;color: #3bb4f2" onclick="showUL('ul${team.teamid}')">${team.team_name } </div>
      		       <ul id="ul${team.teamid }" style="list-style:none;margin:0px;display: none ;margin-bottom:15px;">	
      		       		<c:forEach items = "${program.items }" var = "item">
      		       			<c:if test="${item.teamid == team.teamid }">
      		       				<li style="margin-top: 5px;">
      		       					<span style="cursor: pointer;" onclick="showUL('div${item.itemid}')">${item. item_name }</span>
      		       					<div id="div${item.itemid }" style="padding-left: 20px;padding-bottom: 15px;display: none;">
	      		       					<c:forEach items="${program.details }" var = "detail">
	      		       						<c:if test="${item.itemid == detail.itemid  }">
	      		       							<a  style="color:#999;">${detail.detail_name }</a><input <c:if test="${detail. program_detail_id != null  }">checked="checked"</c:if> type="checkbox" value="${detail.detailid }" name="detailid"  />
	      		       							<br>
	      		       						</c:if>
	      		       					</c:forEach>
      		       					</div>
      		       				</li>
      		       			</c:if>
      		       		</c:forEach>
      		      </ul>
      		      <hr/>
        	</c:forEach>
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <a href="javascript:save();"  class="am-btn am-btn-primary">保存修改</a>
            </div>
          </div>
        </form>
      </div>

    </div>
  </div>

</body>
</html>