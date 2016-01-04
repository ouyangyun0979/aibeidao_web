package com.uhoo.uhooapi.app.model.mapper;

// Generated 2015-1-22 15:49:50 by Hibernate Tools 3.4.0.CR1

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class CommonRowMapper extends BaseRowMapper implements RowMapper<Object>{

	@Override
	public Map<String,Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();//得到数据源标记数据
		int columnCount = rsmd.getColumnCount();//得到当前数据源的总列数
		Map<String,Object> beanmap=new HashMap<String,Object>();
		for (int index = 1; index <= columnCount; index++) {
			String column = JdbcUtils.lookupColumnName(rsmd, index);
			//String type=rsmd.getColumnTypeName(index);
			Object value = JdbcUtils.getResultSetValue(rs, index, Object.class);
			beanmap.put(column, value);
		}
		return beanmap;
	}
}
