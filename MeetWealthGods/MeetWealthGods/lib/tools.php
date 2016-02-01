<?php

/**
 *
 * 相关工具函数
 */

class CTools
{
    /**
     * 获得本地PHP的文件文件内容，内容需要通过json_decode解析
     * 由于保存的时先写入了文件头"<?php exit();?>"，所以需要跳过15个字符
     * @param filename PHP文件名
     * @return 需要通过json_decode解析的文件内容
     */
    public static function GetPHPFileContent($filename)
    {
		return trim(substr(file_get_contents($filename), 15));
    }

    /**
     * 将一段内容保存到本地的PHP文件，内容需要通过json_encode编码
     * 先保存15个字符的"<?php exit();?>"作为文件头
     * @param filename PHP文件名
     * @param content 要保存的文件内容（已通过json_encode编码后的内容）
     */
    public static function SetPHPFileContent($filename, $content)
    {
        $fp = fopen($filename, "w");
        fwrite($fp, "<?php exit();?>" . $content);
        fclose($fp);
    }
    /**
     * 获得页面被访问的次数，通过保存PHP文件实现
     * @return 已访问的次数
     */
    public static function GetVisitCount()
    {
        $data = json_decode($this->GetPHPFileContent("VisitCount.php"));
        $data->count += 1;
	    $this->SetPHPFileContent("VisitCount.php", json_encode($data));
        return $data->count;
    }

    /**
     * 通过curl调用微信SDK接口
     * @param url 微信SDK接口的地址
     * @return 微信SDK接口的返回值
     */
    public static function httpGet($url)
    {
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_TIMEOUT, 500);
        // 为保证第三方服务器与微信服务器之间数据传输的安全性，所有微信接口采用https方式调用，必须使用下面2行代码打开ssl安全校验。
        // 如果在部署过程中代码在此处验证失败，请到 http://curl.haxx.se/ca/cacert.pem 下载新的证书判别文件。
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, true);
        curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, true);
        curl_setopt($curl, CURLOPT_URL, $url);

        $res = curl_exec($curl);
        curl_close($curl);

        return $res;
    }
	
	/**
	 * 打印输出数组信息(所有的键值对)
	 * @param $inArray 要打印的数组
	 */
	public static function PrintArrayInfo($inArray)
	{
		foreach($inArray as $key=>$value)
		{
			echo "<font color='#00ff55;'>$key</font> : $value <br/>";
		}
	}

}

?>