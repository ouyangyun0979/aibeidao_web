package com.uhoo.uhooapi.app.services.interfaces;

import com.uhoo.uhooapi.app.api.service.IMyService;
import com.uhoo.uhooapi.app.model.AbdUser;

/**
 * 商品活动Service
 * @author yang.yun
 *
 */
public interface IAbdUserService extends IMyService{

	public int validate(String username, String phone);

	public AbdUser save(AbdUser user);

	public AbdUser loginValidate(String username, String password);


}
