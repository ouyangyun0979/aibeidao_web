package com.uhoo.uhooapi.app.api.dto;

import java.util.Date;


/**
 * 
* @ClassName: WxResource
* @Description: 微信资源DTO
* @author yang.yun
* @date 2014年5月13日 下午4:29:24
*
 */
public class MyFile extends AbstractDTO{

	private static final long serialVersionUID = 1L;
	
	private String id = null;
	
	private String name = null;
	
	private String fileName = null;
	
	private String fileType = null;
	
	private String fileSize = null;
	
	private String path = null;
	
	private String type = null;
	
	private String upload_type = null;
	
	private Date create_time;

	public String getUpload_type() {
		return upload_type;
	}

	public void setUpload_type(String upload_type) {
		this.upload_type = upload_type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
