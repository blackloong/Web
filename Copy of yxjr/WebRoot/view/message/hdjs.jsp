<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://com.supwisdom/jsp/jstl/myfunctions" prefix="myfn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  <title>还款计算器</title>
  	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta name="keywords" content="翼勋金融" />
	<meta name="description" content="翼勋金融" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
 	<style>
 	*{margin:0;padding:0}
 	#radiolists{font-size:14px;line-height:45px;color:#666;margin:0 5%}
 	#radiolists input{border:0; border:1px solid #ccc;line-height:30px;padding:0 5px; color:#666}
 	#radiolists li{list-style:none}
 	#radiolists select{line-height:40px}
 	.blues{color:#20A7DA}
 	.leix{color:#red; font-size:18px;line-height:60px}
 	.startl{background:#ff0;color:#fff;width:200px;line-height:50px;font-size:14px}
 	.startj{text-align:center;display:block;text-decoration:none;color:#fff;background-color: #20A7DA;width:90%;border-radius:5px;line-height:40px}
 	.jsjg{color:#20A7DA;font-size:18px;line-height:50px;margin:0 5%}
 	.jiek{font-size:14px; color:#666;margin:0 5%;line-height:35px}
 	.jisjg{font-size:18px;color:#20A7DA; margin:0 5%}
 	.jieguo{border-collapse: collapse;margin:0 5%;width:90%}
 	.jieguo th{color:#20A7DA;font-size:12px;font-weight: 400}
 	.jieguo td{border:1px solid #ccc;font-size:12px; color:#666;width:20%;text-align:center;line-height:30px}
 	</style>
  <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
  <meta charset="utf-8">
<script src="<%=basePath%>assets/js/jquery.min.js"></script>
<script src="<%=basePath%>assets/js/amazeui.min.js"></script>
<script src="<%=basePath%>assets/js/app.js"></script>
  <script type="text/javascript" src="<%=basePath%>assets/js/jquery1.11.1.min.js"></script>
  <script type="text/javascript"  src="<%=basePath%>assets/js/layer.js"></script>
<script src="<%=basePath%>assets/js/extend/layer.ext.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#chklist').hcheckbox();
			$('#chklist2').hcheckbox();
			$('#radiolist').hradio();
			$('#radiolist2').hradio();
			$('select').comboSelect();/*select*/
		});
	</script>
</head>
<body>
 
<div >
	 
		<div>
			<ul id="radiolists"  >
				<li><span class="blues">申请金额</span> <input type="text" id="sqje" placeholder="请输入借款金额" /> 元</li>
				<li><span class="blues">借款期限</span> <input type="text"  id="jkqx"  placeholder="月请输入借款期限" /> 月</li>
				<li ><span class="leix">利率类型</span>
					<div >
						<input name='llvalue_' type="radio"   value='1' checked="checked" /><label>年利率</label>
						<input name='llvalue_' type="radio" value='2' /><label >月利率</label>
					</div>
					<div class=""></div>
				</li>
				<li><span class="blues">借款利率</span> <input type="text"  id="jkll"  placeholder="请输入借款利率" /> %</li>
				<li><span class="blues">还款方式</span>
					
						<select class=""  id="hkfs" >
						  <option value="1">等额本息</option>
						  <option value="2">等本等息</option>
						  <option value="3">先息后本</option>
						  <option value="4">按月付息到期还本</option>
						</select>
					
				</li>
				<!-- - -->
				<li style="margin-top:15px"><a href="javascript:js_lx();" class="startj">开始计算</a></li>
				
				<div>利率范围：不超过中国人民银行基准利率的4倍</div>
			</ul>
		</div>
</div>
	<script type="text/javascript">
function js_lx(){	
		 
		      var sqje=   $("#sqje").val();
		 	 var jkqx=	 $("#jkqx").val();
		 	 var jkll=	 $("#jkll").val();
		 	 var hkfs=	 $("#hkfs").val();
		 
		 if(sqje==""){return;}
		 if(jkqx==""){return;}
		 if(jkll==""){return;}
		 if(hkfs==""){return;}
		 		 
		 		  var checks = $("input[name=llvalue_]");
	var val ="";
	for(var i = 0 ; i <checks.length ; i ++ ){
			if(checks[i].checked){
				val = $(checks[i]).val();
		}
	}		
	if(val=="1"){ //年

	}else if(val=="2"){//月
		jkll=jkll*12;
	}else{
	 return ;
	}
		 		 
		 		 
		 		 
		 
		    $.post("public_product_index_lx" ,"sqje="+sqje+"&jkqx="+jkqx+"&jkll="+jkll+"&hkfs="+hkfs,function(data){
		          $("#jed_div_show_id").html("借款总额：<span class='red'>"+sqje+"</span> 元 到期还款总额：<span class='red'>"+data.ze+"元</span>");
		         var htmlval="<tr class='color-6'><th>期数</th><th>还款金额</th><th>还款本金</th><th>还款利息</th><th>剩余金额</th></tr>";
		          for(var i=0;i<data.list.length;i++){
		              htmlval=htmlval+"<tr><td>"+data.list[i].qs+"</td><td>"+data.list[i].myhkje+"</td><td>"+data.list[i].myhkbj+"</td><td>"+data.list[i].myhklx+"</td><td>"+data.list[i].syhkje+"</td></tr>";
		          }
		           $("#table_tr_show_id").html(htmlval);
		          
		         $(".popup").fadeIn(300);
			   $(".mask").fadeIn(300);
		          
		    });
		 
		 
			
		}
	
</script>
<!-- 弹窗层 -->
<div>
	<div>
		<div class="jsjg">计算结果</div>
		<a href="javascript:"></a>
	</div>
	<div>
		<div>
				<div class="jiek" id="jed_div_show_id">借款总额：<span class="red">0</span> 元 到期还款总额：<span class="red">0元</span></div>
				<div class="jisjg">计算结果(每月)</div>
				<table class="jieguo" id="table_tr_show_id" >		
			</table>
			</div>
	</div>
</div>
</body>

</html>