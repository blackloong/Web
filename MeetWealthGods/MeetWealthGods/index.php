<?PHP
require_once "lib/Config.php";
header('location:https://open.weixin.qq.com/connect/oauth2/authorize?appid='.WxBaseConfig::AppID.'&redirect_uri=http%3a%2f%2fshihaijiang.com%2fMeetWealthGods%2fmain.php&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect');    
?>