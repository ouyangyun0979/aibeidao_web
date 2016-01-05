package com.uhoo.uhooapi.app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.uhoo.uhooapi.app.api.dao.AbstractBaseDAO;
import com.uhoo.uhooapi.app.model.AbdUser;
import com.uhoo.uhooapi.web.util.SHA1;

/**
 * 
 * @ClassName: UserManageDAO
 * @Description: 用户信息数据访问层
 * @author yang.yun
 * @date 2014年4月30日 下午5:40:21
 *
 */
@Repository
public class AbdUserDao extends AbstractBaseDAO {

	public AbdUser loginValidate(String username, String password) {
		System.out.println(username+"---------"+password);
		List<AbdUser> beans = jdbcTemplate.query(
				"SELECT * FROM user where 1=1 and (nick_name=? or account=? or email=?) and password=?",
				new Object[] {username,username,username,SHA1.hex_sha1(password)},
				new BeanPropertyRowMapper<AbdUser>(AbdUser.class));
		if (beans != null && beans.size() > 0) {
			return beans.get(0);
		}
		return null;
	}
	
	private static final String SAVE_SQL = "INSERT USER(account,email,password,nick_name,register_time,login_time,last_login_time,update_time,status) VALUES (?,?,?,?,NOW(),NOW(),NOW(),NOW(),0)";
	public AbdUser save(final AbdUser bean) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
                PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(SAVE_SQL,new String[]{"account","email","password","nick_name"});
                ps.setObject(1, bean.getAccount());
				ps.setObject(2, bean.getEmail());
				ps.setObject(3, bean.getPassword());
				ps.setObject(4, bean.getNick_name());
                return ps;
            }
	    }, keyHolder);
		int id=keyHolder.getKey().intValue();
		bean.setId(id);
		return bean;
	}
	
	public int validate(String username, String phone) {
		int errorcode = 0;
		List<AbdUser> beans = jdbcTemplate.query("SELECT * FROM USER where nick_name=?",new Object[] {username}, new BeanPropertyRowMapper<AbdUser>(AbdUser.class));
		if (beans != null && beans.size() > 0) {
			errorcode = 1;
		} else {
			beans = jdbcTemplate.query("SELECT * FROM USER where account=?",new Object[] { phone }, new BeanPropertyRowMapper<AbdUser>(AbdUser.class));
			if (beans != null && beans.size() > 0) {
				errorcode = 2;
			}
		}
		return errorcode;
	}

}
