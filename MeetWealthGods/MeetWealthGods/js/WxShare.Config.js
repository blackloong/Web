//var strTile = '$inTitle';
//var strDescription = '$inDesc';
//var urlIcon = '$inIcon';
//var urlLink = '$inLink';

wx.config({
    debug: false,
    appId: appid,
    timestamp: timestamp,
    nonceStr: noncestr,
    signature: signature,
    jsApiList:
    [
        // 所有要调用的 API 都要加到这个列表中
        'onMenuShareTimeline',
        'onMenuShareAppMessage',
        'onMenuShareQQ',
        'onMenuShareWeibo',
        'chooseWXPay'
    ]
});//end wx.config

wx.ready(function () {
    // 在这里调用 API
    //获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
    wx.onMenuShareTimeline({
        title: strTile, // 分享标题
        link: urlLink, // 分享链接
        imgUrl: urlIcon, // 分享图标
        // 用户确认分享后执行的回调函数
        success: function () { },
        // 用户取消分享后执行的回调函数
        cancel: function () { }
    });//end wx.onMenuShareTimeline

    //获取“分享给朋友”按钮点击状态及自定义分享内容接口
    wx.onMenuShareAppMessage({
        title: strTile, // 分享标题
        desc: strDescription, // 分享描述
        link: urlLink, // 分享链接
        imgUrl: urlIcon, // 分享图标
        //type: '', // 分享类型,music、video或link，不填默认为link
        //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        // 用户确认分享后执行的回调函数
        success: function () { },
        // 用户取消分享后执行的回调函数
        cancel: function () { }
    });//end wx.onMenuShareAppMessage

    wx.onMenuShareQQ({
        title: strTile, // 分享标题
        desc: strDescription, // 分享描述
        link: urlLink, // 分享链接
        imgUrl: urlIcon, // 分享图标
        // 用户确认分享后执行的回调函数
        success: function () { },
        // 用户取消分享后执行的回调函数
        cancel: function () { }
    });//end wx.onMenuShareQQ

    wx.onMenuShareWeibo({
        title: strTile, // 分享标题
        desc: strDescription, // 分享描述
        link: urlLink, // 分享链接
        imgUrl: urlIcon, // 分享图标
        // 用户确认分享后执行的回调函数
        success: function () { },
        // 用户取消分享后执行的回调函数
        cancel: function () { }
    });//end wx.onMenuShareWeibo
});//end wx.ready
