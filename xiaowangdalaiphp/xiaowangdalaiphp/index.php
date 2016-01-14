<?php require_once "jssdk.php";
	$jssdk = new JSSDK("wx580cbdc1e130c188", "43d6e288cef7049055b51b69a668aa8d"); //服务号
	$signPackage = $jssdk->GetSignPackage();
?>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
    <title>良心老板喊你来领年终奖，全额8888元</title>
    <link href="Style.css" rel="stylesheet" type="text/css"/>
    <link rel="icon" href="headIcon.ico" mce_href="headicon.ico" type="image/x-icon">
</head>
<body class="body">
    <div class="pageBackground">
        <div class="shareBackground" onclick="btnCloseShare()"></div>
        <div onclick="btnTopHover();">
            <div class="btnTopHover">&nbsp;</div>
        </div>
        <marquee class="marqueeCompanyBrief">本活动由小往大来理财平台赞助，小往大来9年老品牌资产管理公司出品，央企强力支撑，多家第三方权威机构认证。稳健理财就选小往大来，收益率最高13%。</marquee>
        <div class="textReceiveCount">已有<?php echo $signPackage["VisitCount"];?>位同事领取年终奖</div>
        <div onclick="btnRegister();">
            <div class="btnRegister">&nbsp;</div>
        </div>
        <div onclick="btnNotifyNext();">
            <div class="btnNotifyNext">&nbsp;</div>
        </div>
        <div class="slide-mask">
            <ul class="slideup">
                <li class="slide">爱是条不归路 已领取 8888元 全年发财</li>
                <li class="slide">陈丽兰 已领取 6888元 一路惊喜</li>
                <li class="slide">Amanda呀 已领取 4888元 名利双收</li>
                <li class="slide">诸洪峰 已领取 8888元 全年发财</li>
                <li class="slide">忧伤的笑╮ 已领取 6888元 一路惊喜</li>
                <li class="slide">始终是爱° 已领取 6888元 一路惊喜</li>
                <li class="slide">那些明媚的阳光丶 已领取 4888元 名利双收</li>
                <li class="slide">白琴 已领取 6888元 一路惊喜</li>
                <li class="slide">江南生活 已领取 8888元 全年发财</li>
                <li class="slide">Jojon 已领取 1888元 满满的小确幸</li>
                <li class="slide">李丽娜 已领取 4888元 名利双收</li>
                <li class="slide">马建南 已领取 3888元 美满一年</li>
                <li class="slide">潘媛媛 已领取 3888元 美满一年</li>
                <li class="slide">Rock=。=张 已领取 6888元 一路惊喜</li>
            </ul>
        </div>
    </div>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <?PHP require_once "wxsdk.php"; ?>
    <script src="jquery-1.8.3.min.js"></script>
    <script src="index.js"></script>
</body>
</html>
