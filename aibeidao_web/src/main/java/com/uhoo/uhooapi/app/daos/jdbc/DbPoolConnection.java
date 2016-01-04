package com.uhoo.uhooapi.app.daos.jdbc;

import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.uhoo.uhooapi.app.api.conf.MyConfig;

public class DbPoolConnection {
	private static DbPoolConnection databasePool = null;
    private static DruidDataSource dds = null;
    static {
        Properties properties = MyConfig.getProps();
        //System.out.println("----properties:"+properties.getProperty("minEvictableIdleTimeMillis"));
        try {
            dds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private DbPoolConnection() {}
    public static synchronized DbPoolConnection getInstance() {
        if (null == databasePool) {
            databasePool = new DbPoolConnection();
        }
        return databasePool;
    }
    public DruidPooledConnection getConnection() throws SQLException {
        return dds.getConnection();
    }
   
}
