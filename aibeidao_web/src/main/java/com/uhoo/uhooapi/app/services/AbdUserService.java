package com.uhoo.uhooapi.app.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.uhoo.uhooapi.app.daos.AbdUserDao;
import com.uhoo.uhooapi.app.model.AbdUser;
import com.uhoo.uhooapi.app.services.interfaces.IAbdUserService;

/**
 * 
* @ClassName: ResourceService
* @Description: 资源服务实现类
* @author Yun.yang
* @date 2014年5月13日 下午4:46:35
*
 */
@Service
public class AbdUserService implements IAbdUserService{
	
	
	@Resource
	private AbdUserDao abdUserDao= null;

	@Override
	public int validate(String username, String phone) {
		return abdUserDao.validate(username, phone);
	}

	@Override
	public AbdUser save(AbdUser user) {
		return abdUserDao.save(user);
	}

	@Override
	public AbdUser loginValidate(String username, String password) {
		return abdUserDao.loginValidate(username,password);
	}
	
}
