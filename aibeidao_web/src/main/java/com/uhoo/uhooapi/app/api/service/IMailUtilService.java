package com.uhoo.uhooapi.app.api.service;

public interface IMailUtilService {
	
	public void sendNoticeMail(String sendTo,String mailSubject);
	
	public void sendNoticeSalesMail(String sendTo,String mailSubject,String content);

	public void sendTotalTargetCompareEmail(String sendTo, String string,String targetingValue ,String forecastValue);
	
	public void sendPasswordMail(String username,String password,String sendTo);

}
