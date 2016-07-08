<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://com.supwisdom/jsp/jstl/myfunctions" prefix="myfn" %>

<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
<link href="assets/js/umeditor/themes/default/css/umeditor.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript"
	src="assets/js/umeditor/third-party/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="assets/js/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="assets/js/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets/js/umeditor/lang/zh-cn/zh-cn.js"></script>

	<link rel="stylesheet" href="<%=basePath%>kindeditor4_1_10/themes/default/default.css" />
	<link rel="stylesheet" href="<%=basePath%>kindeditor4_1_10/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=basePath%>kindeditor4_1_10/kindeditor.js"></script>
	<script charset="utf-8" src="<%=basePath%>kindeditor4_1_10/lang/zh_CN.js"></script>
	<script charset="utf-8" src="<%=basePath%>kindeditor4_1_10/plugins/code/prettify.js"></script>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=b893c5b94b507754c8a5d8d6034f54d8"></script>
	
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

	function saveDot () {
		$("#saveDot").submit();
	}	
	
	
	// 地理编码
 
     var  types;
     var start_xy1;
     var end_xy1 
	function geocoder(ds) {
	// alert(ds);
		
	var marker = new Array();
		var windowsArr = new Array(); 
		var map = new AMap.Map("mapContainer", {
			resizeEnable: true
		});
		
 	    var MGeocoder;
	    //加载地理编码插件
	    AMap.service(["AMap.Geocoder"], function() {        
	        MGeocoder = new AMap.Geocoder({ 
	            city:"全国", //城市，默认：“全国”
	            radius:1000 //范围，默认：500
	        });
	        //返回地理编码结果  
	        //地理编码
	        MGeocoder.getLocation(ds, function(status, result){
	        	if(status === 'complete' && result.info === 'OK'){
	        		geocoder_CallBack(result);
	        	}
	        	else{
	        	    
 				 ("#deliAddress").val("");
 					        	    
	        	}
	        	
	        	
	        	
	        });
	    });
	}  
	
	
	
		      //这里是地图 输入地址获取 坐标的方法
		function geocoder_CallBack(data){
		    var resultStr="";
		    //地理编码结果数组
		    var geocode = new Array();
		    geocode = data.geocodes;  
		   
			    for (var i = 0; i < geocode.length; i++) {
					  
						document.getElementById("lng").value=geocode[i].location.getLng();
						document.getElementById("lat").value=geocode[i].location.getLat();
		       }
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">门店编辑</strong> </div>
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
        <form class="am-form am-form-horizontal" action="dotsave" method="post" id="saveDot">
        	<input name="id" type="hidden" value="${data.id }" required="required"/>

			<div class="am-form-group"> <label for="user-phone" class="am-u-sm-3 am-form-label">门店简称 </label> 
			 <div class="am-u-sm-9" style="width: 70%">  <input type="text" required="required"  value= "${data.dot }" name="dot">  </div>  </div>   
			<div class="am-form-group"> <label for="user-phone" class="am-u-sm-3 am-form-label">所在省</label> 
			<div class="am-u-sm-9" style="width: 70%"> 
			<select name="lo_sh" required="required" ><dic:loadSelectTag tableName='tb_dictionary' key='id' text='value' id='${data.lo_sh}_id' where=" and pid=14  " /></select>
	         </div> </div>
			<div class="am-form-group"> <label for="user-name"  class="am-u-sm-3 am-form-label">负责人</label> 
			<div class="am-u-sm-9" style="width: 70%">   <input type="text"  required="required" value="${data.gl_name }" name="gl_name">  </div> </div>
			<div class="am-form-group"> <label for="user-phone" class="am-u-sm-3 am-form-label">联系电话</label>    
			<div class="am-u-sm-9" style="width: 70%">  <input type="text"  required="required" value= "${data.gl_phone }" name="gl_phone" >   </div> </div>
			<div class="am-form-group"> <label for="user-phone" class="am-u-sm-3 am-form-label">是否上线</label>
			<div class="am-u-sm-9" style="width: 70%">
			 <input type="radio" required="required"  value= "0" name="isline" <c:if test="${data.isline!=1}"> checked="checked"</c:if> >  是   <input type="radio"  value= "1" name="isline" <c:if test="${data.isline==1}"> checked="checked"</c:if> >  否
			 </div></div>
			<div class="am-form-group"> <label for="user-phone" class="am-u-sm-3 am-form-label">所在地详细地址 </label> 
			<div class="am-u-sm-9" style="width: 70%">  <input type="text" required="required"  value= "${data.details }" name="details" onblur="geocoder(this.value)" id="deliAddress">  </div>  </div>   
			<div class="am-form-group"> <label for="user-phone" class="am-u-sm-3 am-form-label">经度</label> 
			<div class="am-u-sm-9" style="width: 70%"> <input type="text" required="required"  value= "${data.longitude }" name="longitude" id="lng" >  </div> </div>
			<div class="am-form-group"> <label for="user-name"  class="am-u-sm-3 am-form-label">纬度</label> 
			<div class="am-u-sm-9" style="width: 70%">   <input type="text" required="required"  value="${data.latitude }" name="latitude" id="lat">  </div> </div>
			
			<div class="am-form-group"> <label for="user-name"  class="am-u-sm-3 am-form-label">原翼勋id</label> 
			<div class="am-u-sm-9" style="width: 70%">   <input type="text" required="required"  value="${data.description }" name="description" id="description">  </div> </div>
			
			
			 <div class="am-form-group">
            <label for="user-phone" class="am-u-sm-3 am-form-label">描述</label>
            <div class="am-u-sm-9" style="width: 70%">
               <textarea class="article_tip" id="myEditor" name="bdesc" style="width: 790px; height: 252px;" >${data.bdesc }</textarea> 
              
            </div>
          </div>
          
          
			
          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <a href="javascript:saveDot();"  class="am-btn am-btn-primary">保存修改</a>
            </div>
          </div>
        </form>
      </div>
      
    </div>
  </div>
  <!-- content end -->
</div>
         	<p  id="mapContainer" >

<footer>
  <hr>
  <p class="am-padding-left"><!--© 2014 AllMobilize, Inc. Licensed under MIT license. <a href="http://www.mycodes.net/" target="_blank">源码之家</a> --></p>
</footer>

<script type="text/javascript">
    var um = UM.getEditor('myEditor');
</script>
</body>
</html>