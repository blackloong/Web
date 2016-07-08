package com.fq.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import com.fq.form.moblieform;

import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStreamWriter;  
 import java.net.URL;  

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import net.sf.json.JSONObject; 

public class soursinterface {

	/**
	 * @param 接口请求
	 */
	public static String doPost (String url , Object paramters ,String charSet,int connectTimeOut){
		String responseStr = "";
		PostMethod postRequest = new PostMethod(url.trim());
		List<NameValuePair> list = new ArrayList<NameValuePair> ();
		int statusCode = 0;
		HttpClient httpclient = new HttpClient();
		try{
			 httpclient.getParams().setParameter(
		 	    		HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
			 httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectTimeOut);
			Method[] ms = paramters.getClass().getMethods();
			for ( int i = 0 ; i < ms.length ; i ++ ){
				Method m = ms[i];
				String name = m.getName();
				if(name.startsWith("get")){
					String param=name.substring(3,name.length());
					param=param.substring(0,1).toLowerCase()+param.substring(1,param.length());
					if(param.equals("class")){
						continue;
					}
				
					Object value =  m.invoke(paramters, null);
					if(value != null){
						NameValuePair nvp = new NameValuePair(param,value.toString()) ;
						list.add(nvp);
					}
				}
			}
			NameValuePair[] nvps = new NameValuePair[list.size()];
			postRequest.setRequestBody(list.toArray(nvps));
			statusCode = httpclient.executeMethod(postRequest);
			BufferedReader reader = new BufferedReader(new InputStreamReader(postRequest.getResponseBodyAsStream(),"UTF-8"));  
			StringBuffer stringBuffer = new StringBuffer();  
			String str = "";  
			while((str = reader.readLine())!=null){
			    stringBuffer.append(str);
			}
			responseStr = stringBuffer.toString();
			 if(statusCode<HttpURLConnection.HTTP_OK || statusCode>=HttpURLConnection.HTTP_MULT_CHOICE){
	                System.err.println("失败返回码[" + statusCode + "]");
	                responseStr="{\"rspCode\":\""+statusCode+"\"}";
	          }
		}catch (Exception e) {
			// TODO: handle exception
			responseStr="{\"rspCode\":\"链接异常,请检查网络\"}";
		}
		return responseStr;
	}
	
	
	/***
	 * 短信接口文档
	 * @return
	 * @throws Exception
	 */
  	public static void interfacescbdsoursyzmbyphone(String mobile,String contex) throws Exception {
		
			moblieform mf = new moblieform();
 			 mf.setName("13651758993");
			 mf.setPwd("CD0883D7BCB36A248A57661D5417");
		 	 mf.setSign("云送服务");
			 mf.setType("pt");
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
			String hehe = dateFormat.format(now);
			mf.setStime(hehe);
		 mf.setContent(contex);
	     mf.setMobile(mobile);
	     String url="http://web.cr6868.com/asmx/smsservice.aspx";
	     String responseStr = soursinterface.doPost(url, mf, "UTF-8",60000);
	    	
	 
	}
	
  	 /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String post(String strURL, String params) {  
        System.out.println(strURL);  
        System.out.println(params);  
        try {  
            URL url = new URL(strURL);// 创建连接  
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
                System.out.println(result);  
                return result;  
            }  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    }  
    
  	
    /***
     * java post json 
     * json 格式请求数据
     * @param ADD_URL
     * @param obj
     */
    
    
    public static String postJson(String ADD_URL,  JSONObject obj) {

        try {
            //创建连接
            URL url = new URL(ADD_URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
           /* JSONObject obj = new JSONObject();
            obj.element("app_name", "asdf");
            obj.element("app_ip", "10.21.243.234");
            obj.element("app_port", 8080);
            obj.element("app_type", "001");
            obj.element("app_area", "asd");*/
            out.writeBytes(obj.toString());
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            System.out.println(sb);
            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString(); 
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; 
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
    
    //内部类
    private static class UTF8PostMethod extends PostMethod {
		public UTF8PostMethod(String url) {
			super(url);
		}

		@Override
		public String getRequestCharSet() {
			return "UTF-8";
		}
	}

	public static void main(String[] args) {
//		moblieform mf=new moblieform();
//		mf.setUsername("chinavmy");
//		mf.setPwd("0311188");
//		Random rd=new Random();
//		int i=rd.nextInt(9999);
//		for(int j=0;j>=0;j++){
//			if(i<1000){
//			i=rd.nextInt(9999);
//		}else{
//			break;
//		}
//		}
//		mf.setContent("【美发】尊敬的用户，您验证码为："+i+",如有疑问，请咨询沪商网");
//		mf.setMobile("13127640379");
//		mf.setDetail("");
//		mf.setTiming("");
//		String url="http://www.uoleem.com.cn/api/uoleemApi";
//		String responseStr = soursinterface.doPost(url, mf, "UTF-8", 1000);
//		System.out.print(responseStr);
//		System.out.println(i);
	}
	

}
