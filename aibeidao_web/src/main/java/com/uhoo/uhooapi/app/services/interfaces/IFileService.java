package com.uhoo.uhooapi.app.services.interfaces;

import com.uhoo.uhooapi.app.api.dto.MyFile;
import com.uhoo.uhooapi.app.api.dto.Pagination;
import com.uhoo.uhooapi.app.api.service.IMyService;

/**
 * 
* @ClassName: IResourceService
* @Description: 微信资源服务接口
* @author yang.yun
* @date 2014年5月13日 下午4:33:00
*
 */
public interface IFileService extends IMyService{

	public void add(MyFile msg);
	
	public MyFile queryResourceById(String messageId);
	
	public void query(Pagination page);

	public void delete(String fileid);
	
}
