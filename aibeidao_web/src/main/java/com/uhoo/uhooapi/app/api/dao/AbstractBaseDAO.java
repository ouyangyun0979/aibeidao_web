package com.uhoo.uhooapi.app.api.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.uhoo.uhooapi.app.api.util.GUIDHexGenerator;


/**
 * 
* @ClassName: AbstractBaseDao
* @Description: 基础DAO抽象类
* @author yang.yun
* @date 2013年11月20日 上午9:33:15
* 
* @param <T>
 */
public class AbstractBaseDAO{
	
	@Resource
	protected JdbcTemplate jdbcTemplate;
	
	public static String generateId(){
		return GUIDHexGenerator.getInstance().generateId();
	}

}
