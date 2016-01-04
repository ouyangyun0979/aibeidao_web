package com.uhoo.uhooapi.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Page{

	private int pagesize=10;//每页行数
	
	private int total=0;//总数
	
	private int pagecount=0;//分页总数
	
	private int page=1;//当前页号 
	
	private Map<String , String> params = new HashMap<String,String>();
	
	private List<Object> rows;
	
	private Object data;
	
	public Page(){
		
	}
	
	public Page(int pagesize){
		this.pagesize=pagesize;
	}
	
	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page<=0){
			page=1;
		}
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		int tmp_pagecount = this.total % this.pagesize;
		if(tmp_pagecount == 0){
			this.pagecount = this.total / this.pagesize + 1;
		}else{
			this.pagecount = (this.total / this.pagesize) + 2;
		}
		pagecount = pagecount - 1;
		if(pagecount < 1)pagecount = 1;
	}

	public int calculateMaxRecord(){
		return (this.page - 1) * this.pagesize;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
