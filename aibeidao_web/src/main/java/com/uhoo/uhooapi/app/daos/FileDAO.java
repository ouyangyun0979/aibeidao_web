package com.uhoo.uhooapi.app.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uhoo.uhooapi.app.api.dao.AbstractBaseDAO;
import com.uhoo.uhooapi.app.api.dto.MyFile;
import com.uhoo.uhooapi.app.api.dto.Pagination;

/**
 * 
* @ClassName: MessageDAO
* @Description: 资源管理数据访问层
* @author yang.yun
* @date 2014年4月30日 下午5:40:21
*
 */
@Repository
public class FileDAO extends AbstractBaseDAO{
	
	private static final String RES_QUERY_BY_ID = "SELECT ID,NAME,FILE_NAME,FILE_TYPE,FILE_SIZE,PATH,TYPE,CREATE_TIME,UPLOAD_TYPE FROM LYC_FILE WHERE ID = ?";
	
	public MyFile queryById(String id){
		List<MyFile> list = jdbcTemplate.query(RES_QUERY_BY_ID, new Object[]{id}, new RowMapper<MyFile>(){
			@Override
			public MyFile mapRow(ResultSet rs, int rowNum) throws SQLException {
				MyFile res = new MyFile();
				res.setId(rs.getString("ID"));
				res.setName(rs.getString("NAME"));
				res.setFileName(rs.getString("FILE_NAME"));
				res.setFileType(rs.getString("FILE_TYPE"));
				res.setFileSize(rs.getString("FILE_SIZE"));
				res.setPath(rs.getString("PATH"));
				res.setType(rs.getString("TYPE"));
				res.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				res.setUpload_type(rs.getString("UPLOAD_TYPE"));
				return res;
			}
		});
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	private static final String RES_SAVE_SQL = "INSERT LYC_FILE(ID,NAME,FILE_NAME,FILE_TYPE,FILE_SIZE,PATH,TYPE,CREATE_TIME,UPLOAD_TYPE) VALUES (?,?,?,?,?,?,?,NOW(),?)";

	public void save(final MyFile res){
		if(res.getId() == null){
			res.setId(generateId());
		}
		jdbcTemplate.update(RES_SAVE_SQL, new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, res.getId());
				ps.setString(2, res.getName());
				ps.setString(3, res.getFileName());
				ps.setString(4, res.getFileType());
				ps.setString(5, res.getFileSize());
				ps.setString(6, res.getPath());
				ps.setString(7, res.getType());
				ps.setString(8, res.getUpload_type());
			}
		});
	}
	
	private static final String RES_QUERY_COUNT_SQL = "SELECT COUNT(ID) FROM LYC_FILE";
	
	private static final String RES_QUERY_SQL = "SELECT ID,NAME,FILE_NAME,FILE_TYPE,FILE_SIZE,PATH,TYPE,CREATE_TIME,UPLOAD_TYPE FROM LYC_FILE";
	
	public void query(final Pagination p){
		String input=p.getParams().get("input");
		String wheresql=" WHERE 1=1 ";
		if(input!=null&&!"".equals(input)){
			wheresql+=" AND NAME LIKE '%"+input+"%'";
		}
		Integer count = jdbcTemplate.queryForObject(RES_QUERY_COUNT_SQL+wheresql,Integer.class);
		p.setMaxRows(count);
		List<MyFile> list = jdbcTemplate.query(RES_QUERY_SQL+wheresql+" ORDER BY CREATE_TIME DESC LIMIT ? , ?", new Object[]{p.calculateMaxRecord(),p.getPageSize()}, new RowMapper<MyFile>(){
			@Override
			public MyFile mapRow(ResultSet rs, int rowNum) throws SQLException {
				MyFile res = new MyFile();
				res.setId(rs.getString("ID"));
				res.setName(rs.getString("NAME"));
				res.setFileName(rs.getString("FILE_NAME"));
				res.setFileType(rs.getString("FILE_TYPE"));
				res.setFileSize(rs.getString("FILE_SIZE"));
				res.setPath(rs.getString("PATH"));
				res.setType(rs.getString("TYPE"));
				res.setCreate_time(rs.getTimestamp("CREATE_TIME"));
				res.setUpload_type(rs.getString("UPLOAD_TYPE"));
				return res;
			}
		});
		p.setDatas(list);
	}
	
	private static final String RES_DELETE_SQL = "DELETE FROM LYC_FILE WHERE ID = ?";
	public void delete(final String fileid) {
		if(fileid != null && fileid.trim().length() > 0){
			jdbcTemplate.update(RES_DELETE_SQL, new PreparedStatementSetter(){
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, fileid);
				}
			});
		}
	}
}
