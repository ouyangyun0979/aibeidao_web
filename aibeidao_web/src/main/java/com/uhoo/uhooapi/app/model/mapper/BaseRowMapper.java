package com.uhoo.uhooapi.app.model.mapper;

// Generated 2015-1-22 15:49:50 by Hibernate Tools 3.4.0.CR1

import java.sql.ResultSet;

public class BaseRowMapper{

	public boolean isContainsColumn(ResultSet rs,String Column){
		try{
			if (rs.findColumn(Column) > 0 ) {
	            return true;
	        }
			return false;
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}
}
