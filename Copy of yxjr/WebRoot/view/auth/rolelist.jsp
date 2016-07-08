<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!doctype html>
<html class="no-js">
<head>
  <title>系统管理</title>
  <meta name="description" content="系统管理">
  <meta name="keywords" content="系统管理">
</head>
<%@include file = "../head.jsp"%>
<script type="text/javascript" >
	
	function delRoleByIds(){
		var checks = $("input[type=checkbox]");
		var id ="";
		for(var i = 0 ; i <checks.length ; i ++ ){
			if(checks[i].checked){
				if(!isNaN($(checks[i]).val()))
					id += $(checks[i]).val()+",";
			}
		}
		if(id == ""){
			layer.msg("选择要删除的人");
			return;
		}
		id= id.substring(0, id.length-1);
		layer.confirm("确认删除么?",function(){
			$.ajax({
				url:"delRoleByIds?ids="+id,
				type:"post",
				success:function(data){
					if(data.errorMsg){
						layer.msg(data.errorMsg);
						return ;
					}
					layer.msg("删除成功");
					window.location.href = document.location;
				},
				error:function (data) {
					layer.msg(data.errorMsg);
				}
			});
		});
	}
	
	
	function addRole (){
		layer.prompt("输入角色名称",function(val){
			$.ajax({
				url:"addRole?name="+val,
				type:"post",
				success:function (data){
					if(data.errorMsg){
							layer.msg(data.errorMsg);
							return ;
						}
					if(data){
						layer.msg("添加成功");
						window.location.href = document.location;
					}
				},
				error:function (data) {
					layer.msg(data.errorMsg);
				}
			});
		});
	}
	function editRole (id,name){
		layer.prompt({title:name+"&nbsp;&nbsp;&nbsp;修改为"},function(val){
				$.ajax({
					url:"editRole?id="+id+"&name="+val,
					type:"post",
					success:function (data){
						if(data.errorMsg){
							layer.msg(data.errorMsg);
							return ;
						}
						if(data){
							layer.msg("修改成功");
							window.location.href = document.location;
						}
					},
					error:function (data) {
						layer.msg(data.errorMsg);
					}
				});
			});
	} 
	function setAuth (roleid,name){
		layer.open ({
			type:2,
			title:name+"权限设置",
			area:["400px","700px;"],
			content:"programShow?roleId="+roleid
		}); 
	
	}
</script>

<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<div class="am-cf admin-main" >
  <!-- sidebar start -->
  <%@include file = "../leftMenu.jsp"%>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表格</strong> / <small>Table</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <a type="button" class="am-btn am-btn-default" href="javascript:addRole();"><span class="am-icon-plus"></span> 新增</a>
              <a type="button" class="am-btn am-btn-default" href="javascript:delRoleByIds();"><span class="am-icon-trash-o"></span> 删除</a>
            </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
          <!--   <input type="text" class="am-form-field">
                <span class="am-input-group-btn">
                  <button class="am-btn am-btn-default" type="button" onclick="">搜索</button> 
                </span> -->
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
                <th class="table-check"><input type="checkbox" id="selectAllCheck"   /></th><th class="table-title">角色名</th><th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${data }" var="item">
          	<tr>
              <td ><input type="checkbox" value="${item.id }" /></td>
              <td>${item.role_name }</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:editRole('${item.id }','${item.role_name }')"><span class="am-icon-pencil-square-o"></span>编辑</a>
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:setAuth('${item.id }','${item.role_name }')"><span class="am-icon-pencil-square-o"></span>设置权限</a>
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
  </div>
  <!-- content end -->
</div>
<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
<footer>
  <hr>
  <p class="am-padding-left"><!--© 2014 AllMobilize, Inc. Licensed under MIT license. <a href="http://www.mycodes.net/" target="_blank">源码之家</a> --></p>
</footer>


</body>
</html>