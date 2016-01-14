	<script>
        var strTile = "良心老板喊你来领年终奖，全额8888元";
        var strDescription = "小往大来新手活动棒棒哒，理财平台稳健省心最重要。";
        var urlIcon = "http://tm.uncn88.com/Tpl/Promotion/headIcon.ico";
        var urlLink = "http://tm.uncn88.com/uncn/promotion";

        wx.config({
	        debug: false,
	        appId: '<?php echo $signPackage["appId"];?>',
	        timestamp: '<?php echo $signPackage["timestamp"];?>',
            nonceStr: '<?php echo $signPackage["nonceStr"];?>',
            signature: '<?php echo $signPackage["signature"];?>',
            jsApiList: 
            [
	            // 所有要调用的 API 都要加到这个列表中
	            'onMenuShareTimeline',
	            'onMenuShareAppMessage',
	            'onMenuShareQQ',
	            'onMenuShareWeibo'
            ]
        });

        wx.ready(function () {
            // 在这里调用 API
            //获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
            wx.onMenuShareTimeline({
                title : strTile, // 分享标题
                link : urlLink, // 分享链接
                imgUrl : urlIcon, // 分享图标
                // 用户确认分享后执行的回调函数
                success: function () { },
                // 用户取消分享后执行的回调函数
                cancel: function () { }
            });

            //获取“分享给朋友”按钮点击状态及自定义分享内容接口
            wx.onMenuShareAppMessage({
                title : strTile, // 分享标题
                desc : strDescription, // 分享描述
                link : urlLink, // 分享链接
                imgUrl : urlIcon, // 分享图标
                //type: '', // 分享类型,music、video或link，不填默认为link
                //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                // 用户确认分享后执行的回调函数
                success: function () { },
                // 用户取消分享后执行的回调函数
                cancel: function () { }
            });

            wx.onMenuShareQQ({
                title : strTile, // 分享标题
                desc : strDescription, // 分享描述
                link : urlLink, // 分享链接
                imgUrl : urlIcon, // 分享图标
                // 用户确认分享后执行的回调函数
                success: function () { },
                // 用户取消分享后执行的回调函数
                cancel: function () { }
            });

            wx.onMenuShareWeibo({
                title : strTile, // 分享标题
                desc : strDescription, // 分享描述
                link : urlLink, // 分享链接
                imgUrl : urlIcon, // 分享图标
                // 用户确认分享后执行的回调函数
                success: function () { },
                // 用户取消分享后执行的回调函数
                cancel: function () { }
            });

        });
    </script>

