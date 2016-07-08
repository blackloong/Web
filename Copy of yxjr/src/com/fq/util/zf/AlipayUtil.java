package com.fq.util.zf;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
public class AlipayUtil {

	static String zshUrl = "http://223.6.254.184:4901/JsZshService.uig";

	public static String queryOrder(String orderNo) {
		String interface_key = "791792BE55585F169961AFB9F1300F354AB66310DA079E42E3261158734E4294219B6A097BBCA1E577CB988896258142ADB2602AB17D4A7D";
		Map<String, String> data = new HashMap<String, String>();
		data.put("instId", "13494");
		data.put("type", "SonicQueryReq");
		data.put("msgId", "");
		data.put("msgTime",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		data.put("version", "");
		data.put("key", interface_key);

		// ----------------------------封装开始
		// data.put("service", "alipay.acquire.precreate");
		data.put("partner", "2088711464028412");
		data.put("partner_key", "188j5xzsc06652bezcykn8u8obwmcmre");
		data.put("_input_charset", "GBK");
		data.put("out_trade_no", orderNo);
		// data.put("seller_email", "zhaishenghuo@aliyun.com");
		// data.put("subject", "宅生活统一支付订单");
		// data.put("product_code", "BARCODE_PAY_OFFLINE");
		// data.put("total_fee", "0.02");
		// data.put("price", "0.02");
		// data.put("quantity", "1");
		// data.put("goods_detail", "梵高|1|商品|0.01|111|97013|60254|6008");
		// ----------------------------封装结束
		// ----------------------------排序开始
		String dataSend = gaoYangGameSendBefore(data).toString();
		System.out.println("串排序后:" + dataSend);
		// ----------------------------排序结束
		// ----------------------------加密开始
		// String mysign = new MD5().encode(dataSend + key, "GBK");

		String one = encode(encode1(dataSend, "UTF-8"), "UTF-8");
		System.out.println("第一次加密结果为：" + one);
		String second = encode(interface_key + one + interface_key, "UTF-8");
		System.out.println("第二次加密结果为:" + second);
		// String mysign = new MD5().encode(
		// interface_key
		// + new MD5().encode(
		// StringEncoding.encode(dataSend, "UTF-8"),
		// "UTF-8") + interface_key, "UTF-8");
		// System.out.println("第二次加密结果为:" + mysign);
		data.remove("key");
		data.put("sign_type", "MD5");
		data.put("sign", second);
		// ----------------------------加密结束

		// ----------------------------重新排序并生成&=字符串开始
		dataSend = gaoYangGameSendBefore(data).toString();
		// ----------------------------重新排序并生成&=字符串结束

		// ----------------------------发送到支付宝开始
		HttpClient httpclient = new HttpClient();
		setChacterIsGbk(httpclient);
		System.out.println("准备发送的数据为:" + zshUrl + "?" + dataSend);
		String response = post(zshUrl, dataSend,
				"application/x-www-form-urlencoded;text/html;charset=UTF-8",
				httpclient);
		return response;
	}

	public static String orderPay(String total, String detail, String title) {
		String interface_key = "791792BE55585F169961AFB9F1300F" +
				"354AB66310DA079E42E3261158734E4294219B6A097BBCA1E577CB988896258142ADB2602AB17D4A7D";
		Map<String, String> data = new HashMap<String, String>();
		data.put("instId", "13494");
		data.put("type", "SonicPreReq");
		data.put("msgId", "");
		data.put("msgTime",
				new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		data.put("version", "");
		data.put("key", interface_key);

		// ----------------------------封装开始
		data.put("service", "alipay.acquire.precreate");
		data.put("partner", "2088711464028412");
		data.put("partner_key", "188j5xzsc06652bezcykn8u8obwmcmre");
		data.put("_input_charset", "GBK");
		data.put("out_trade_no",
				new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		data.put("seller_email", "qt_pay@aliyun.com");
		data.put("subject", title);
		data.put("product_code", "BARCODE_PAY_OFFLINE");
		data.put("total_fee", total);
		data.put("price", total);
		data.put("quantity", "1");
		data.put("goods_detail", detail);
		// ----------------------------封装结束
		// ----------------------------排序开始
		String dataSend = gaoYangGameSendBefore(data).toString();
		System.out.println("串排序后:" + dataSend);
		// ----------------------------排序结束
		// ----------------------------加密开始
		// String mysign = new MD5().encode(dataSend + key, "GBK");

		String one = encode(encode1(dataSend, "UTF-8"), "UTF-8");
		System.out.println("第一次加密结果为：" + one);
		String second = encode(interface_key + one + interface_key, "UTF-8");
		System.out.println("第二次加密结果为:" + second);
		// String mysign = new MD5().encode(
		// interface_key
		// + new MD5().encode(
		// StringEncoding.encode(dataSend, "UTF-8"),
		// "UTF-8") + interface_key, "UTF-8");
		// System.out.println("第二次加密结果为:" + mysign);
		data.remove("key");
		data.put("sign_type", "MD5");
		data.put("sign", second);
		// ----------------------------加密结束

		// ----------------------------重新排序并生成&=字符串开始
		dataSend = gaoYangGameSendBefore(data).toString();
		// ----------------------------重新排序并生成&=字符串结束

		// ----------------------------发送到支付宝开始
		// UigXmlPost post = new UigXmlPost();
		HttpClient httpclient = new HttpClient();
		setChacterIsGbk(httpclient);
		System.out.println("准备发送的数据为:" + zshUrl + "?" + dataSend);
		String response = post(zshUrl, dataSend,
				"application/x-www-form-urlencoded;text/html;charset=UTF-8",
				httpclient);
		return response;

	}

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

	public static String encode1(String str, String enc) {

		try {
			return java.net.URLEncoder.encode(str, enc);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static String encode(String myinfo, String encode) {
		byte[] digesta = null;
		try {
			java.security.MessageDigest alga = java.security.MessageDigest
					.getInstance("MD5");
			alga.update(myinfo.getBytes(encode));
			digesta = alga.digest();
			System.out.println("MD5完成加密" + Arrays.toString(digesta));
		} catch (Exception ex) {
		}
		return byte2hex(digesta);
	}

	public static String byte2hex(byte[] b) { // 二行制转字符 ?
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			if (n < b.length - 1) {
				hs = hs;
			}
		}
		return hs;
	}

	public static HttpClient setChacterIsGbk(HttpClient hc) {

		HttpClientParams hp = new HttpClientParams();
		hp.setConnectionManagerTimeout(15000);
		hp.setContentCharset("GBK");
		hc.setParams(hp);
		hc.setConnectionTimeout(15000);
		hc.setTimeout(15000);
		return hc;
	}

	public static String post(String url, String str, String charset,
			HttpClient httpclient) {
		String aRequestContent = str;

		if ((aRequestContent != null) && (aRequestContent.length() > 0)) {
			PostMethod post = new PostMethod(url);
			post.setRequestBody(aRequestContent);
			// post.setRequestHeader("Content-type", "text/xml; charset=UTF-8");
			// post.setRequestHeader("Content-type",
			// "application/x-www-form-urlencoded");
			post.setRequestHeader("Content-type", charset);
			try {

				System.out.println("宅生活发送的报文为：" + aRequestContent);
				int result = httpclient.executeMethod(post);
				if (200 == result) {
					System.out.println("支付宝返回的报文为："
							+ post.getResponseBodyAsString());
					return post.getResponseBodyAsString();
				}
				return null;
			} catch (Exception localException) {

			} finally {
				if (post != null)
					post.releaseConnection();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// String xmlString = orderPay("0.01",
		// "幸运街商品|1|商品|0.01|111|97013|60254|6008");
		
		 String queryXmlString = queryOrder("20141117220932266");
		 System.out.println(queryXmlString);
	}
}
