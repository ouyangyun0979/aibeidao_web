package com.uhoo.uhooapi.web.util.httputil;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;


/**
 * @author yang.yun
 * 请求发送接口工具类
 */
public class HttpRequestUtil {
	
	public static final String ROOT_URL = "http://120.27.45.226/uhoo_api/api";
	
	/**获取access_token的接口地址（GET） 限200（次/天）*/
	public static final String URL_ACCESS_TOKEN = ROOT_URL+"/auth/access_token?worker_id=WORKER_ID&appid=APPID";
	
	public static String doAccessTokenRequest(String worker_id){
		String requestUrl = URL_ACCESS_TOKEN.replace("WORKER_ID",worker_id).replace("APPID", "20150818yy123");
		String tokenstr=HttpUtil.getJsonString(requestUrl);
		JSONObject jsonobject = JSONObject.fromObject(tokenstr);
		String token=jsonobject.getString("token");
		System.out.println("----------------token:"+token);
		return token;
	}
	
	//抢单
	public static final String URL_ACCEPT_ORDER_LIST = ROOT_URL+"/order/ORDER_ID/accept?token=ACCESS_TOKEN";
	public static String acceptOrder(String order_id,String token){
		String requestUrl = URL_ACCEPT_ORDER_LIST.replace("ACCESS_TOKEN",token).replace("ORDER_ID", order_id);
		String jsonstr = HttpUtil.getJsonString(requestUrl);
		JSONObject jsonobject = JSONObject.fromObject(jsonstr);
		System.out.println("---------------jsonstr:"+jsonstr);
		System.out.println("---------success:"+jsonobject.getBoolean("success"));
		if(jsonobject.getBoolean("success")){
			return null;
		}
		String error_desc=jsonobject.getString("error_desc");
		return error_desc;
	}
	
	//服务工单
	public static final String URL_SERVER_ORDER_LIST = ROOT_URL+"/order/ORDER_ID/service?token=ACCESS_TOKEN";
	public static String serviceOrder(String order_id, String token) {
		String requestUrl = URL_SERVER_ORDER_LIST.replace("ACCESS_TOKEN",token).replace("ORDER_ID", order_id);
		String jsonstr = HttpUtil.getJsonString(requestUrl);
		JSONObject jsonobject = JSONObject.fromObject(jsonstr);
		System.out.println("---------------服务工单jsonstr:"+jsonstr);
		System.out.println("---------success:"+jsonobject.getBoolean("success"));
		if(jsonobject.getBoolean("success")){
			return null;
		}
		String error_desc=jsonobject.getString("error_desc");
		return error_desc;
	}
	
	public static final String URL_ASKORDER_URL = ROOT_URL+"/servicecategory/SERVICE_ID/order/new";
	public static String askOrder(String serviceid, String user_id, String addr_address, String datetime, String number,String token) {
		String requestUrl = URL_ASKORDER_URL.replace("SERVICE_ID", serviceid);
		String notes="系统管理员提交工单。";
		Map<String,String> params=new HashMap<String,String>();
		Map<String,String> hparams=new HashMap<String,String>();
		params.put("datetime", datetime);
		params.put("number", number);
		params.put("addr_address", addr_address);
		hparams.put("token", token);
		params.put("datetime", datetime);
		params.put("notes", notes);
		String jsonstr = HttpUtil.sendPostData(requestUrl,params,hparams);
		System.out.println("---------------发起工单返回:"+jsonstr);
		JSONObject jsonobject = JSONObject.fromObject(jsonstr);
		if(jsonobject.getBoolean("success")){
			return null;
		}
		String error_desc=jsonobject.getString("error_desc");
		return error_desc;
	}
}
