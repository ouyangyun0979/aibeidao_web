package com.uhoo.uhooapi.app.api.exception;

/**
 * 配置异常
 * @author yang.yun
 */
public class ConfigException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ConfigException(String msg,Throwable e){
		super(msg,e);
	}

}
