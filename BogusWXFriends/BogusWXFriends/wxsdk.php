<?php
/**
 *
 * 调用微信SDK相关的接口实现功能
 */

require_once "tools.php";

class CWXSDK
{
    private $appID;
    private $appSecret;

    /**
     * 构造函数
     */
    public function __construct($appID, $appSecret) 
    {
		$this->appID = $appID;
        $this->appSecret = $appSecret;
		$this->setupShare();
	}

    /**
     * 根据appID和appSecret初始化微信SDK相关的分享接口
     */
    private function setupShare() 
    {
        $jsapiTicket = $this->getJsApiTicket();

		// 注意 URL 一定要动态获取，不能 hardcode.
        $protocol = (!empty($_SERVER['HTTPS']) && $_SERVER['HTTPS'] !== 'off' || $_SERVER['SERVER_PORT'] == 443) ? "https://" : "http://";
        $url = "$protocol$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";

        $timestamp = time();
        $nonceStr = $this->createNonceStr();

        // 这里参数的顺序要按照 key 值 ASCII 码升序排序
        $string = "jsapi_ticket=$jsapiTicket&noncestr=$nonceStr&timestamp=$timestamp&url=$url";

        $signature = sha1($string);

        $signPackage = array(
          "appId"     => $this->appID,
          "nonceStr"  => $nonceStr,
          "timestamp" => $timestamp,
          "url"       => $url,
          "signature" => $signature,
          "rawString" => $string,
        );
		return $signPackage; 
    }

    /**
     * 创建随机字符串，用于调用微信SDK接口
     * @return 随机生成的字符串
     */
    private function createNonceStr($length = 16) 
    {
        $chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        $str = "";
        for ($i = 0; $i < $length; $i++) 
        {
          $str .= substr($chars, mt_rand(0, strlen($chars) - 1), 1);
        }
        return $str;
    }

    /**
     * 调用微信SDK接口，获得相关Ticket
     * @return 微信SDK接口返回的Ticket
     */
    private function getJsApiTicket() 
    {
        // jsapi_ticket 应该全局存储与更新，以下代码以写入到文件中做示例
		$data = json_decode(CTools::GetPHPFileContent("jsapi_ticket.php"));
		if ($data->expire_time < time()) 
        {
            $accessToken = $this->getAccessToken();
            // 如果是企业号用以下 URL 获取 ticket
            //$url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=$accessToken";
            $url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=$accessToken";
            $res = json_decode(CTools::httpGet($url));
	        //CTools::SetPHPFileContent("jsapi_ticket_original.php", json_encode($res));
            $ticket = $res->ticket;
            if ($ticket) 
            {
                $data->expire_time = time() + 7000;
                $data->jsapi_ticket = $ticket;
                CTools::SetPHPFileContent("jsapi_ticket.php", json_encode($data));
            }
        } 
        else 
        {
            $ticket = $data->jsapi_ticket;
        }

        return $ticket;
    }

    /**
     * 调用微信SDK接口，获得相关Token
     * @return 微信SDK接口返回的Token
     */
    private function getAccessToken() 
    {
        // access_token 应该全局存储与更新，以下代码以写入到文件中做示例
        $data = json_decode(CTools::GetPHPFileContent("access_token.php"));
        if ($data->expire_time < time()) 
        {
            // 如果是企业号用以下URL获取access_token
            //$url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=$this->appID&corpsecret=$this->appSecret";
            $url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=$this->appID&secret=$this->appSecret";
            $res = json_decode(CTools::httpGet($url));
	        //CTools::SetPHPFileContent("jsapi_token_original.php", json_encode($res));
            $access_token = $res->access_token;
            if ($access_token) 
            {
                $data->expire_time = time() + 7000;
                $data->access_token = $access_token;
                CTools::SetPHPFileContent("access_token.php", json_encode($data));
            }
        } 
        else 
        {
            $access_token = $data->access_token;
        }
        return $access_token;
    }

    /**
     * 根据code获得用户的信息
     * @return 从微信SDK接口获得的用户信息
     */
	public function GetUserInfo() 
    {
		$code = $_GET['code'];
        $state = $_GET['state'];
        if (empty($code))
            $this->error('授权失败');
        $token_url = 'https://api.weixin.qq.com/sns/oauth2/access_token?appid='.$this->appID.'&secret='.$this->appSecret.'&code='.$code.'&grant_type=authorization_code';
        $token = json_decode(file_get_contents($token_url));
        if (isset($token->errcode))
        {
            echo '<h1>错误：</h1>'.$token->errcode;
            echo '<br />
                      <h2>错误信息：</h2>'.$token->errmsg;
            exit;
        }
        $access_token_url = 'https://api.weixin.qq.com/sns/oauth2/refresh_token?appid='.$this->appID.'&grant_type=refresh_token&refresh_token='.$token->refresh_token;
        //转成对象
        $access_token = json_decode(file_get_contents($access_token_url));
        if (isset($access_token->errcode))
        {
            echo '<h1>错误：</h1>'.$access_token->errcode;
            echo '<br />
                      <h2>错误信息：</h2>'.$access_token->errmsg;
            exit;
        }
        $user_info_url = 'https://api.weixin.qq.com/sns/userinfo?access_token='.$access_token->access_token.'&openid='.$access_token->openid.'&lang=zh_CN';
        //转成对象
        $user_info = json_decode(file_get_contents($user_info_url));
        if (isset($user_info->errcode))
        {
            echo '<h1>错误：</h1>'.$user_info->errcode;
            echo '<br />
                      <h2>错误信息：</h2>'.$user_info->errmsg;
            exit;
        }
        //打印用户信息
        //echo '<pre>';
        //print_r($user_info);
        //echo '</pre>';
        return $user_info;
    }
}

?>