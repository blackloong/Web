    
wx.ready(function () 
{
    // 在这里调用 API
    //获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
    wx.onMenuShareTimeline({
        title: '<?php echo $news['Title'];?>', // 分享标题
        link: '<?php echo $news['Url'];?>', // 分享链接
        imgUrl: '<?php echo $news['PicUrl'];?>', // 分享图标
        // 用户确认分享后执行的回调函数
        success: function () {        },
        // 用户取消分享后执行的回调函数
        cancel: function () {        }
    });
    
    //获取“分享给朋友”按钮点击状态及自定义分享内容接口
    wx.onMenuShareAppMessage({
        title: '<?php echo $news['Title'];?>', // 分享标题
        desc: '<?php echo $news['Description'];?>', // 分享描述
        link: '<?php echo $news['Url'];?>', // 分享链接
        imgUrl: '<?php echo $news['PicUrl'];?>', // 分享图标
        //type: '', // 分享类型,music、video或link，不填默认为link
        //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
        // 用户确认分享后执行的回调函数
        success: function () {        },
        // 用户取消分享后执行的回调函数
        cancel: function () {        }
    });

    wx.onMenuShareQQ({
        title: '<?php echo $news['Title'];?>', // 分享标题
        desc: '<?php echo $news['Description'];?>', // 分享描述
        link: '<?php echo $news['Url'];?>', // 分享链接
        imgUrl: '<?php echo $news['PicUrl'];?>', // 分享图标
        // 用户确认分享后执行的回调函数
        success: function () {        },
        // 用户取消分享后执行的回调函数
        cancel: function () {        }
    });

    wx.onMenuShareWeibo({
        title: '<?php echo $news['Title'];?>', // 分享标题
        desc: '<?php echo $news['Description'];?>', // 分享描述
        link: '<?php echo $news['Url'];?>', // 分享链接
        imgUrl: '<?php echo $news['PicUrl'];?>', // 分享图标
        // 用户确认分享后执行的回调函数
        success: function () {        },
        // 用户取消分享后执行的回调函数
        cancel: function () {        }        
    });

});
