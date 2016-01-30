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
    $mySQL->Query("insert into BogusWXFriendsUser (openid, nickname, headimgurl, sex, addtime)
                               value ('$user_info->openid', '$user_info->nickname', '$user_info->headimgurl', '$user_info->sex', 'time()')");
}
$wxsdk->SetShareData("OMG!明星都在我朋友圈里！", 
	"万万没想到，朋友圈里竟然有辣么多明星，猛料频频哦！", 
	"http://shihaijiang.com/boguswxfriends/img/tft.jpg", 
	"http://shihaijiang.com/boguswxfriends/main.php?openid=".$user_info->openid);
//定义js使用的变量
include_once('main.html');
echo "<script type='text/javascript'>
	          var nickname = '$user_info->nickname';
			  var headimgurl = '$user_info->headimgurl';
	  </script>";
echo "<script src='js/jquery.min.js'></script>";
echo "<script src='js/main_script.js'></script>";
?>
