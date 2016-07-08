package com.fq.util;

import java.util.Random;

import com.jianzhou.sdk.BusinessService;

public class CallJZSms {

	

	public static void main(final String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		Random rd = new Random();
        int i = rd.nextInt(9999);
        for (int j = 0; j >= 0; j++) {
		    if (i >= 1000)
			break;
		     i = rd.nextInt(9999);
	   }
        
        String Content="尊敬的用户，您验证码为"+ i +",如有疑问，请咨询翼勋金融，服务热线400-920-1313!【翼勋金融】";
 	 
		BusinessService bs = new BusinessService();
		bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");

		/***
		类别	参数名称	类型	说明
		输入	account	string	企业的登录账号
		输入	password	string	账号对应的密码
		输入	destmobile	string	目标手机号，
		1、	多个手机号码用;分割
		2、	建议一次最多提交3000左右的号码 
         */
		System.out.println("333:" + bs.sendBatchMessage("sdk_yixun", "20150724", "13525182581", Content));

	}

}
