<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
<script type="text/javascript" >
	
	function prodouct_delete(){
		var checks = $("input[type=checkbox]");
		var id ="";
		for(var i = 0 ; i <checks.length ; i ++ ){
			if(checks[i].checked){
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
				url:"prodouct_delete?ids="+id,
				type:"post",
				success:function(data){
					if(data.boo){
						layer.msg("删除成功");
						window.location.href="prodouct_list";
					}
					
				},
				error:function (data) {
					layer.msg("删除失败");
				}
			});
		});
	}


	function prodouct_fbxj(a,b){
	
	$.post("prodouct_fbxj","status="+a+"&id="+b,function(data){
      if(data.boo){
		layer.msg("操作成功");
		window.location.href="prodouct_list";
		}else{
			layer.msg("操作失败");
		}
	})
			
	}


	function showDetail (title,id,type){
		layer.open({
			title:title+"_扩展数据维护",
			type:2,
			area: ['95%', '95%'],
			content:"fieldshow?proid="+id+"&retype="+type
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">产品管理</strong> </div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <a type="button" class="am-btn am-btn-default" href="prodouct_edit"><span class="am-icon-plus"></span> 新增</a>
              <a type="button" class="am-btn am-btn-default" href="javascript:prodouct_delete();"><span class="am-icon-trash-o"></span> 删除</a>
            </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
          	<form action="diclist" method="post" id="queryForm">
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
                <th class="table-check"><input type="checkbox" id="selectAllCheck"   /></th>
              <th class="table-title">产品标题</th>
              <th class="table-title">产品编号</th>
              <th class="table-type">产品利率</th>
              <th class="table-type">产品周期 </th>
              <th class="table-type">创建时间 </th>
              <th class="table-type">产品状态 </th>
          
              <th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${pageData }" var="item">
          	<tr>
              <td ><input type="checkbox" value="${item.id }" /></td>
              <td>${item.title}</td>
                         
              <td>${item.procode}</td>              
               <td>${item.pro_rate}</td>
               <td>${item.pro_cycle }</td>
                <td>${fn:substring(item.credate , 0, 10)}</td>
              <td> <c:if test="${item.status == 0  }">待发布</c:if>
              <c:if test="${item.status == 1  }">已发布</c:if>
              <c:if test="${item.status == 2 }">已下架</c:if></td>
                         <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="prodouct_edit?id=${item.id }"><span class="am-icon-pencil-square-o"></span>编辑</a>
				<c:if test="${item.status == 0  }">
					<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:prodouct_fbxj(1,${item.id})"><span class="am-icon-pencil-square-o"></span>发布</a>
              </c:if>
                   <c:if test="${item.status == 1  }">
                   	<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:prodouct_fbxj(2,${item.id})"><span class="am-icon-pencil-square-o"></span>下架</a>
            </c:if>
                	<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:showDetail('${item.title}',${item.id},1)"><span class="am-icon-pencil-square-o"></span>产品扩展字段</a>
                
                	<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="javascript:showDetail('${item.title}',${item.id},2)"><span class="am-icon-pencil-square-o"></span>申请扩展字段</a>
                	<a  class="am-btn am-btn-default am-btn-xs am-text-secondary" style="background-color: #fff;" href="prodouct_info?id=${item.id }"><span class="am-icon-pencil-square-o"></span>产品详情</a>
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