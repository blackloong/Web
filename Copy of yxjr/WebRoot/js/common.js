/**
 * 分页执行sql 
 * 如果为form提交则取form取第一个表单
 * 
 */
function goPage (page) {
	var form = document.forms[0];
	if(form){
		var pageInput = document.createElement("input");
		pageInput.name="currentPage";
		pageInput.value=page;
		pageInput.type="hidden";
		form.appendChild(pageInput);
		form.submit();
	}else{
		var url = document.location;
		if(url.indexOf("?")>-1){
			if(url.indexOf("&currentPage=")>-1){
				var reg = /currentPage=\d*/g;
			    url =	url.replace(reg,"currentPage="+page);
			}else{
				url +="&currentPage="+page
			}
		}else{
			url +="?currentPage="+page;
		}
		window.location.href = url;
	}
}
$(function(){
	/**
	 * 选项全选
	 */
	$("#selectAllCheck").change(
			function(){
				var checkValue = this.checked;
				var checks = $("input[type=checkbox]");
				for (var i = 0 ; i < checks.length ; i ++){
					checks[i].checked = checkValue;
				}
			}
	);
});
var error = "请选择图片文件";
function previewImage(file,divId)
{
  //验证图片大小
  var fileSize = $(file)[0].files[0].size/1024; //kb
  if(fileSize>4000){
	  layer.alert("请上传小于4m的图片");
	  error = "请上传小于4m的图片";
	  return;
  }
  //验证文件类型
  var fileName = $(file).val();
  var fileExt = fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
  var allImgExt=".jpg|.jpeg|.gif|.bmp|.png|";
  if(allImgExt.indexOf(fileExt)<=-1){
	  layer.alert("仅支持jpg，jpeg，gif，bmp,png格式的图片");
	  error = "仅支持jpg，jpeg，gif，bmp,png格式的图片";
	  return;
  }
  var MAXWIDTH  = 260; 
  var MAXHEIGHT = 180;
  var div = document.getElementById(divId);
  if (file.files && file.files[0])
  {
      div.innerHTML ='<img id="imghead" class="am-img-circle am-img-thumbnail" style="margin-top:5px;">';
      var img = document.getElementById('imghead');
      img.onload = function(){
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        img.width  =  rect.width;
        img.height =  rect.height;
        img.style.marginTop = rect.top+'px';
      };
      var reader = new FileReader();
      reader.onload = function(evt){img.src = evt.target.result;};
      reader.readAsDataURL(file.files[0]);
  }
  else //兼容IE
  {
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
    file.select();
    var src = document.selection.createRange().text;
    div.innerHTML = '<img id=imghead>';
    var img = document.getElementById('imghead');
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
  }
  error = "";
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight )
    {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
         
        if( rateWidth > rateHeight )
        {
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else
        {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
     
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}
function showImage(obj) {
	var img = new Image();
	img.src = $(obj).attr("src");
	var hight = img.height;
	var width = img.width;
	var imgHidde = document.createElement("img");
	imgHidde.src=$(obj).attr("src");
	imgHidde.id = "imgID";
	$("#hiddenImg").remove();
	$("body").append("<img src='"+$(obj).attr("src")+"' id='hiddenImg' style='display:none'>");
	layer.open({
	    type: 1,
	    title: false,
	    closeBtn: true,
	    area: [width,hight],
	    skin: 'layui-layer-nobg', //没有背景色
	    shadeClose: true,
	    content:$("#hiddenImg")
	});
}
