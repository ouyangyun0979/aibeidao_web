package com.uhoo.uhooapi.app.services;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.uhoo.uhooapi.app.api.dto.MyFile;
import com.uhoo.uhooapi.app.api.dto.Pagination;
import com.uhoo.uhooapi.app.daos.FileDAO;
import com.uhoo.uhooapi.app.services.interfaces.IFileService;
import com.uhoo.uhooapi.web.util.MD5;

/**
 * 
* @ClassName: ResourceService
* @Description: 资源服务实现类
* @author yang.yun
* @date 2014年5月13日 下午4:46:35
*
 */
@Service
public class FileService implements IFileService{
	
	private Logger logger = LoggerFactory.getLogger(FileService.class);
	
	@Resource
	private FileDAO fileDao= null;

	@Override
	public void add(MyFile msg) {
		fileDao.save(msg);
		logger.debug("add resource ok.");
	}

	@Override
	public MyFile queryResourceById(String resid) {
		return fileDao.queryById(resid);
	}

	@Override
	public void query(Pagination page) {
		fileDao.query(page);
	}

	@Override
	public void delete(String fileid) {
		fileDao.delete(fileid);
	}
public static void main(String[] args) {
	System.out.println(MD5.md5s("hhjz"));
}
}
