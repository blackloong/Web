<?php

/**
 *
 * 数据相关功能函数
 */
class CMySQL
{
    /** 连接到数据库的实例 */
    private $sql;

    /**
     * 构造函数,连接到指定的数据库
     * @param $DBAddress 数据库地址
     * @param $inDBName 数据库名称
     * @param $inUserName 登录数据库的用户名
     * @param $inPassword 登录数据库的密码
     */
    public function __construct($DBAddress, $inDBName, $inUserName, $inPassword) 
    {
        $this->sql = mysql_connect($DBAddress, $inUserName, $inPassword);
        if(!$this->sql)
        {
            die('Count not connect: ' . mysql_error());
        }
        mysql_select_db($inDBName, $this->sql);
	}

    /**
     * 析构函数,关闭数据连接
     */
    public function __destruct() 
    {
        mysql_close($this->sql);
	}

    /**
     * 执行一段查询语句
     * @param $inStr 要执行的查询语句
     * @param 查询结果
     */
    public function Query($inStr)
    {
        $result = mysql_query($inStr, $this->sql);
        if(!$result)
        {
			print_r($inStr);
            die('Error' . mysql_error());
        }
        return $result;
    }

    /**
     * 通过查询结果取得一行作为对象返回
     * @param $inQueryResult 通过Query查询的结果
     * @return 结果对象
     */
    public function FetchObject($inQueryResult)
    {
        return mysql_fetch_object($inQueryResult);
    }
}

?>