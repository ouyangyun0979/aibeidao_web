package com.uhoo.uhooapi.web.controller;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.uhoo.uhooapi.web.util.RespContent;


public class AbstractCtl {
	
	public final Logger logger = LoggerFactory.getLogger(AbstractCtl.class);
	
	/**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
   public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
		    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
		    Matcher matcher = regex.matcher(mobileNumber);
		    flag = matcher.matches();
	   }catch(Exception e){
		    flag = false;
	   }
	  return flag;
	 }
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, HttpServletResponse response) {
	    return "404.jsp";
	}
	
	public String getIpAddr(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        return ip; 
    }

	@SuppressWarnings("deprecation")
	public RespContent checkParam(Map<String, String> param){
		RespContent resp = new RespContent();
		resp.setError_code("0");
		resp.setError_desc("访问成功");
		Iterator<String> it = param.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println("-----key:"+key+"==val:"+param.get(key));
			param.put(key, URLDecoder.decode(param.get(key)));
		}
		return resp;
	}
}
