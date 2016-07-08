<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
<script type="text/javascript" >
	
	function deldot(){
		var checks = $("input[type=checkbox]");
		var id ="";
		for(var i = 0 ; i <checks.length ; i ++ ){
			if(checks[i].checked){
				if(!isNaN( $(checks[i]).val()))
					id += $(checks[i]).val()+",";
			}
		}
		if(id == ""){
			layer.msg("请选择项！");
			return;
		}
		id= id.substring(0, id.length-1);
		layer.confirm("确认删除么?",function(){
			$.ajax({
				url:"dotdelete?ids="+id,
				type:"POST",
				success:function(data){
					
					if(data.boo){
						layer.msg("删除成功");
						location.href="dotlist";
					}
				},
				error:function (data) {
					layer.msg("删除失败");
				}
			});
		});
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表格</strong> / <small>Table</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <a type="button" class="am-btn am-btn-default" href="dotedit"><span class="am-icon-plus"></span> 新增</a>
              <a type="button" class="am-btn am-btn-default" href="javascript:deldot();"><span class="am-icon-trash-o"></span> 删除</a>
            </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
          	<form action="dotlist" method="post" id="queryForm">
	            <input type="text" name="keyWord" value="${keyWord }" class="am-form-field">
	        </form>
	                <span class="am-input-group-btn">
	                  <button class="am-btn am-btn-default" type="button" onclick="$('#queryForm').submit();">搜索</button>
	                </span>
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
                <th class="table-check">&nbsp;</th>
              <th class="table-title">网点简称</th><th class="table-title">所在省</th>
              <th class="table-type">所在地详细地址 </th><th class="table-set">负责人</th>
              <th class="table-set">联系电话</th>
              <th class="table-set">经度</th><th class="table-set">纬度</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageData }" var="item">
          	<tr>
              <td ><input type="checkbox" value="${item.id }" /></td>
                <td>${item.dot }</td>
              <td>${item.value }</td>
              <td>${item.details }</td>
                <td>${item.gl_name }</td>
               <td>${item.gl_phone }</td>
                <td>${item.longitude }</td>
                 <td>${item.latitude }</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="dotedit?id=${item.id }"><span class="am-icon-pencil-square-o"></span>编辑</a>
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

<footer>
  <hr>
  <p class="am-padding-left"><!--© 2014 AllMobilize, Inc. Licensed under MIT license. <a href="http://www.mycodes.net/" target="_blank">源码之家</a> --></p>
</footer>


</body>
</html>