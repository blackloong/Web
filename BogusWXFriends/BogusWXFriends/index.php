<?PHP
$appid = 'wx580cbdc1e130c188';
$redirectURL = '127.0.0.1/main.php';
//$jssdk = new JSSDK("wx580cbdc1e130c188", "43d6e288cef7049055b51b69a668aa8d"); //服务号

//header('location:https://open.weixin.qq.com/connect/oauth2/authorize?appid='.$appid.'&redirect_uri='$redirectURL'&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect');
//header('location:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx580cbdc1e130c188&redirect_uri=http%3A%2F%2Fshihaijiang.com%2Fboguswxfriends%2Fmain.php&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect');
//header('location:https://www.baidu.com');
header('location:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx580cbdc1e130c188&redirect_uri=http://shihaijiang.com/boguswxfriends/main.php&response_type=code&scope=SCOPE&state=STATE#wechat_redirect');
//http://www.cnblogs.com/lykbk/p/fsdfsdfs34543534.html
//http://shihaijiang.com/BogusWXFriends/
//http://xiaohuang.cc/post/437.html
//http://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html
?>