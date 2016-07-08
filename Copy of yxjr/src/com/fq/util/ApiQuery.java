package com.fq.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONObject;

import com.fq.util.ll.Md5Algorithm; 

public class ApiQuery {
    private static String       TRADER_MD5_KEY = "1880EE4D8D0E12E2A43C3C1363D9ED7D";
	
    public static String genSign(JSONObject reqObj)
    {
        String sign = reqObj.getString("sign");
        String sign_type=reqObj.getString("sign_type");
        // // 生成待签名串
        String sign_src = genSignData(reqObj);
        System.out.println("商户[" + reqObj.getString("oid_partner") + "]待签名原串"
                + sign_src);
        System.out.println("商户[" + reqObj.getString("oid_partner") + "]签名串"
                + sign);

        if("MD5".equals(sign_type)){
            sign_src += "&key=" + TRADER_MD5_KEY;
            return signMD5(sign_src);
        }
       /* if("RSA".equals(sign_type)){
            return getSignRSA(sign_src);
        }*/
        return null;
    }

    public static String signMD5(String signSrc)
    {

        try
        {
            return Md5Algorithm.getInstance().md5Digest(
                    signSrc.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    


    /**
     * 生成待签名串
     * 
     * @param paramMap
     * @return
     */
    public static String genSignData(JSONObject jsonObject)
    {
        StringBuffer content = new StringBuffer();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = keys.get(i);
            // sign 和ip_client 不参与签名
            if ("sign".equals(key))
            {
                continue;
            }
            String value = jsonObject.getString(key);
            // 空串不参与签名
            if (null==value)
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }


}
