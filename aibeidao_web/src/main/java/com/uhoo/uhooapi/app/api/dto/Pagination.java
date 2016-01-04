package com.uhoo.uhooapi.app.api.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pagination extends AbstractDTO{

	private static final long serialVersionUID = 1L;

	private int maxRows = 0;
	
	private int maxPage = 0;
	
	private int curPage = 1;
	
	private int pageSize = 18;
	
	private List<? extends IDataDTO> datas = null;
	
	private Map<String , String> params = new HashMap<String,String>();
	
	private List<String> orders = null;
	
	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
		int tmp_maxPage = this.maxRows % this.pageSize;
		if(tmp_maxPage == 0){
			this.maxPage = this.maxRows / this.pageSize + 1;
		}else{
			this.maxPage = (this.maxRows / this.pageSize) + 2;
		}
		maxPage = maxPage - 1;
		if(maxPage < 1)maxPage = 1;
	}
	
	public int calculateMaxRecord(){
		return (this.curPage - 1) * this.pageSize;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<? extends IDataDTO> getDatas() {
		return datas;
	}

	public void setDatas(List<? extends IDataDTO> datas) {
		this.datas = datas;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public List<String> getOrders() {
		return orders;
	}

	public void setOrders(List<String> orders) {
		this.orders = orders;
	}

}
