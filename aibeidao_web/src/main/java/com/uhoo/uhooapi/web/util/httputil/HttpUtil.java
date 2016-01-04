package com.uhoo.uhooapi.web.util.httputil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


@SuppressWarnings("deprecation")
public class HttpUtil {
	
	//全局的accessToken
	public static String accessToken = null;
	public static String jsapiAccessToken = null;
	//accessToken的有效时间
	public static long expiresTime = System.currentTimeMillis();
	public static long jsapiExpiresTime = System.currentTimeMillis();

	private static HttpClient client;
	
	@SuppressWarnings({ "finally"})
	public static String getJsonString(String requestUrl){
			if(client==null){
				client = new DefaultHttpClient();
			}
			client = new DefaultHttpClient();
		    HttpGet get = new HttpGet(requestUrl);
		    String result = null;
		    try{
		        HttpResponse res = client.execute(get);
		        String responseContent = null; // 响应内容
		        HttpEntity entity = res.getEntity();
		        responseContent = EntityUtils.toString(entity,"utf-8");
		        result=responseContent;
		    }catch (Exception e){
		        e.printStackTrace();
		    }finally{ //关闭连接 ,释放资源
		        client.getConnectionManager().shutdown();
		        return result;
		    }
	 }
	
	public static String sendPostData(String pathUrl, Map<String,String> ps,Map<String,String> hps) {
		String result = "{}";
		try{
        	if(client==null){
				client = new DefaultHttpClient();
			}
			client = new DefaultHttpClient();
        	 // HttpPost连接对象  
            HttpPost httpost = new HttpPost(pathUrl);
//            httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);   
//			httpConn.setRequestProperty("Content-Type", "application/octet-stream");   
//			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接   
//			httpConn.setRequestProperty("Charset", "UTF-8");   
            for(Map.Entry<String, String> entry: hps.entrySet()){
				 httpost.setHeader(entry.getKey(), entry.getValue());
			}
            // 使用NameValuePair来保存要传递的Post参数  
            List<NameValuePair> params = new ArrayList<NameValuePair>();  
            // 添加要传递的参数  
            for(Map.Entry<String, String> entry: ps.entrySet()){
				 params.add(new BasicNameValuePair(entry.getKey(),entry.getValue())); 
			}
           
            // 设置字符集  
            HttpEntity httpentity = new UrlEncodedFormEntity(params, "utf-8");  
            // 请求httpRequest  
            httpost.setEntity(httpentity);  
            // 取得默认的HttpClient  
            // 取得HttpResponse  
            HttpResponse httpResponse = client.execute(httpost);  
            // HttpStatus.SC_OK表示连接成功  
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                // 取得返回的字符串  
                result = EntityUtils.toString(httpResponse.getEntity());  
            }
            if(result==null){
            	result="{}";
            }
	    }catch(Exception e){
	        e.printStackTrace();
	    }
		return result;
    }
}
