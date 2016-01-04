package com.uhoo.uhooapi.app.api.dto;


/**
* @ClassName: FileMetaDTO
* @Description: 文件上传结果-单文件信息
* @author yang.yun
* @date 2013年11月15日 上午10:11:05
*/
public class FileMetaDTO extends AbstractDTO{
	
	private static final long serialVersionUID = 1L;
	private String fileName;
    private String fileSize;
    private String fileType;
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
