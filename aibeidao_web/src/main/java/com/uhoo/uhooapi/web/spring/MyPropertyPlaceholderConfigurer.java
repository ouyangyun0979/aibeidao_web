package com.uhoo.uhooapi.web.spring;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.uhoo.uhooapi.app.api.conf.MyConfig;

/**
 * 
* @ClassName: RainbowPropertyPlaceholderConfigurer
* @Description: spring配置文件替换
* @author yang.yun
* @date 2015年7月11日 下午11:34
*
 */
public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	
	@Override
	protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
		return MyConfig.get(placeholder);
	}

	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		return MyConfig.get(placeholder);
	}

}
