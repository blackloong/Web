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
  <title>数据字典管理</title>
  <meta name="description" content="数据字典管理">
  <meta name="keywords" content="数据字典管理">
   


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
	var index ;
	function addDetail (id){
		var html = "<form action=\"dicEditSub\" method=\"post\" id=\"form2\"><input name='id' value='' type='hidden'/><input name='pid' value='"+id+"' type='hidden'/>"+
		"<table align=\"center\" style=\"margin-top:30px;\" >"+
		"<tr><td align=\"right\">值：</td><td><input name=\"value\"  /><td></tr>"+
		"<tr><td align=\"right\" >类型：</td><td ><input name=\"type\" type=\"text\" /><td></tr>"+
			"<tr><td align=\"right\" >拼音缩写：</td><td ><input name=\"py\" type=\"text\"/><td></tr>"+
				"<tr><td align=\"right\" >排序：</td><td ><input name=\"orderby\" type=\"text\" /><td></tr>"+
		"<tr><td align=\"center\" colspan=\"2\" style=\"padding-top:15px;\"><a style=\"background:#0e90d2;color:#fff;text-decoration:none;display:inline-block; padding:4px 0px;width:158px; text-align:center\"  href=\"javascript:dicEditlSub();\">"+
		"确定</a></td></tr></table></form>";
	index = layer.open({
			title:"详细功能",
			type:1,
			area:['420px', '240px'],
			content:html
		});
	}
	
	function loadData () {
		$.ajax({
			url:"dicShowAjax?id="+'${id}',
			success:function(data){
				if(data.errorMsg){
					layer.msg(data.errorMsg);
					return ;
				}
				$("#tbody").html("");
				var html = "";
				for(var i = 0 ; i < data.length; i ++ ){
					var item = data[i];
					html +='<tr>\
					              <td ><input type="checkbox" value="'+item.id+'" /></td>\
					               <td>'+item.id+'</td>\
					                <td>'+item.value+'</td>\
					              <td>'+item.type+'</td>\
					              <td>'+item.py+'</td>\
					              <td>\
					                <div class="am-btn-toolbar">\
					                  <div class="am-btn-group am-btn-group-xs">\
										<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:editDetail(\''+item.id+'\',\''+item.value+'\',\''+item.type+'\',\''+item.py+'\',\''+item.orderby+'\')"><span class="am-icon-pencil-square-o"></span>编辑</a>\
					                  </div>\
					                </div>\
					              </td>\
					            </tr>';
				}
				$("#tbody").html(html);
			},
			error:function (data) {
				layer.msg(data.errorMsg);
			}
		});
	}
	function editDetail (id,value,type,py,orderby){
		var html = "<form action=\"dicEditSub\" method=\"post\" id=\"form2\"><input name='id' value='"+id+"' type='hidden'/>"+
		"<table align=\"center\" style=\"margin-top:30px;\" >"+
		"<tr><td align=\"right\">值：</td><td><input name=\"value\"  value='"+value+"'/><td></tr>"+
		"<tr><td align=\"right\" >类型：</td><td ><input name=\"type\" type=\"text\" value='"+type+"'/><td></tr>"+
			"<tr><td align=\"right\" >拼音缩写：</td><td ><input name=\"py\" type=\"text\" value='"+py+"'/><td></tr>"+
				"<tr><td align=\"right\" >排序：</td><td ><input name=\"orderby\" type=\"text\" value='"+orderby+"'/><td></tr>"+
		"<tr><td align=\"center\" colspan=\"2\" style=\"padding-top:15px;\"><a style=\"background:#0e90d2;color:#fff;text-decoration:none;display:inline-block; padding:4px 0px;width:158px; text-align:center\"  href=\"javascript:dicEditlSub();\">"+
		"确定</a></td></tr></table></form>";
		index = layer.open({
			title:"详细功能",
			type:1,
			area:['420px', '240px'],
			content:html
		});
	}
	function dicEditlSub (){
		$.ajax({
			url:"dicEditSub",
			data:$("#form2").serialize(),
			success:function(data){
				
				if(data.boo){
					layer.msg("操作成功");
					layer.close(index);
					loadData();
				}
			},
			error:function (data) {
				layer.msg("操作失败");
			}
		});
	}
	
	
	function deldic(){
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
				url:"dicdelete?ids="+id,
				type:"POST",
				success:function(data){
					
					if(data.boo){
						layer.msg("删除成功");
						loadData();
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
<body onload="loadData();">

  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">子数据</strong></div>
    </div>

	<div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <a type="button" class="am-btn am-btn-default" href="javascript:addDetail('${id }');"><span class="am-icon-plus"></span> 新增</a>
               <a type="button" class="am-btn am-btn-default" href="javascript:deldic();"><span class="am-icon-trash-o"></span> 删除</a>
            </div>
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
                <th class="table-check">&nbsp;</th><th class="table-title">id</th><th class="table-title">名称</th><th class="table-type">类型</th><th class="table-type">拼音缩写</th><th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody id="tbody">
          </tbody>
        </table>
          <hr />
        </form>
      </div>

    </div>
  </div>
  <!-- content end -->



</body>
</html>