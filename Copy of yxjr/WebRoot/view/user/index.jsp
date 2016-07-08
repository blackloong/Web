<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>用户轨迹图集合</title> 
<%@include file = "../head.jsp"%>
   <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=b893c5b94b507754c8a5d8d6034f54d8"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>

<script type="text/javascript">
	var mapObj ;
function init() {
	 		mapObj = new AMap.Map("map");
		    mapObj.plugin(["AMap.ToolBar", "AMap.OverView", "AMap.Scale","AMap.PlaceSearch"], function () {
                toolbar = new AMap.ToolBar();
                toolbar.autoPosition = false; //加载工具条
                mapObj.addControl(toolbar);
                overview = new AMap.OverView(); //加载鹰眼
                mapObj.addControl(overview);
                scale = new AMap.Scale(); //加载比例尺
                mapObj.addControl(scale);
                
            });
            
               AMap.service(["AMap.Geocoder"], function() {        
		        MGeocoder = new AMap.Geocoder({ 
		            city:"全国", //城市，默认：“全国”
		            radius:1000 //范围，默认：500
		        });
	        });
	  ajaxMarker();
	
	
	
}
	function ajaxMarker(){
	  mapObj.clearMap();
		 $("#queryForm").ajaxSubmit({
			url:"<%=basePath%>getLng",
			type:"post",
			dataType:"json",
			success:function (data) {
			//alert(data)
				if(data)
				{
						for(var i = 0 ; i < data.length ; i ++ )
						{
							addMark(data[i].longitude,data[i].latitude,data[i].username,data[i].mobile,data[i].acq_date,i);
						}
				}
			}
		});
	}
	//var logType = '${log.type}';
	function addMark (lng,lat,name,mob,date,index){
				var marker = new AMap.Marker({
									id:"ysm23"+index,//唯一ID
									position:new AMap.LngLat(lng,lat), //基点位置
									icon:"view/images/maps.png", //marker图标，直接传递地址url
									offset:{x:-8,y:-34} //相对于基点的位置
							});
                    marker.setMap(mapObj);////添加坐标
				var moreInfo = "";
				 
				 //alert(lng+","+lat);
			var infoWindow = new AMap.InfoWindow({
            content:"姓名: "+name+"&nbsp;&nbsp;<br/>手机号 : "+mob+"&nbsp;&nbsp;<br/>时间"+date,
            size: new AMap.Size(0, 0),
            autoMove: true,
            offset: new AMap.Pixel(0, -30)
        });
					
				
		   var aa = function() {
            infoWindow.open(mapObj, marker.getPosition());
            };
				marker.on("mouseover", aa); //添加员工信息显示
				
				if(index==0){
					mapObj.setCenter(marker.getPosition());	
				}
				
	}
	
	function addMarker(e) {
		i++;
		var mark = new AMap.Marker ({
			id :"m"+i,
			position:new AMap.LngLat(e.lnglat.lng,e.lnglat.lat),
			icon:"http://api.amap.com/webapi/static/Images/marker_sprite.png"//marker图标，直接传递地址url
		});
		    marker.setMap(mapObj);//添加坐标
		
	}
	
	function TipContents(type, address, tel) {  //窗体内容  
	    if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {  
	        type = "暂无";  
	    }  
	    if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {  
	        address = "暂无";  
	    }  
	    if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {  
	        tel = "暂无";  
	    }  
	    var str = "&nbsp;&nbsp;地址：" + address + "<br />&nbsp;&nbsp;电话：" + tel + " <br />&nbsp;&nbsp;类型：" + type;  
	    return str;  
}  

</script>
</head>

<body onload="init();">

 

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
             <!--  <a type="button" class="am-btn am-btn-default" href="dotedit"><span class="am-icon-plus"></span> 新增</a>
              <a type="button" class="am-btn am-btn-default" href="javascript:deldot();"><span class="am-icon-trash-o"></span> 删除</a> -->
            </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
          	<form action="dotlist" method="post" id="queryForm">
	           <table style="margin-left: 10px;">
			    <tr>
	                      	<td nowrap="nowrap" style="padding-left: 10px;"><input
								type="text" name="uname" value="${formData.uname }"
								class="am-form-field" placeholder="姓名">
							</td>
							<td nowrap="nowrap" style="padding-left: 10px;"><input
								type="text" name="createtime" id="createtime"
								value="${formData.createtime }" class="am-form-field"
								placeholder="开始时间" onFocus="WdatePicker({createtime: '%y-%M-{%d}' ,dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
							</td>
							
							<td nowrap="nowrap" style="padding-left: 10px;"><input
								type="text" name="endtime" id="endtime"
								value="${formData.endtime }" class="am-form-field" 
								placeholder="结束时间"
								onFocus="WdatePicker({endtime: '%y-%M-{%d}' ,dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
							</td>
				</tr>
				</table>
	        </form>
	                <span class="am-input-group-btn">
	                  <button class="am-btn am-btn-default" type="button" onclick="ajaxMarker()">搜索</button>
	                </span>
          </div>
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
           
   
	 <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
	 <tr>
        	
            <td valign="top">
            	<div id="result"  width="1200"></div>
            	<div id="map" style=" margin-top:50px; border:5px solid #ccc; width:1840px; margin-left:30px; float:left;height:900px">
            	</div>
           </td>
           </tr>
           </table>
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