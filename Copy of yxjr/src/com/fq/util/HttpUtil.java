package com.fq.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class HttpUtil {

	public static Logger log = LogManager.getLogger(HttpUtil.class);

	/**
	 * 
	 * @param url
	 * @param paramters
	 * @param charSet
	 * @param connectTimeOut
	 */
	public static String doPost(String url, Object paramters) {
		String responseStr = "";
		PostMethod postRequest = new UTF8PostMethod(url.trim());
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		int statusCode = 0;
		HttpClient httpclient = new HttpClient();
		try {
			httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
			Method[] ms = paramters.getClass().getMethods();
			for (int i = 0; i < ms.length; i++) {
				Method m = ms[i];
				String name = m.getName();
				if (name.startsWith("get")) {
					String param = name.substring(3, name.length());
					param = param.substring(0, 1).toLowerCase() + param.substring(1, param.length());
					if (param.equals("class")) {
						continue;
					}

					Object value = m.invoke(paramters);
					if (value != null) {
						NameValuePair nvp = new NameValuePair(param, value.toString());
						list.add(nvp);
					}
				}
			}
			NameValuePair[] nvps = new NameValuePair[list.size()];
			postRequest.setRequestBody(list.toArray(nvps));
			statusCode = httpclient.executeMethod(postRequest);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(postRequest.getResponseBodyAsStream(), "UTF-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = reader.readLine()) != null) {
				stringBuffer.append(str);
			}
			responseStr = stringBuffer.toString();
			log.info(responseStr);
			if (statusCode < HttpURLConnection.HTTP_OK || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
				System.err.println("失败返回码[" + statusCode + "]");
				responseStr = "{\"rspCode\":\"" + statusCode + "\"}";
			}
		} catch (Exception e) {
			responseStr = "{\"rspCode\":\"链接异常,请检查网络\"}";
			log.error(e.getMessage());
		}
		return responseStr;
	}

	private static class UTF8PostMethod extends PostMethod {
		public UTF8PostMethod(String url) {
			super(url);
		}

		@Override
		public String getRequestCharSet() {
			return "UTF-8";
		}
	}

	public static String doGet(String url, String charset) {
		String responseStr = "";
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		/* 2 生成 GetMethod 对象并设置参数 */
		GetMethod getMethod = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		// String response = "";
		/* 3 执行 HTTP GET 请求 */
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			/* 4 判断访问的状态码 */
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("请求出错: " + getMethod.getStatusLine());
			}
			/* 5 处理 HTTP 响应内容 */
			// HTTP响应头部信息，这里简单打印
			// Header[] headers = getMethod.getResponseHeaders();
			// for (Header h : headers)
			// System.out.println(h.getName() + "------------ " + h.getValue());
			// 读取 HTTP 响应内容，这里简单打印网页内容
			byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
			responseStr = new String(responseBody, charset);
			// System.out.println("----------response:" + response);
		} catch (HttpException e) {
			System.out.println("请检查输入的URL!");
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			System.out.println("发生网络异常!");
			e.printStackTrace();
		} finally {
			/* 6 .释放连接 */
			getMethod.releaseConnection();
		}
		return responseStr;
	}

	// 文件上传
	public static String doPostWithFile(String url, String fileName) {
		File file = new File(fileName);
		String res = "";
		if (!file.exists())
			return "";
		PostMethod filePost = new PostMethod(url);
		try {
			Part[] parts = { new FilePart(file.getName(), file) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);
			if (status != HttpStatus.SC_OK) {
				System.err.println("请求出错: " + filePost.getStatusLine());
			}
			byte[] responseBody = filePost.getResponseBody();// 读取为字节数组
			res = new String(responseBody, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static String doPostWithBody(String url, String body) throws Exception {
		String responseStr = "";
		PostMethod postMethod = new UTF8PostMethod(url.trim());
		RequestEntity se = new StringRequestEntity(body, "application/json", "UTF-8");
		postMethod.setRequestEntity(se);
		HttpClient httpClient = new HttpClient();
		httpClient.executeMethod(postMethod);
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(postMethod.getResponseBodyAsStream(), "UTF-8"));
		StringBuffer stringBuffer = new StringBuffer();
		String str = "";
		while ((str = reader.readLine()) != null) {
			stringBuffer.append(str);
		}
		responseStr = stringBuffer.toString();
		return responseStr;
	}

	
	 
}
