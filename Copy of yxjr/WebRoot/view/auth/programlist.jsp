<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html class="no-js">
<head>

  <title>系统管理</title>
  <meta name="description" content="系统管理">
  <meta name="keywords" content="系统管理">
   
</head>
  <%@include file = "../head.jsp"%>

<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->



<script type="text/javascript" >
	function addProgram () {
		layer.prompt({
							    title: '输入功能组名称,并确认',
							    formType: 0 //prompt风格，支持0-2
							}, function(val){
							    $.ajax({
							    	url:"programTeamAdd?name="+val,
							    	type:"post",
							    	success:function (data){
							    		if(data.errorMsg){
											layer.msg(data.errorMsg);
											return ;
										}
										if(data){layer.msg("添加成功");window.location.href = document.location;}
							    	},
									error:function (data) {
										layer.msg(data.errorMsg);
									}
							    });
							});
	}
	function editProgram(id,name){
		layer.prompt(
			{
				title:name+"&nbsp;&nbsp;&nbsp;修改为以下标题",
				formType:0
			},function (val){
				   $.ajax({
				    	url:"programTeamUp?id="+id+"&name="+val,
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
			}
			
		);
	}
	function showItem(id){
		$("#" + id).css("display",$("#" + id).css("display") == 'none' ? '' : "none");
	}
	function addItem (teamid){
		var html = "<form action=\"programDetailEdit\" method=\"post\" id=\"form2\"><input id='itemid' name='teamid' value='"+teamid+"' type='hidden'/><table align=\"center\" style=\"margin-top:30px;\" ><tr><td align=\"right\" style=\"height:50px;\">功能名称:</td><td><input name=\"name\"  value=''/><td></tr><tr><td align=\"right\" >URL:</td><td ><input name=\"url\" type=\"text\" value=''/><td></tr><tr><td align=\"right\" colspan=\"2\" style=\"padding-top:15px;\"><a style=\"background:#0e90d2;color:#fff;text-decoration:none;display:inline-block; padding:4px 0px;width:158px; text-align:center\"  href=\"javascript:editItemSub();\">确定</a></td></tr></table></form>";
		index = layer.open({
			title:"详细功能",
			type:1,
			area:['420px', '240px'],
			content:html,
			shift:2
		});
	}
	
	function editiItem (id,name,url){
		var html = "<form action=\"programDetailEdit\" method=\"post\" id=\"form2\"><input name='id' value='"+id+"' type='hidden'/><table align=\"center\" style=\"margin-top:30px;\" ><tr><td align=\"right\" style=\"height:50px;\">功能名称:</td><td><input name=\"name\"  value='"+name+"'/><td></tr><tr><td align=\"right\" >URL:</td><td ><input name=\"url\" type=\"text\" value='"+url+"'/><td></tr><tr><td align=\"right\" colspan=\"2\" style=\"padding-top:15px;\"><a style=\"background:#0e90d2;color:#fff;text-decoration:none;display:inline-block; padding:4px 0px;width:158px; text-align:center\"  href=\"javascript:editItemSub();\">确定</a></td></tr></table></form>";
		index = layer.open({
			title:"详细功能",
			type:1,
			area:['420px', '240px'],
			content:html
		});
	}
	
	function editItemSub(){
		$.ajax({
			url:"programItemEidt",
			data:$("#form2").serialize(),
			success:function (data){
				if(data.errorMsg){
					layer.msg(data.errorMsg);
					return ;
				}
				if(data){
					layer.msg("操作成功");
					window.location.href = document.location;
				}
			} ,
			error:function (data) {
				layer.msg(data.errorMsg);
			}
		});
	
	}
	
	
	function showDetail (itemid,itemname){
		layer.open({
			title:itemname+"下子功能维护",
			type:2,
			area: ['80%', '75%'],
			content:"programDetailList?id="+itemid
		});
		
	}
</script>

<body>

<div class="am-cf admin-main" style="margin-left: 5px;">
  <!-- sidebar start -->
  <%@include file = "../leftMenu.jsp"%>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">功能管理</div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <a type="button" class="am-btn am-btn-default" href="javascript:addProgram();"><span class="am-icon-plus"></span> 新增</a>
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
                <th class="table-check"><input type="checkbox" id="selectAllCheck"   /></th><th class="table-title">功能组</th><th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${data }" var="item">
          	<tr onclick="showItem('tr${item.id}')">
              <td ><input type="checkbox" value="${item.id }" /></td>
              <td>${item.team_name }</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:editProgram('${item.id }','${item.team_name }')"><span class="am-icon-pencil-square-o"></span>编辑</a>
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:addItem('${item.id }')"><span class="am-icon-pencil-square-o"></span>增加子功能</a>
<!-- 					<a  class="am-btn am-btn-default am-btn-xs am-text-danger" style="background-color: #fff;"><span class="am-icon-trash-o"></span>删除</a> -->
                  </div>
                </div>
              </td>
            </tr>
            <tr style="display: none;" id="tr${item.id }"><td>&nbsp;</td><td colspan="2">
	            	  <table class="am-table am-table-striped am-table-hover table-main"  style="background-color: " >
	            	  		<c:forEach items="${items }" var="i">
	            	  			<c:if test="${item.id==i.teamid }">
		            	  			<tr>
		            	  					<td>${i.item_name }</td>
		            	  					<td>${i.item_url }</td>
		            	  					<td>
		            	  						 <div class="am-btn-toolbar">
									                  <div class="am-btn-group am-btn-group-xs">
														<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:editiItem('${i.id }','${i.item_name }','${i.item_url }')"><span class="am-icon-pencil-square-o"></span>编辑</a>
														<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:showDetail('${i.id }','${i.item_name }')"><span class="am-icon-pencil-square-o"></span>维护子功能</a>
<!-- 														<a  class="am-btn am-btn-default am-btn-xs am-text-danger" style="background-color: #fff;"><span class="am-icon-trash-o"></span>删除</a> -->
									                  </div>
	                							</div>
	                						</td>
		            	  			</tr>
	            	  			</c:if>
	            	  		</c:forEach>
	            	  </table>
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
<footer style="margin-bottom: 10px;">
  <hr>
  <p class="am-padding-left"><!--© 2014 AllMobilize, Inc. Licensed under MIT license. <a href="http://www.mycodes.net/" target="_blank">源码之家</a> --></p>
</footer>


</body>
</html> 