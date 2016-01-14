/**
*	JSSDK 模块
*
*	msxiehui@163.com
*	2015-11-29
*/


(function () { // 创建最外层匿名函数.
    var JSSDK = window.JSSDK = function (selector) {
        return new JSSDK.fn.init(selector);
    };
    JSSDK.fn = JSSDK.prototype = {
        init: function (selector) {
            if (selector) this.selector = selector;
            return this;
        },
    };

    JSSDK.init = _init;
    JSSDK.reset = _reset;

})();
var titles, links, imgUrls, doc;
function _init(_title, _doc, _link, _img, debug) {
    titles = _title;
    links = _link;
    imgUrls = _img;
    doc = _doc;
    var debug = arguments[4] ? arguments[4] : false;
    //alert(debug);
    try {
        jssdk(debug);
    } catch (e) {
    }
}

function _reset(_title, _doc, _link, _img) {
    titles = _title;
    links = _link;
    imgUrls = _img;
    doc = _doc;
    share();
}


var config;
function get_config(url) {
    url = "../global/jssdk.php?get=js&url=" + url;
    $.ajax({
        type: "GET",
        async: false,
        timeout: 10000,
        url: url,
        dataType: "json",
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert("系统错误："+errorThrown);
        },
        success: function (json) {
            config = json;
        }
    });
}

function jssdk(debug) {
    var url = location.href.split('#')[0];
    url = encodeURIComponent(url);
    get_config(url);
    wx.config({
        debug: debug,
        appId: config.appId,
        timestamp: config.timestamp,
        nonceStr: config.nonceStr,
        signature: config.signature,
        jsApiList: [
          // 所有要调用的 API 都要加到这个列表中
          'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ'
        ]
    });
    wx.ready(function () {
        // 在这里调用 API
        share();
    });

}
function share() {
    wx.onMenuShareTimeline({
        title: doc,
        link: links,
        desc: doc,
        imgUrl: imgUrls,
        success: function () {
            //alert('分享成功');
        },
        cancel: function () {
            //  alert('取消分享');
        }
    });
    wx.onMenuShareAppMessage({
        title: titles,
        link: links,
        desc: doc,
        imgUrl: imgUrls,
        success: function () {
            //alert('分享成功');
        },
        cancel: function () {
            //alert('取消分享');
        }
    });
    wx.onMenuShareQQ({
        title: titles,
        link: links,
        desc: doc,
        imgUrl: imgUrls,
        success: function () {
            //alert('分享成功');
        },
        cancel: function () {
            //alert('取消分享');
        }
    });
}
