package com.uhoo.uhooapi.app.api.conf;

import java.util.Properties;

public class MyConfig {

	private static Properties prop = new Properties();
	
	public static final String FILE_DIR = "file.root.dir";
	
	/**
	 * 重新装置配置
	 * @param newprop
	 */
	public static void load(Properties newprop){
		prop = newprop;
	}
	
	public static Properties getProps(){
		return prop;
	} 
	
	/**
	 * 读取单个系统属性
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return prop.getProperty(key,null);
	}
	
	/**
	 * 设置单个系统属性
	 * @param key
	 * @param value
	 */
	public static void set(String key,String value){
		prop.setProperty(key, value);
	}
	
}
