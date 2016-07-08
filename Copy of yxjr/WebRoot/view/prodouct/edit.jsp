<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://com.supwisdom/jsp/jstl/myfunctions" prefix="myfn" %>

<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>kindeditor4_1_10/themes/default/default.css" />
	<link rel="stylesheet" href="<%=basePath%>kindeditor4_1_10/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=basePath%>kindeditor4_1_10/kindeditor.js"></script>
	<script charset="utf-8" src="<%=basePath%>kindeditor4_1_10/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=basePath%>kindeditor4_1_10/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="description"]', {
			
				cssPath : '<%=basePath%>kindeditor4_1_10/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>kindeditor4_1_10/jsp/upload_json.jsp',
				fileManagerJson : '<%=basePath%>kindeditor4_1_10/jsp/file_manager_json.jsp',
				allowFileManager : true,
				
				afterBlur: function(){this.sync();}
				
			});
			prettyPrint();
		});
	</script>


<script type="text/javascript" >

$(function(){
 
	regInfo();
	
	var ss= $("#idptype2").val();
		

});

	function prodouct_save () {
		$("#prodouct_save").submit();
		
	}	
	
	function regInfo() {
		 var  sspid=$("#idptype2").val();
 		var pram={
		   id:$("#ptype1").val(),
		 };
		 
		$.ajax({
			url : '<%=basePath%>prodouctTypelist',
			type : 'post',
			data:pram,
			datatype : 'json',
			
			success : function(date) {
				$("#ptype2").empty();
				//alert(date.smPeciesList[0].id)
			    html="";  
			   $.each(date.data, function(i,val){	 
			     
			        if(sspid==val.id){
			            html+= '<option value ="'+val.id+'"    selected="selected" > '+val.value+'</option>';     
			        
			        }
			        
			        else{
			        
			          html+= '<option value ="'+val.id+'">'+val.value+'</option>';     
			        
			        }
 			       
			   }); 
			   	 $("#ptype2").append(html);   
			   	 regInfo1();
			   
			}
		});
		}
	
	
		function regInfo1() {
		 var  sspid=$("#idptype3").val();
		 		
		 
		var pram={
		   id:$("#ptype2").val(),
		 };
		 
		 
		$.ajax({
			url : '<%=basePath%>prodouctTypelist',
			type : 'post',
			data:pram,
			datatype : 'json',
			
			success : function(date) {
				$("#ptype3").empty();
				//alert(date.smPeciesList[0].id)
			    html="";  
			   $.each(date.data, function(i,val){	 
			     
			        if(sspid==val.id){
			            html+= '<option value ="'+val.id+'"    selected="selected" > '+val.value+'</option>';     
 			        }
			        
			        else{
			        
			          html+= '<option value ="'+val.id+'">'+val.value+'</option>';     
			        
			        }
 			       
			   }); 
			   	 $("#ptype3").append(html);   
			   
			}
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

    <hr/>

    <div class="am-g">

    <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      	<div class="">
          <div class="">
            <div class="am-g">
              <div class="am-u-md-4" id="imgDiv">
                  </div>
              <div class="am-u-md-8"><p> </p><div class="am-form-group"> <p class="am-form-help"></p></div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form class="am-form am-form-horizontal" action="prodouct_save"   enctype="multipart/form-data" method="post" id="prodouct_save">
        	<input name="id" type="hidden" value="${data.id }"/>       	
         	<input id="idptype2"  type="hidden" value="${data.ptype2 }"/>
        	<input id="idptype3" type="hidden" value="${data.ptype3 }"/>
				<div class="am-form-group">  <label for="user-name" class="am-u-sm-3 am-form-label">产品名称:</label>
				<div class="am-u-sm-9" style="width: 75%"> <input type="text"  value="${data.title }" name="title"></div> </div>  
				
		   <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">一级分类</label>
            <div class="am-u-sm-9">
              <select name="ptype1" id="ptype1" onchange ="regInfo()">
               <c:forEach items="${datauser }" var="item">
               <option value="${item.id }" <c:if test="${item.id==data.ptype1 }"> selected="selected"</c:if> >${item.value }</option>
                <!--
                <option value="${item.id }">${item.value }</option> -->
             
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">二级分类</label>
            <div class="am-u-sm-9">
              <select name="ptype2" id="ptype2" onchange ="regInfo1()">
               </select>
            </div>
          </div>
          <!-- <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">三级分类</label>
            <div class="am-u-sm-9">
              <select name="ptype3" id="ptype3">
              
                </select>
            </div>
          </div> -->
    
				<div class="am-form-group"> <label for="user-name" class="am-u-sm-3 am-form-label">产品编号:</label>
				<div class="am-u-sm-9" style="width: 75%"> <input type="text"  value="${data.procode }" name="procode"></div> </div>
				<div class="am-form-group"> <label for="user-name" class="am-u-sm-3 am-form-label">产品利率:</label>
				<div class="am-u-sm-9" style="width: 75%"> <input type="text"   name="pro_rate" value="${data.pro_rate}"></div> </div>		     
				<div class="am-form-group"> <label for="user-name" class="am-u-sm-3 am-form-label">产品周期:</label>
				<div class="am-u-sm-9" style="width: 75%"> <input type="text"   name="pro_cycle"  value="${data.pro_cycle}"></div> </div>
				<div class="am-form-group"> <label for="user-name" class="am-u-sm-3 am-form-label">app端背景:</label>
				<div class="am-u-sm-9" style="width: 75%"  > <input type="file"   name="pro_logo"> ${data.pro_logo_value}</div> </div>
				<div class="am-form-group"> <label for="user-name" class="am-u-sm-3 am-form-label">产品申请最大额度:</label>
				<div class="am-u-sm-9" style="width: 75%"> <input type="text"  value="${data.pro_max_amount }"   name="pro_max_amount"></div> </div>
				<div class="am-form-group"> <label for="user-name" class="am-u-sm-3 am-form-label">产品申请最小额度:</label>
				<div class="am-u-sm-9" style="width: 75%"> <input type="text"  value="${data.pro_min_amount }"   name="pro_min_amount"></div> </div>
				<div class="am-form-group"> <label for="user-name" class="am-u-sm-3 am-form-label">排序:</label>
				<div class="am-u-sm-9" style="width: 75%"> <input type="text"  value="${data.sort }" name="sort"></div> </div>
				<div class="am-form-group"> <label for="user-phone" class="am-u-sm-3 am-form-label">产品描述:</label>
				<div class="am-u-sm-9" style="width: 75%">
				<textarea name="description"  rows="8" style="visibility:hidden;">
			${data.description }
			</textarea> 
				</div> </div>    
				<div class="am-form-group">
				<div class="am-u-sm-9 am-u-sm-push-3">
				<a href="javascript:prodouct_save();"  class="am-btn am-btn-primary">保存修改</a>
				</div>
				</div>
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