package com.uhoo.uhooapi.app.api.dto;

import java.util.List;


/**
* @ClassName: ResourceResult
* @Description: 操作结果
* @author yang.yun
* @date 2013年11月15日 上午10:11:05
*
 */
public class OpResult extends AbstractDTO{
	
	private static final long serialVersionUID = 1L;

	private Boolean isOk = false;
	
	private String opErrorMsg = "";
	
	private String opMsg = "";
	
	private Pagination page = null;
	
	private Object data = null;
	
	private List<?> datas = null;
	
	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public Boolean getIsOk() {
		return isOk;
	}

	public void setIsOk(Boolean isOk) {
		this.isOk = isOk;
	}
	
	public String getOpMsg() {
		return opMsg;
	}

	public void setOpMsg(String opMsg) {
		this.opMsg = opMsg;
	}

	public String getOpErrorMsg() {
		return opErrorMsg;
	}

	public void setOpErrorMsg(String opErrorMsg) {
		this.opErrorMsg = opErrorMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}
}
