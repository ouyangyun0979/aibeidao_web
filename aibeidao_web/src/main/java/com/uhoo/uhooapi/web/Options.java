package com.uhoo.uhooapi.web;
/**
 * 数据字典类
 * @author Administrator
 *
 */
public class Options {
	
	public static final String SYS_LOGIN_ADMIN = "sys_login_admin";
	public static final String SYS_LOGIN_ROLE = "sys_login_role";
	public static final String LOGIN_PROVIDER_ID = "login_provider_id";
	
	public static final String SYSROLE_ADMIN = "admin";//后台登录员的角色
	public static final String SYSROLE_OPERATOR = "operator";//后台登录员的角色
	public static final String SYSROLE_USER = "user";//后台登录员的角色
	public static final String SYSROLE_COMPANY = "company";//后台管理公司管理员角色
	
	//订单状态部分参数   工单状态 submit:待受理; accept:待服务; service:待支付; close:已完成; cancel:已取消
	public static final String ORDER_STATUS_SUBMIT = "submit";
	public static final String ORDER_STATUS_PREACCEPT = "preaccept";
	public static final String ORDER_STATUS_ACCEPT = "accept";
	public static final String ORDER_STATUS_SERVICE = "service";
	public static final String ORDER_STATUS_CLOSE = "close";
	public static final String ORDER_STATUS_CANCEL = "cancel";
	
	//支付方式 cash:现金; alipay:支付宝; weixin:微信支付;ucoin:余额支付
	public static final String ORDER_PAYM_CASH = "cash";
	public static final String ORDER_PAYM_ALIPAY = "alipay";
	public static final String ORDER_PAYM_WEIXIN = "weixin";
	public static final String ORDER_PAYM_UCOIN = "ucoin";
		
	//用户账单类型
	public static final String BILL_TYPE_INCASH = "incash";//充值入账
	public static final String BILL_TYPE_PAYORDER = "payorder";//支付订单
	
	public static Integer ORDER_NUM = 0;
	//session域保存数据
	public static final String SMS_SEND_CODE = "sms_send_code";
	public static final String SMS_SEND_PHONE = "sms_send_phone";
	public static final String SMS_SEND_HOST_IPSTR = "last_sms_ipstr";
	public static final String SMS_SEND_TIME="last_sms_time";

	

	
	public static final String TYPE_SERVER = "type_server";
	public static final String TYPE_CUSTOMER = "type_customer";

	//用户角色
	public static final String USERROLE_USER = "user";
	public static final String USERROLE_WORKER = "worker";
	
	public static final String ANDROID_USERID = "android_userid";
	public static final String ANDROID_TOKEN = "android_token";


	
	
	
	public static final String ACCEPT_BYCOM = "bycom";
	public static final String ACCEPT_BYSERVER = "byserver";

	


	
	public static int expire_day=3;
	
	
	///////////////新
	public static final Integer SYS_SUPERADMIN =0;
	public static final Integer SYS_ADMIN =1;
	
	//首页显示个数
	public static final Integer INDEX_NUM = 8;
	//分类类型
	public static final String C_TYPE_INSTANCE = "instance";
	public static final String C_TYPE_DIARY = "diary";
	public static final String C_TYPE_QUESTION = "question";
	//首页推荐问题标题个数
	public static final int INDEX_QST_NUM = 4;
	//热推问题个数
	public static final int HOT_QST_NUM = 8;
	//经典问题个数
	public static final int CLASSIC_QST_NUM = 8;
	//热推实例个数
	public static final int HOT_INSTANCE_NUM = 8;
	
	public static final String REGISTER_TYPE_QQ = "qq";
	public static final String REGISTER_TYPE_SINA = "sina";
	
	
	
	
}
