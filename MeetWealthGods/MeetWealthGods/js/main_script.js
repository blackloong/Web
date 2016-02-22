//迎财神页面的变量
var body = document.getElementById("body");
var bg = document.getElementById("bg");
var btnMeetGods = document.getElementById("btnMeetGods");

//接财神页面的变量
var btnPayGods = document.getElementById("btnPayGods");
var previewGodBoxs = document.getElementsByClassName("previewGodsBox");
var textMeetPay2 = document.getElementById("textMeetPay");
var curPGBRotateIndex = 0;//当前旋转的索引

//迎财神成功的页面变量
var godsInfo = [{ name: "文财神", desc: "今年您的财运在于静，平稳保险是你的发财关键词", img: "img/WG_Wen.png" },
               { name: "武财神", desc: "您今年的财运在于运动，走南闯北的辛苦不白瞎", img: "img/WG_Wu.png" },
               { name: "北路财神", desc: "您今年的财运在北方，东北这嘎达老有财啦", img: "img/WG_Bei.png" },
               { name: "南路财神", desc: "您今年的财运在南方，广东人民欢迎你", img: "img/WG_Nan.png" },
               { name: "西路财神", desc: "您今年的财运在西方，西部开发靠你啦", img: "img/WG_Xi.png" },
               { name: "东路财神", desc: "您今年的财运在东方，高大上的江浙沪最适合你", img: "img/WG_Dong.png" },
               { name: "偏财神", desc: "今年您的偏财运超棒的，记得要养成买彩票的好习惯哦", img: "img/WG_Pian.png" }];
var imgGuagnLun = document.getElementById("imgGuanglun");
var textGodTitle = document.getElementById("godTitle");
var textGodName = document.getElementById("godName");
var textGodDesc = document.getElementById("godDesc");
var imgGod = document.getElementById("imgMyGod");
var btnPay = document.getElementById("btnPay");
var btnShare = document.getElementById("btnShare");
var curGuangLunRotateCount = 0;//当前光轮旋转的次数
var GuangLunRotateDeg = 180; //光轮每次旋转的角度
//var gMeetPayIndex = 1;
//var gMeetPay = 3.88;

//背景音乐
var audioBG = document.getElementById("audioBG");

function WxPayJsApiCall() {
    var jsonPayInfo = JSON.parse(gWxPayJsApiParameters);
    wx.chooseWXPay({
        timestamp: jsonPayInfo.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
        nonceStr: jsonPayInfo.nonceStr, // 支付签名随机串，不长于 32 位
        package: jsonPayInfo.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
        signType: jsonPayInfo.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
        paySign: jsonPayInfo.paySign, // 支付签名
        success: function (res)
        {
            // 支付成功后的回调函数
            //alert('pay sucess' + res);
            SetPageInfo(3);
        },
        fail:function(res)
        {
            //alert('pay cancel');
        }
    });
}

function MeetGods()
{
    SetPageInfo(2);
}

function PayGods()
{
    //SetPageInfo(3);
    if (typeof WeixinJSBridge == 'undefined') {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', WxPayJsApiCall, false);
        }
        else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', WxPayJsApiCall);
            document.attachEvent('onWeixinJSBridgeReady', WxPayJsApiCall);
        }
    }
    else {
        WxPayJsApiCall();
    }
}

var RotatePreviewGodBoxs = function()
{
    var intervalDeg = 360 / previewGodBoxs.length;
    for (var i = 0; i < previewGodBoxs.length; ++i)
    {
        previewGodBoxs[i].style.transform = "rotate(" + ((i + curPGBRotateIndex) * intervalDeg) % 360 + "deg)";
    }
    ++curPGBRotateIndex;
    if (curPGBRotateIndex >= previewGodBoxs.length)
        curPGBRotateIndex = 0;
}

var RotateGuanglun = function()
{
    imgGuagnLun.style.transform = "rotate(" + ++curGuangLunRotateCount * GuangLunRotateDeg + "deg)";
    curGuangLunRotateCount = curGuangLunRotateCount % (360 / GuangLunRotateDeg);
}

function SetPreviewGodBoxsDisplay(bDisplay)
{
    var intervalDeg = 360 / previewGodBoxs.length;
    for (var i = 0; i < previewGodBoxs.length; ++i)
    {
        previewGodBoxs[i].style.display = bDisplay ? "" : "none";
        previewGodBoxs[i].style.transform = "rotate(" + i * intervalDeg + "deg)";
        previewGodBoxs[i].style.transitionDuration = "2s";
    }
}

function SetPageInfo(inPageIndex)
{
    switch(inPageIndex)
    {
        case 1://起始页面
            SetPreviewGodBoxsDisplay(false);
            textMeetPay2.style.display = "none";
            break;
        case 2://接财神的界面
            bg.style.background = "url(img/page2.jpg) no-repeat";
            bg.style.backgroundSize = "100% 100%";
            btnMeetGods.style.display = "none";
            btnPayGods.style.display = "";
            textMeetPay2.style.display = "";
            textMeetPay2.textContent = gMeetPay / 100;
            SetPreviewGodBoxsDisplay(true);
            window.setInterval(RotatePreviewGodBoxs, 3000);
            break;
        case 3://成功接到财神的界面
            bg.style.background = "url(img/page3.jpg) no-repeat";
            bg.style.backgroundSize = "100% 100%";
            btnPayGods.style.display = "none";
            textMeetPay2.style.display = "none";
            textGodTitle.style.display = "";
            textGodName.style.display = "";
            textGodDesc.style.display = "";
            imgGod.style.display = "";
            imgGuagnLun.style.display = "";
//            btnPay.style.display = "";
            btnShare.style.display = "";
            textGodName.textContent = godsInfo[gMeetPayIndex].name;
            textGodDesc.textContent = godsInfo[gMeetPayIndex].desc;
            imgGod.style.background = "url(" + godsInfo[gMeetPayIndex].img + ") no-repeat";;
            imgGod.style.backgroundSize = "100% 100%";
            SetPreviewGodBoxsDisplay(false);
            window.setInterval(RotateGuanglun, 2500);
            break;
        default:
            break;
    }
}

function PlayBG()
{
    audioBG.play();
}

function OpenShareGuide()
{
    $('.shareGuide').show();
}

function CloseShareGuide()
{
    $('.shareGuide').hide();
}

$(function () {
    $('#pageBackground').height($(window).height());
    $('#pageBackground').width($(window).width());
    SetPageInfo(1);
});
