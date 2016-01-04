package com.uhoo.uhooapi.app.daos.jdbc;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
/**
 * 数据库操作对象
 */
@SuppressWarnings("rawtypes")
public class EasyMapsManager {

    private DatabaseAccess dao;
    /**
     * 结果集列表
     */
	private ArrayList<HashMap> dataList;

    public EasyMapsManager(DatabaseAccess dao) {
        this.dao = dao;//数据库操作对象
    }

    /**
     * 加入新的预处理参数
     *
     * @param parameter 参数
     * @return 自身对象
     */
    public void setPreparedParameter(Object parameter) {
        dao.setPreparedParameter(parameter);
    }

    /**
     * 从数据库查询结果集合中取得数据
     *
     * @param rs 数据库查询结果集合
     * @return List<EasyData>
     */
    @SuppressWarnings("unchecked")
	private ArrayList<HashMap> loadData(ResultSet rs) {
        dataList = new ArrayList<HashMap>();
        try {
        	if(rs!=null){
	            ResultSetMetaData metaData = rs.getMetaData();
	            int columnCount = metaData.getColumnCount();
	            while (rs != null && rs.next()) {
	               // Object[] rowData = new Object[columnCount];
	                HashMap rowData=new HashMap();
	                for (int i = 1; i <= columnCount; i++) {
	                    rowData.put(metaData.getColumnLabel(i), rs.getObject(i));//获得列标签和名称是有区别的
	                }
	                dataList.add(rowData);
	            }
        	}
        } catch (SQLException ex) {
        	ex.printStackTrace();
            //System.out.println(this.getClass().getName() + ex);
        }
        return dataList;
    }

    /**
     * 获得查询集合，如果没有查询到结果，返回一个size=0的list
     *
     * @param sql
     * @return List<EasyData>
     */
    public ArrayList<HashMap> executeQuery(String sql) {
        try{
        	//System.out.println("-----------"+dao);
    	    loadData(dao.executeQuery(sql));
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	dao.close();
        }
        return dataList;
    }
    
    /**
     * 获得当前集合的总数
     *
     * @param sql
     * @return List<EasyData>
     */
    public int executeCount(String sql) {
    	int count=0;
        try{
    	    count=dao.executeCount(sql);
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	dao.close();
        }
        return count;
    }
}
