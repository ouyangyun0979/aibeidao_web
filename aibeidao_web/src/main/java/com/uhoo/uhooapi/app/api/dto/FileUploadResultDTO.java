package com.uhoo.uhooapi.app.api.dto;

import java.util.List;


/**
* @ClassName: FileUploadResultDTO
* @Description: 文件上传结果
* @author yang.yun
* @date 2013年11月15日 上午10:11:05
*
*/
public class FileUploadResultDTO extends AbstractDTO{
	
	private static final long serialVersionUID = 1L;
	
	private Boolean isOk = false;
	
	private String opErrorMsg = "";
	
	private String opMsg = "";
	
	private String fileId = "";
	
	private List<FileMetaDTO> files = null;
	
	public Boolean getIsOk() {
		return isOk;
	}

	public void setIsOk(Boolean isOk) {
		this.isOk = isOk;
	}

	public String getOpErrorMsg() {
		return opErrorMsg;
	}

	public void setOpErrorMsg(String opErrorMsg) {
		this.opErrorMsg = opErrorMsg;
	}

	public String getOpMsg() {
		return opMsg;
	}

	public void setOpMsg(String opMsg) {
		this.opMsg = opMsg;
	}

	public List<FileMetaDTO> getFiles() {
		return files;
	}

	public void setFiles(List<FileMetaDTO> files) {
		this.files = files;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
