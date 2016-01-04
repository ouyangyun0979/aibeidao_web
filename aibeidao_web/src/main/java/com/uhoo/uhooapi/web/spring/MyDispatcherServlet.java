package com.uhoo.uhooapi.web.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.uhoo.uhooapi.app.api.conf.MyConfig;
import com.uhoo.uhooapi.app.api.exception.ConfigException;
import com.uhoo.uhooapi.web.helper.ServiceHelper;

public class MyDispatcherServlet extends DispatcherServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(MyDispatcherServlet.class);
	
	private static AbstractApplicationContext springctx = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		logger.info("uhoo web init.");
		logger.info("uhoo web load config start.");
		File sysconfig = new File("config/newlyc.properties");
		logger.info("uhoo load config from {}",sysconfig.getAbsolutePath());
		Properties prop = new Properties();
		InputStreamReader is = null;
		try {
			is = new InputStreamReader(new FileInputStream(sysconfig),"UTF-8");
			prop.load(is);
			MyConfig.load(prop);
		} catch (FileNotFoundException e) {
			throw new ConfigException("uhoo.conf not found.",e);
		} catch (IOException e) {
			throw new ConfigException("uhoo.conf read fail.",e);
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					logger.warn("uhoo.conf InputStreamReader close error.");
				}
			}
		}
		logger.info("uhoo web load config ok.");
		
		logger.info("uhoo web load service spring start.");
		springctx = new ClassPathXmlApplicationContext(new String[]{"classpath*:spring-app.xml"});
		ServiceHelper.setApplicationContext(springctx);
		logger.info("uhoo web load service spring ok.");
		logger.info("uhoo web init ok.");
		super.init(config);
	}

	@Override
	public void destroy() {
		springctx.close();
		super.destroy();
	}
}
