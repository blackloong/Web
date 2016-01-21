<?PHP
$appid = 'wxde7a1c3c9b803601';
$appsecret = 'd4624c36b6795d1d99dcf0547af5443d';
require_once "wxsdk.php";
$wxsdk = new CWXSDK($appid, $appsecret);
$user_info = $wxsdk->GetUserInfo();
$wxsdk->SetShareData("OMG!明星都在我朋友圈里！", 
					"万万没想到，朋友圈里竟然有辣么多明星，猛料频频哦！", 
					"http://shihaijiang.com/boguswxfriends/img/tft.jpg", 
					"http://shihaijiang.com/boguswxfriends");
echo "<script src='js/main_script.js'></script>";
include_once('main.html');
//echo '<pre>';
//print_r($user_info);
//echo '</pre>';
?>
