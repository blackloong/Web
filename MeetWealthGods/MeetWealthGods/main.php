<?PHP
require_once "lib/WxShare.Api.php";
require_once "lib/MySQL.php";
require_once "lib/Config.php";
require_once "lib/Log.php";
require_once "lib/WxPay.Api.php";
//初始化数据库
$gMySQL = new CMySQL(CSQLConfig::ServerAddress, CSQLConfig::DataBaseName, CSQLConfig::UserName, CSQLConfig::Password);
//初始化微信分享的接口实例
$gWxShareApi = new CWxShareApi(WxBaseConfig::AppID, WxBaseConfig::AppSecret);
//获得客户端传入的openid，如果存在则表示查看该openid的朋友圈，否则创建自己的朋友圈
$gOpenid = $_GET['openid'];
if(isset($gOpenid))
{
	//从数据库空读取已存在用户信息
	$result = $gMySQL->Query("select * from BogusWXFriendsUser where openid = '$gOpenid'");
	$user_info = $gMySQL->FetchObject($result);
}
else
{
	//请求当前用户的信息
	$user_info = $gWxShareApi->GetUserInfo();
	$result = $gMySQL->Query("select * from BogusWXFriendsUser where openid = '$user_info->openid'");
	if(mysql_num_rows($result))
	{
		$gMySQL->Query("update BogusWXFriendsUser set nickname = '$user_info->nickname', headimgurl = '$user_info->headimgurl' where openid = '$user_info->openid'");
	}
	else
	{
		$curTime = date('Y-m-d H:i:s');
		$gMySQL->Query("insert into BogusWXFriendsUser (openid, nickname, headimgurl, sex, addtime) value ('$user_info->openid', '$user_info->nickname', '$user_info->headimgurl', '$user_info->sex', '$curTime')");
	}
}
//获得微信分享的js配置数据
$gWxShareConfig = $gWxShareApi->GetWXShareJSConfigData();
//输出网页内容
include_once('main.html');

//初始化日志
$gLogHandler = new CLogFileHandler("../logs".date('Y-m-d').'.log');
$gLog = Log::Init($gLogHandler, 15);
//初始化微信支付js的接口
$gWxPayJSApi = new JsApiPay();
//初始化统一下单数据
$gOrder = new WxPayUnifiedOrder();
$gOrder->SetBody("TestBody");
$gOrder->SetAttach("TestAttach");
$gOrder->SetOut_trade_no(WxPayConfig::MCHID.date("YmdHis"));
$gOrder->SetTotal_fee("1");
$gOrder->SetTime_start(date("YmdHis"));
$gOrder->SetTime_expire(date("YmdHis", time() + 600));
$gOrder->SetGoods_tag("testGoodsTag");
$gOrder->SetNotify_url("http://shihaijiang.com/boguswxfriends/WxPayNotify.php");
$gOrder->SetTrade_type("JSAPI");
$gOrder->SetOpenid($user_info->openid);
//创建统一订单
$gUnifiedOrder = WxPayApi::unifiedOrder($gOrder);
//根据统一订单生成JS订单数据
$gWxPayJsApiParameters = $gWxPayJSApi->GetJsApiParameters($gUnifiedOrder);
//生成js支付函数
echo "<script type='text/javascript'>
	        function WxPayJsApiCall()
			{
			    WeixinJSBridge.invoke('getBrandWCPayRequest',
									<?PHP echo $gWxPayJsApiParameters; ?>,
									function(res)
									{
										WeixinJSBridge.log(res.err_msg);
										alert(res.err_code + res.err_desc + res.err_msg);
									});
			}
			function callpay()
			{
				if(typeof WeixinJSBridge == 'undefined')
				{
					if(document.addEventListener)
					{
						document.addEventListener('WeixinJSBridgeReady', WxPayJsApiCall, false);
					}
					else if(document.attachEvent)
					{
						document.attachEvent('WeixinJSBridgeReady', WxPayJsApiCall);
						document.attachEvent('onWeixinJSBridgeReady', WxPayJsApiCall);
					}
				}
				else
				{
					WxPayJsApiCall();
				}
			}
	  </script>";
//定义js使用的变量
echo "<script type='text/javascript'>
			  var appid = 'WxBaseConfig::AppID';
	          var nickname = '$user_info->nickname';
			  var headimgurl = '$user_info->headimgurl';
			  var strTile = 'OMG!明星都在我朋友圈里！';
			  var strDescription = '万万没想到，朋友圈里竟然有辣么多明星，猛料频频哦！';
			  var urlIcon = 'http://shihaijiang.com/boguswxfriends/img/tft.jpg';
			  var urlLink = 'http://shihaijiang.com/boguswxfriends/main.php?openid='+'$user_info->openid';
			  var timestamp = '$gWxShareConfig->timestamp';
			  var nonceStr = '$gWxShareConfig->noncestr';
			  var signature = '$gWxShareConfig->signature';
	  </script>";
echo "<script src='http://res.wx.qq.com/open/js/jweixin-1.0.0.js'></script>";
echo "<script src='js/WxShare.Config.js'></script>";
echo "<script src='js/jquery.min.js'></script>";
echo "<script src='js/main_script.js'></script>";
//echo '<pre>';
//print_r(time());
//print_r(435345);
//echo '</pre>';

?>

