<?PHP
$appid = 'wxde7a1c3c9b803601';
$appsecret = 'd4624c36b6795d1d99dcf0547af5443d';
require_once "wxsdk.php";
require_once "mysql.php";
$mySQL = new CMySQL('bdm207399638.my3w.com', 'bdm207399638_db', 'bdm207399638', 'woshitooth10');
$openid = $_GET['openid'];
$wxsdk = new CWXSDK($appid, $appsecret);
if(isset($openid))
{
	$result = $mySQL->Query("select * from BogusWXFriendsUser where openid = '$openid'");
    $user_info = $mySQL->FetchObject($result);
}
else
{
	$user_info = $wxsdk->GetUserInfo();
	$result = $mySQL->Query("select * from BogusWXFriendsUser where openid = '$user_info->openid'");
	if(mysql_num_rows($result))
	{
		$mySQL->Query("update BogusWXFriendsUser set nickname = '$user_info->nickname', headimgurl = '$user_info->headimgurl' where openid = '$user_info->openid'");
	}
	else
	{
		$curTime = date('Y-m-d H:i:s');
		$mySQL->Query("insert into BogusWXFriendsUser (openid, nickname, headimgurl, sex, addtime) value ('$user_info->openid', '$user_info->nickname', '$user_info->headimgurl', '$user_info->sex', '$curTime')");
	}
}
//echo '<pre>';
//print_r(time());
//print_r(435345);
//echo '</pre>';
//获得微信js的配置数据
$wxShareConfig = $wxsdk->GetWXShareJSConfigData();
//输出网页内容
include_once('main.html');
//定义js使用的变量
echo "<script type='text/javascript'>
			  var appid = '$appid';
	          var nickname = '$user_info->nickname';
			  var headimgurl = '$user_info->headimgurl';
			  var strTile = 'OMG!明星都在我朋友圈里！';
			  var strDescription = '万万没想到，朋友圈里竟然有辣么多明星，猛料频频哦！';
			  var urlIcon = 'http://shihaijiang.com/boguswxfriends/img/tft.jpg';
			  var urlLink = 'http://shihaijiang.com/boguswxfriends/main.php?openid='+'$user_info->openid';
			  var timestamp = '$wxShareConfig->timestamp';
			  var nonceStr = '$wxShareConfig->noncestr';
			  var signature = '$wxShareConfig->signature';
	  </script>";
echo "<script src='http://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>";
echo "<script src='js/wxshareconfig.js'></script>";
echo "<script src='js/jquery.min.js'></script>";
echo "<script src='js/main_script.js'></script>";
?>
