package com.fq.util.zf;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypay.merchantutil.Md5Encrypt;
import com.mypay.merchantutil.UrlHelper;
import com.mypay.merchantutil.timestamp.TimestampUtils;

import timestamp.AskForTimestampDemo;

public class DirectUtil1{
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		AskForTimestampDemo demo = new AskForTimestampDemo();

		String anti_phishing_key =null;
		 try {
	            anti_phishing_key = demo.runDemo();
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		          	    
			// 签名方式
			/*
			 * 将请求参数名排序，拼接成字符串，如：a=v1&b=v2 然后对该字符串签名，DigitalSigner.sign() : String
			 * 得到的即为签名.
			 */
	        Map<String,String[]> reqMap=new HashMap<String ,String[]>();
	        reqMap.putAll(req.getParameterMap());
	       
	        reqMap.put("anti_phishing_key",new String[]{anti_phishing_key});

		
		// 签名方式
		/*
		 * 将请求参数名排序，拼接成字符串，如：a=v1&b=v2 然后对该字符串签名，DigitalSigner.sign() : String
		 * 得到的即为签名
		 */
		String paramString = UrlHelper.sortParamers(reqMap);
        String key = "9UCKYZ6Q804CO5O43TGHLMDO4YTU10hggixe"; // 商户加密字符串 
        String paramStr = UrlHelper.sortParamers(reqMap);
        
        String plaintext = TimestampUtils.mergePlainTextWithMerKey(paramStr, key);
        
        // 加密(MD5加签)，默认已取UTF-8字符集，如果需要更改，可调用Md5Encrypt.setCharset(inputCharset)
        String sign = Md5Encrypt.encrypt(plaintext); 
        
        String encodedParamString = UrlHelper.encode(reqMap, req.getParameter("input_charset"));
        
        String gateway = "https://www.ebatong.com/direct/gateway.htm"; // ebatong商户网关
        String url = gateway + "?" + encodedParamString + "&sign=" + URLEncoder.encode(sign, req.getParameter("input_charset"));
        System.out.println(gateway + "?" + paramString + "&sign=" + sign);
        System.out.println(url);
        resp.sendRedirect(url);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		doPost(req, resp);
	}
	
	/**
	 * 封装参数
	 * @param map
	 * @return
	 */
	public static Map gaoYangGameSendBefore(Map map) {

		Map treeMap = new TreeMap() {

			@Override
			public String toString() {

				Iterator iterator = this.entrySet().iterator();
				StringBuffer sb = new StringBuffer();
				while (iterator.hasNext()) {
					Map.Entry entry = (Map.Entry) iterator.next();
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					sb.append(key + '=' + value + '&');
				}

				return sb.substring(0, sb.length() - 1).toString();
			}

			public int compare(Object o1, Object o2) {

				String s1 = (String) o1;
				String s2 = (String) o2;
				return s1.compareTo(s2);

			}
		};

		treeMap.putAll(map);
		return treeMap;

	}
}
