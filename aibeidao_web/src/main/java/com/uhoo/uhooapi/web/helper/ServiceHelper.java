package com.uhoo.uhooapi.web.helper;

import org.springframework.context.support.AbstractApplicationContext;

import com.uhoo.uhooapi.app.api.service.IMyService;

/**
* 
* @ClassName: ServiceHelper
* @Description: Service调用的帮助类
* @author yang.yun
* @date 2013年11月20日 上午10:05:53
*
*/
public class ServiceHelper {
	
	private static AbstractApplicationContext springctx = null;
	
	public static IMyService getService(String serviceName){
		return (IMyService)springctx.getBean(serviceName);
	}
	
    public static void setApplicationContext(AbstractApplicationContext ctx){
    	springctx = ctx;
    }

}
