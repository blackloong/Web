<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/dic" prefix="dic"%>
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
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="<%=basePath%>assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="<%=basePath%>assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />


<link rel="stylesheet" href="<%=basePath%>assets/css/amazeui.min.css" />
<link rel="stylesheet" href="<%=basePath%>assets/css/admin.css">

<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script src="<%=basePath%>assets/js/app.js"></script>
<script type="text/javascript"
	src="<%=basePath%>assets/js/jquery1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>assets/js/layer.js"></script>

<script src="<%=basePath%>assets/js/extend/layer.ext.js"></script>

<script src="<%=basePath%>js/system.js"></script>

<script type="text/javascript">
	var index;
var retype_v=${retype};
	function onchange_display(a, value,value_text,datas,value_blod_name) {
	
		if( value==undefined||value=='undefined'||value==''){value='';}
		if( value_text==undefined||value_text=='undefined'||value_text==''){value='';}							
		if( datas==undefined||datas=='undefined'||datas==''){value_text='';}							
		if( value_blod_name==undefined||value_blod_name=='undefined'||value_blod_name==''){value_blod_name='';}
					
		var html = "";
		var sel = "";
		var v = a.value;
		if ("text" == v) {
			 if(retype_v==1){		html = "<td align=\"right\">文本值：</td><td ><input name=\"value\" value=\""+value+"\" type=\"text\"/></td>";}
		} else if ("textarea" == v) {
			 if(retype_v==1){		html = "<td align=\"right\">大文本值：</td><td ><textarea name=\"value_text\">"+value_text+"</textarea></td>";}
		} else if ("select" == v) {
			html = "<td align=\"right\">数据源：</td><td ><select name=\"datas\" onchange=\"_systemUI.loadSelect(this,'seldatas_id','tb_dictionary','id','value',' and pid=','');\" ><dic:loadSelectTag tableName='tb_dictionary' key='id' text='value' id='"+datas+"_id' where=' and pid=0 ' /></select></td>";
		  	 if(retype_v==1){sel = "<td align=\"right\">数据源值：</td><td ><select name=\"value\" id='seldatas_id'></select></td>";   }
		} else if ("radio" == v) {
			html = "<td align=\"right\">数据源：</td><td ><select  name=\"datas\" onchange=\"_systemUI.loadSelect(this,'seldatas_id','tb_dictionary','id','value',' and pid=','');\"  ><dic:loadSelectTag tableName='tb_dictionary' key='id' text='value' id='"+datas+"_id' where=' and pid=0 '/></select></td>";
		 if(retype_v==1){	sel = "<td align=\"right\">数据源值：</td><td ><select name=\"value\" id='seldatas_id'></select></td>";}
		} else if ("file" == v) {
			 if(retype_v==1){	html = "<td align=\"right\">附件：</td><td ><input name=\"value_blod\" type=\"file\"/>"+value_blod_name+"</td>";}
		}
		$("#content_show_id").html(html);
		$("#content_value_id").html(sel);
		}
	

	function addField(id, retype) {
		var ft = "<select name=\"fieldtype\" onchange=\"onchange_display(this);\">\
<option value=\"text\">文本</option>\
<option value=\"textarea\">大文本</option>\
<option value=\"select\">下拉框</option>\
<option value=\"radio\">单选框</option>\
<option value=\"file\">附件上传</option>\
</select>";
		//block
		var inp_value="";
		var inp_sel="";
		 if(retype_v==1){
	    inp_value="<td align=\"right\">值：</td><td><input name=\"value\" type=\"text\"/></td>" ;
		 }
		var html = "<form action=\"savefield\" method=\"post\"   enctype=\"multipart/form-data\"  id=\"form1\">"
				+ "<input name='id' value='' type='hidden'/><input name='proid' value='"+id+"' type='hidden'/><input name='retype' value='"+retype+"' type='hidden'/>"
				+ "<table  style=\"margin-top:10px; width:100%;\" >"
				+ "<tr><td align=\"right\" width='30%'>字段名称：</td><td><input name=\"fieldname\"  /></td></tr>"
				+ "<tr><td align=\"right\"   >字段描述：</td><td style=\"padding:5px 0 5px 0;\"><input name=\"fieldvalue\" type=\"text\" /></td></tr>"
				+ "<tr><td align=\"right\"   >字段排序：</td><td  style=\"padding:10px 0 10px 0;\"><input name=\"sort\" type=\"text\" /></td></tr>"
				+ "<tr><td align=\"right\"   >字段类型：</td><td >"
				+ ft
				+ "</td></tr>"
				+ "<tr id='content_show_id'>"+inp_value+"</tr><tr  id='content_value_id'  ></tr>"
				+ "<tr><td align=\"center\" colspan=\"2\" style=\"padding-top:15px;margin-top:10px;\" >"
				+ "<a style=\"background:#0e90d2;color:#fff;text-decoration:none;display:inline-block; padding:4px 0px;width:158px; text-align:center\"  href=\"javascript:saveSub();\">"
				+ "确定</a></td></tr></table></form>";
		index = layer.open({
			title : "详细功能",
			type : 1,
			area : [ '40%', '50%' ],
			content : html
		});
	}
	function editField(id,proid,retype,fieldtype,fieldname,fieldvalue,sort,datas,value,value_text,value_blod_name) {
		var ft = "<select name=\"fieldtype\" onchange=\"onchange_display(this,'"+value+"','"+value_text+"','"+datas+"','"+value_blod_name+"');\">";
		var text= "<option value=\"text\" >文本</option>";
		var textarea= "<option value=\"textarea\" >大文本</option>";
		var select= "<option value=\"select\" >下拉框</option>";
		var radio= "<option value=\"radio\" >单选框</option>";
		var file= "<option value=\"file\" >附件上传</option>";
		
		var inp_value="";
		var inp_sel="";
		
		if ("text" == fieldtype) {
			text = "<option value=\"text\" selected=\"selected\" >文本</option>";
		 if(retype_v==1){	inp_value = "<td align=\"right\">文本值：</td><td ><input name=\"value\" value=\""+value+"\" type=\"text\"/></td>"; }
		} else if ("textarea" ==fieldtype) {
		 textarea = "<option value=\"textarea\" selected=\"selected\">大文本</option>";
	 if(retype_v==1){	 inp_value = "<td align=\"right\">大文本值：</td><td ><textarea name=\"value_text\">"+value_text+"</textarea></td>";}
		} else if ("select" == fieldtype) {
			select = "<option value=\"select\" selected=\"selected\">下拉框</option>";
			inp_value = "<td align=\"right\">数据源：</td><td ><select name=\"datas\" onchange=\"_systemUI.loadSelect(this,'seldatas_id','tb_dictionary','id','value',' and pid=','');\" ><dic:loadSelectTag tableName='tb_dictionary' key='id' text='value' id='"+datas+"_id' where=' and pid=0 ' /></select></td>";
		 if(retype_v==1){    inp_sel = "<td align=\"right\">数据源值：</td><td ><select name=\"value\" id='seldatas_id'></select></td>";}
		   
		} else if ("radio" == fieldtype) {
			radio = "<option value=\"radio\" selected=\"selected\">单选框</option>";
			inp_value = "<td align=\"right\">数据源：</td><td ><select  name=\"datas\" onchange=\"_systemUI.loadSelect(this,'seldatas_id','tb_dictionary','id','value',' and pid=','');\"  ><dic:loadSelectTag tableName='tb_dictionary' key='id' text='value' id='"+datas+"_id' where=' and pid=0 '/></select></td>";
	 if(retype_v==1){		inp_sel = "<td align=\"right\">数据源值：</td><td ><select name=\"value\" id='seldatas_id'></select></td>";}
		  
		} else if ("file" == fieldtype) {
			file = "<option value=\"file\" selected=\"selected\">附件上传</option>";
		 if(retype_v==1){	inp_value = "<td align=\"right\">附件：</td><td ><input name=\"value_blod\" type=\"file\"/>"+value_blod_name+"</td>";}
		
		}
		 ft =ft+text+textarea+select+radio+file+"</select>"
	
 




		var html = "<form action=\"savefield\" method=\"post\"   enctype=\"multipart/form-data\"  id=\"form1\">"
				+ "<input name='id' value='"+id+"' type='hidden'/><input name='proid' value='"+proid+"' type='hidden'/>"
				+ "<input name='retype' value='"+retype+"' type='hidden'/>"
				+ "<table  style=\"margin-top:10px; width:100%;\" >"
				+ "<tr><td align=\"right\" width='30%'>字段名称：</td><td><input name=\"fieldname\" type=\"text\" value=\""+fieldname+"\"  /></td></tr>"
				+ "<tr><td align=\"right\"   >字段描述：</td><td style=\"padding:5px 0 5px 0;\"><input name=\"fieldvalue\" value=\""+fieldvalue+"\" type=\"text\" /></td></tr>"
				+ "<tr><td align=\"right\"   >字段排序：</td><td  style=\"padding:10px 0 10px 0;\"><input name=\"sort\"  value=\""+sort+"\"  type=\"text\" /></td></tr>"
				+ "<tr><td align=\"right\"   >字段类型：</td><td >"
				+ ft
				+ "</td></tr>"
				+ "<tr  id='content_show_id'  >"+inp_value+"</tr>"
				+ "<tr  id='content_value_id'  >"+inp_sel+"</tr><tr><td align=\"center\" colspan=\"2\" style=\"padding-top:15px;margin-top:10px;\" >"
				+ "<a style=\"background:#0e90d2;color:#fff;text-decoration:none;display:inline-block; padding:4px 0px;width:158px; text-align:center\"  href=\"javascript:saveSub();\">"
				+ "确定</a></td></tr></table></form>";
		index = layer.open({
			title : "详细功能",
			type : 1,
			area : [ '40%', '50%' ],
			content : html
		});
	}

	function saveSub() {
		$("#form1").submit();

		/* 
			$.ajax({
				url:"savefield",
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
			}); */
	}

	function loadData() {
		$
				.ajax({
					url : "fieldlist?proid=${proid}&retype=${retype}",
					success : function(data) {
						if (data.errorMsg) {
							layer.msg(data.errorMsg);
							return;
						}
						$("#tbody").html("");
						var html = "";
						for ( var i = 0; i < data.length; i++) {
							var item = data[i];

							var retype = "";
							if (item.retype == 1) {
								retype = "产品扩展字段";
							} else if (item.retype == 2) {
								retype = "申请扩展字段";
							}
							var value_ = "";
							if (item.fieldtype == "text") {
								value_ = item.value;
							} else if (item.fieldtype == "textarea") {
								value_ = "--";
							} else if (item.fieldtype == "select") {
								value_ = item.value;
							} else if (item.fieldtype == "radio") {
								value_ = item.value;
							} else if (item.fieldtype == "file") {
								value_ = item.value_blod_name;
							}
							if(value_==''||value_==undefined||value_=='undefined'){
							value_='';
							}

							html += '<tr><td ><input type="checkbox" value="'+item.id+'" /></td> <td>'
									+ item.fieldname
									+ '</td><td>'
									+ item.fieldvalue
									+ '</td><td>'
									+ item.fieldtype
									+ '</td><td>'
									+ retype
									+ '</td>\
					                 <td>'
									+ value_
									+ '</td> <td><div class="am-btn-toolbar"> <div class="am-btn-group am-btn-group-xs">\
										<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" \
										href="javascript:editField(\''
									+ item.id
									+ '\',\''
									+ item.proid
									+ '\',\''
									+ item.retype
									+ '\',\''
									+ item.fieldtype
									+ '\',\''
									+ item.fieldname
									+ '\',\''
									+ item.fieldvalue
									+ '\',\''
									+ item.sort
									+ '\',\''
									+ item.datas
									+ '\',\''
									+ item.value
									+ '\',\''
									+ item.value_text
									+ '\',\''
									+ item.value_blod_name
									+ '\')">\
										<span class="am-icon-pencil-square-o"></span>编辑</a></div>  </div> </td> </tr>';
										}
						
						$("#tbody").html(html);
					},
					error : function(data) {
						layer.msg(data.errorMsg);
					}
				});
	}

	function deldic() {
		var checks = $("input[type=checkbox]");
		var id = "";
		for ( var i = 0; i < checks.length; i++) {
			if (checks[i].checked) {
				if (!isNaN($(checks[i]).val()))
					id += $(checks[i]).val() + ",";
			}
		}
		if (id == "") {
			layer.msg("请选择项！");
			return;
		}
		id = id.substring(0, id.length - 1);
		layer.confirm("确认删除么?", function() {
			$.ajax({
				url : "delfield?ids=" + id,
				type : "POST",
				success : function(data) {

					if (data.boo) {
						layer.msg("删除成功");
						loadData();
					}
				},
				error : function(data) {
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
			<div class="am-fl am-cf">
				<strong class="am-text-primary am-text-lg">子数据</strong>
			</div>
		</div>

		<div class="am-g">
			<div class="am-u-md-6 am-cf">
				<div class="am-fl am-cf">
					<div class="am-btn-toolbar am-fl">
						<div class="am-btn-group am-btn-group-xs">
							<a type="button" class="am-btn am-btn-default"
								href="javascript:addField('${proid}','${retype }');"><span
								class="am-icon-plus"></span> 新增</a> <a type="button"
								class="am-btn am-btn-default" href="javascript:deldic();"><span
								class="am-icon-trash-o"></span> 删除</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="am-g">
			<div class="am-u-sm-12">
				<form class="am-form">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th class="table-check">&nbsp;</th>
								<th class="table-title">字段名称</th>
								<th class="table-title">字段描述</th>
								<th class="table-type">类型</th>
								<th class="table-type">所属类型</th>
								<th class="table-set">值</th>
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