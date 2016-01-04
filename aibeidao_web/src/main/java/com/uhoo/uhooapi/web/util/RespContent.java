package com.uhoo.uhooapi.web.util;

import java.util.List;



public class RespContent {
//	0	成功	
//	1	常规错误	
//	101	未授权	需登录
//	102	没有操作权限	没有相应的操作权限，不需重新登录
//	103	用户不存在	
//	104	时间戳不在有效范围	
//	105	登录验证失败	
//	201	缺少必填参数	
//	202	参数格式错误	
//	203	对象不存在	
//	204	参数取值错误	
//	999
	private boolean success;
	private String error_code;
	private String error_desc;
	private String error_url;
	private Object obj;
	private List<Object> objs;
	private Page page;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public RespContent(){
		this.error_code = "1";
		this.error_desc = "调用服务异常，请稍后再试!";
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		if(success){
			this.error_desc="";
			this.error_code="0";
		}else{
			this.error_code="1";
		}
		this.success = success;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public String getError_desc() {
		return error_desc;
	}
	public void setError_desc(String error_desc) {
		this.error_desc = error_desc;
	}
	public String getError_url() {
		return error_url;
	}
	public void setError_url(String error_url) {
		this.error_url = error_url;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public List<Object> getObjs() {
		return objs;
	}
	public void setObjs(List<Object> objs) {
		this.objs = objs;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
