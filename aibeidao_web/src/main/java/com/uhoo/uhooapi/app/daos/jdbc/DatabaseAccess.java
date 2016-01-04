package com.uhoo.uhooapi.app.daos.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author e
 */

public class DatabaseAccess {

	private String drv = null;
	private String url = null;
	private String usr = null;
	private String pwd = null;
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private CallableStatement cstm = null;
	private ResultSet rs = null;
	private boolean isshowsql=false;
	/**
	 * 预处理参数列表
	 */
	private List<Object> parameterList = null;

	public DatabaseAccess(boolean isshowsql) {
		this.isshowsql=isshowsql;
		this.createPoolConn();// 创建连接池
	}
	public DatabaseAccess() {
		// 设定数据库参数的获得方式，直接设定字符和读配置文件
		this.createPoolConn();// 创建连接池
	}
	
	// 创建连接池连接
	private void createPoolConn() {
		try {
			//long begin = System.currentTimeMillis();
			conn = DbPoolConnection.getInstance().getConnection();
			parameterList = new ArrayList<Object>();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
	}
	
	public void reGetConn() {
		try {
			conn = DbPoolConnection.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 创建普通连接
	@SuppressWarnings("unused")
	private void createConn() {
		try {
			Class.forName(drv);
			conn = DriverManager.getConnection(url, usr, pwd);
			// 改用预处理后去掉2：一个创建
			// stm = conn.createStatement();
			parameterList = new ArrayList<Object>();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
	}
	/**
	 * 放置一个参数到sql预处理列表，执行一个sql后清空，必须在执行sql前执行。
	 * 
	 * @param parameter
	 *            参数
	 */
	public void setPreparedParameter(Object parameter) {
		parameterList.add(parameter);
	}

	/**
	 * 为sql加入参数
	 * 
	 * @param sql
	 *            预处理语句
	 * @throws SQLException
	 */
	private void prepare(String sql) throws SQLException {
		pstm = getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int index = 1;
		for (Object parameter : parameterList) {
			pstm.setObject(index++, parameter);
		}
		printSql(sql);
	}

	// 不清除预处理参数列表，来获取结果总条数
	// 如果sql参数是select count语句 则直接执行返回一个总条数，
	// 如果sql参数是一个普通查询语句，则返货此查询语句去掉limit子句后结果的总条数
	public int executeCount(String sql) {
		int count = 0;
		sql = sql.toLowerCase();
		if (sql.contains("count")) {
			try {
				this.prepare(sql);
				rs = pstm.executeQuery();
				if (rs != null && rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				//System.out.println(e.getMessage());
			}
		} else {
			if (sql.contains("limit")) {
				sql = sql.substring(0, sql.indexOf("limit"));
			}
			StringBuilder countSql = new StringBuilder();
			countSql.append("select count(1) from (").append(sql)
					.append(") as yy");
			try {
				this.prepare(countSql.toString());
				rs = pstm.executeQuery();
				if (rs != null && rs.next()) {
					count = rs.getInt(1);
				}

			} catch (SQLException e) {
				//System.out.println(e.getMessage());
			}
		}
		return count;
	}

	// 执行查询的SQL并返回结果集
	public ResultSet executeQuery(String sql) {
		try {
			this.prepare(sql);
			parameterList.clear();
			rs = pstm.executeQuery();
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
		}
		return rs;
	}

	// 可执行插入、更新、删除的SQL

	public boolean executeUpdate(String sql) {
		boolean b = false;
		try {
			this.prepare(sql);
			parameterList.clear();
			pstm.executeUpdate();
			b = true;
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return b;
	}

	// 专用于执行select语句，并放回数据库自动生成的主键。返回-1说明插入失败

	public Integer executeInsert(String sql) {
		Integer primarykey = -1;
		try {
			this.prepare(sql);
			pstm.execute();
			parameterList.clear();
			rs = pstm.getGeneratedKeys();
			while (rs.next()) {
				primarykey = rs.getInt(1);
			}
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return primarykey;
	}

	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				//System.out.println(e.getMessage());
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				//System.out.println(e.getMessage());
			}
		}
		if (cstm != null) {
			try {
				cstm.close();
			} catch (SQLException e) {
				//System.out.println(e.getMessage());
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				//System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * 输出SQL语句
	 * 
	 * @param sql
	 */
	private void printSql(String sql) {
		if(isshowsql){
			StringBuffer s = new StringBuffer(sql);
			for (Object o : parameterList) {
				if (o != null && o.getClass().getName().equals("java.lang.String")) {
					s = s.replace(s.indexOf("?"), s.indexOf("?") + 1,
							"'" + o.toString() + "'");
				} else {
					if (o == null) {
						s = s.replace(s.indexOf("?"), s.indexOf("?") + 1, "");
					} else {
						s = s.replace(s.indexOf("?"), s.indexOf("?") + 1,
								o.toString());
					}
				}
			}
			System.out.println("sql:" + s.toString());
		}
	}

	/**这里的处理逻辑是  如果已经关闭了链接  则需要重新从池子中拿到连接
	 * @return the conn
	 * @throws SQLException 
	 */
	public Connection getConn() {
		try {
			if(conn==null||conn.isClosed()){
				reGetConn();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 提供公共的方法开启事务
	 */
	public void transaction() {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 公共方法关闭事务
	 * 
	 * @param success
	 */
	public void transactionEnd(boolean success) {
		try {
			if (success) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
