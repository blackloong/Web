<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!doctype html>
<html class="no-js">
<head>
<%@include file = "../head.jsp"%>
<script src="<%=basePath%>assets/js/ajaxfileupload.js"></script>

<script type="text/javascript" >
	
	function ajaxUpload () {
		 $.ajaxFileUpload
	            (
	                {
	                    url: 'bannerSub', //用于文件上传的服务器端请求地址
	                    secureuri: false, //是否需要安全协议，一般设置为false
	                    fileElementId: 'file1', //文件上传域的ID
	                    success: function (data, status)  //服务器成功响应处理函数
	                    {	
	                    	layer.msg("上传成功");
	                    	loadData();
	                    	
	                    },
	                    error: function (data, status, e)//服务器响应失败处理函数
	                    {
	                        layer.msg(data.errorMsg);
	                    }
	                }
	            );
	}
	function del (id){
		layer.confirm('确定删除么?',function (){
			$.ajax({
				url:"bannerDel?id="+id,
				success:function (){
					layer.msg("删除成功");
					loadData();
				}
			});	
		});
	}
	function loadData () {
		var index = layer.load(1, {
				    shade: [0.1,'#fff'] //0.1透明度的白色背景
				});
		$.ajax({
			url:"bannerAjax",
			success:function (data) {
				var html = "";
				for (var i = 0 ; i < data.length ; i ++ ){
					var d = data[i];
					html +='<li>\
						        <a href="#">\
						          <img class="am-img-thumbnail am-img-bdrs" src="'+d.imgsource+'" alt="" onclick="showImage(this);" />\
						        </a>\
						         <a class="gallery-title" href="javascript:del('+d.id+')" >删除 </a>\
						      </li>';
				}
				$("#ulimg").html(html);
				layer.close(index);
			}
		
		});
		
	}
</script>

</head>
<body onload="loadData();">
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">滚动图</strong> </div>
    </div>

    <ul class="am-avg-sm-2 am-avg-md-4 am-avg-lg-6 am-margin gallery-list" id="ulimg">
    </ul>
     <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8" style="float: left;">
        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="am-g">
              <div class="am-u-md-4" id="imgDiv">
                <img class="am-img-circle am-img-thumbnail" src="assets/images/addimg.jpg" alt=""/>
              </div>
              <div class="am-u-md-8">
                <form class="am-form">
                  <div class="am-form-group">
                    <input type="file" id="file1"  onchange="previewImage(this,'imgDiv')" name="img">
                    <p class="am-form-help">请选择要上传的文件...</p>
                    <a type="button" class="am-btn am-btn-primary am-btn-xs" href="javascript:;" onclick="ajaxUpload();" >保存</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        </div>
  </div>
  <!-- content end -->
</div>
</body>
</html>