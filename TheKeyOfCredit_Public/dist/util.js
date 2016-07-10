Date.prototype.Format = function(fmt)   
{    
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };
  if(fmt!==null)
  {   
    if(/(y+)/.test(fmt))   
      fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
    for(var k in o)   
      if(new RegExp("("+ k +")").test(fmt))   
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
    }  
    return fmt;   
  }
function urlParse (name) { // 获取url参数
  name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]")
  var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
  results = regex.exec(location.search)
  return results == null ? "": decodeURIComponent(results[1])
}
$(function () {
//localStorage.removeItem("openid"); 

//modify begin 2016.7.10 更改查找page的代码（因为微信头像的网址数据中包含'/')
/*var page = location.href.split('/').pop();
if (page.indexOf('?') > -1) {
 page = page.split('?')[0];
}*/
var page = location.href.split('?')[0];
page = page.split('/').pop();
//modify end

console.log(page);

var tStrUserMapInfo = urlParse('map');
if(tStrUserMapInfo !== "")
{
	var tUserData = JSON.parse(tStrUserMapInfo);
	if(tUserData !== null)
	{
        localStorage.setItem('openid', tUserData.openid);
        localStorage.setItem('nickname', tUserData.nickname);
        localStorage.setItem('headimg', tUserData.headimgurl);
		if(tUserData.hasOwnProperty('mobile'))
		{
			localStorage.setItem('Mobile', tUserData.mobile);
		}
	}
}

var tStrOpenID = localStorage.getItem('openid');
var tStrMobile = localStorage.getItem('Mobile');
if(tStrOpenID == null || tStrOpenID == "")
{
	window.location.replace('oauth.html?source=6&target=index.html&pageName='+page);
	return false;
}

if(localStorage.getItem('openid') !== null)
{
  if(page=='index.html'){

    $(".tag").html(localStorage.getItem('nickname'));
	if(tStrMobile !== null)
	{
	    $(".mobile").html(tStrMobile);
	}
    if(localStorage.getItem('headimg')!==null)
    {
      $("#userimg").attr("src",localStorage.getItem('headimg')); 
    }
    var Key = "KoeIy12Ay~oEuN3" + new Date().Format("yyyyMMdd");
    if(tStrMobile !== null ){
              //正式
              //http://120.55.161.84:8064/
              console.log(tStrMobile);
              $.ajax({
                //url : 'http://121.40.185.130:8072/api/CreditKey/userCreditCapitalHis',
                url : 'http://120.55.161.84:8064/api/CreditKey/userCreditCapitalHis',
                data : {'checkMsg':$.md5(Key),mobile:tStrMobile},
                type:'POST',
                dataType : 'json',
                async : false,
                success : function(data) {
                  console.log(data);
                  if(data.Code=='00'){
                    if(!data.Data.success) {
                    //审核中
                    $(".toptext").html('授信审核中，请耐心等待');
                  }else{
                    $(".yo-header-normal2").hide();
                    $("#surplusAmount").html(data.Data.CumConsumption);
                    $("#CreditAmount").html(data.Data.creditAmount);
                  }
                }
              },
              error : function(XMLHttpRequest,textStatus, errorThrown) {}
            });
            }
          }else if(page == 'register.html' && localStorage.getItem('Mobliel') !== null)
		  {
			  //已绑定的用户，点击手机号码，无效
		   	  return false;
		  }
        }else{
          if(location.search==""){
            //console.log(2); 
            window.location.replace('oauth.html?source=6&target=index.html&pageName='+page) 
            return false;
          }else{
        if(page=='shenqing.html'){
          if(urlParse('Goodsnull')!=="")
          {
            window.location.replace('oauth.html?source=6&target=index.html&pageName='+page) 
          }
        }
        var arr= location.search.split("%22");
        localStorage.setItem('openid',arr[3]);
        $.ajax({
          url : 'http://Keyofcredit.eifm.net/weixin/wxuname',
          data : {wx: arr[3]},
          type:'POST',
          dataType : 'json',
          async : false,
          success : function(data) {
            console.log(data);
            if(data.code=='00'){
              localStorage.setItem('userData',JSON.stringify(data.date));
              localStorage.setItem('openid',data.date.wx);
              localStorage.setItem('nickname', data.date.username);
              localStorage.setItem('headimg', data.date.headimg);
              if(page=='index.html'){
               $(".tag").html(data.date.username);
               $("#userimg").attr("src",data.date.headimg); 
               $.ajax({
                  //url : 'http://121.40.185.130:8072/api/CreditKey/userCreditCapitalHis',
                  url : 'http://120.55.161.84:8064/api/CreditKey/userCreditCapitalHis',
                  data : {'checkMsg':$.md5(Key),mobile:tStrMobile},
                  type:'POST',
                  dataType : 'json',
                  async : false,
                  success : function(data) {
                   console.log(data);
                   if(data.Code=='00'){
                     if(!data.Data.success) {
	                    //审核中
	                    $(".toptext").html('授信审核中，请耐心等待');
	                  }else{
                     $(".yo-header-normal2").hide();
                     $("#surplusAmount").html(data.Data.CumConsumption);
                     $("#CreditAmount").html(data.Data.creditAmount);
                   }
                 }
               },
               error : function(XMLHttpRequest,textStatus, errorThrown) {}
             });
             }

           }else
           {
            alert(data.msg);
          }
        },
        error : function(XMLHttpRequest,textStatus, errorThrown) {}
      });
} 
}










  // 注意：所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。 
  // 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
  // 完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
//  wx.config({
//	//debug: true, 
//    appId: appId,
//    timestamp: timestamp,
//    nonceStr: nonceStr,
//    signature: signature,
//    jsApiList: [
//		'checkJsApi',
//		'onMenuShareTimeline',
//		'onMenuShareAppMessage',
//		'onMenuShareQQ',
//		'onMenuShareWeibo',
//		'hideMenuItems',
//		'showMenuItems',
//		'hideAllNonBaseMenuItem',
//		'showAllNonBaseMenuItem',
//		'translateVoice',
//		'startRecord',
//		'stopRecord',
//		'onRecordEnd',
//		'playVoice',
//		'pauseVoice',
//		'stopVoice',
//		'uploadVoice',
//		'downloadVoice',
//		'chooseImage',
//		'previewImage',
//		'uploadImage',
//		'downloadImage',
//		'getNetworkType',
//		'openLocation',
//		'getLocation',
//		'hideOptionMenu',
//		'showOptionMenu',
//		'closeWindow',
//		'scanQRCode',
//		'chooseWXPay',
//		'openProductSpecificView',
//		'addCard',
//		'chooseCard',
//		'openCard'
//    	]
//  });
// 
//  var titles=["免费选个股赢百元奖金，把你眼光换成钱","拼眼光选个股，每天都有百元奖金等你拿"];
//  //,"股市新秀在此，我收益排**，你能排多少？"
//  
//  var BASE_URL=  null;
//  var lineLink = BASE_URL+'fn_system.php?friendid='+openid;
//  var shareTitle = titles[Math.floor(Math.random()*2)], descContent = titles[Math.floor(Math.random()*2)];
//
//  function changeTitle() {
//  	lineLink=BASE_URL;
//  	shareTitle = titles[Math.floor(Math.random()*2)];
//  	descContent = shareTitle;
//  	if (page == 'index.php')
//	    {
//	  		
//		    		lineLink=BASE_URL+'fn_system.php?friendid='+openid;
//				    shareTitle = titles[Math.floor(Math.random()*2)];
//	  		
//	    }
//	else
//	  	{
//				
//	  			lineLink=BASE_URL+'fn_system.php?friendid='+openid;
//		    	shareTitle  = titles[Math.floor(Math.random()*2)];
//	  	}
//	}
//  wx.ready(function () {
//	  
//	  changeTitle();
//	  wx.checkJsApi({
//		    jsApiList: ['onMenuShareTimeline'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
//		    success: function(res) {
//		    	//alert('chooseImage'+res);
//		        // 以键值对的形式返回，可用的api值true，不可用为false
//		        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
//		    	}
//		    });
//	  
//	  wx.onMenuShareAppMessage({
//	        title: shareTitle, // 分享标题
//	        desc: descContent, // 分享描述
//	        link: lineLink, // 分享链接
//	        imgUrl: "http://myherochina.com/MySecurity/images/sharelogo.png", // 分享图标
//	        type: '', // 分享类型,music、video或link，不填默认为link
//	        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
//	        success: function () {
//	            // 用户确认分享后执行的回调函数
//	            $(".pop").hide();
//	           _czc.push(['_trackEvent', page, 'ShareAppMessage', 'WeixinJSBridge','1','0']);
//	        },
//	        cancel: function () {
//	            // 用户取消分享后执行的回调函数
//	        }
//	    });
//
//	  wx.onMenuShareTimeline({
//	        title: shareTitle, // 分享标题
//	        link: lineLink, // 分享链接
//	        imgUrl: "http://myherochina.com/MySecurity/images/sharelogo.png", // 分享图标
//	        success: function () {
//	            // 用户确认分享后执行的回调函数
//	            $(".pop").hide();
//	        	 _czc.push(['_trackEvent', page, 'ShareTimeline', 'WeixinJSBridge','1','0']);
//	        },
//	        cancel: function () {
//	            // 用户取消分享后执行的回调函数
//	        }
//	    });
//  });

});
