package com.uhoo.uhooapi.app.api.mail;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.uhoo.uhooapi.app.api.conf.MyConfig;
import com.uhoo.uhooapi.app.api.service.IMailUtilService;

/**
* @ClassName: MailUtilService
* @Description: 邮件工具服务
* @author yang.yun
* @date 2014年1月8日 上午9:20:44
*
 */
@Service
public class MailUtilService implements IMailUtilService{
	
	private Logger logger = LoggerFactory.getLogger(MailUtilService.class);
	
	@Resource
	private JavaMailSender javaMailSender = null;
	
	private String sendfrom = MyConfig.get("wacker.mail.sendfrom");
	
	private String accessUrl = MyConfig.get("wacker.access.url");
	
	@Override
	public void sendNoticeMail(String sendTo,String mailSubject){
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			messageHelper.setTo(sendTo);// 接受者 
			messageHelper.setFrom(sendfrom);//发送者 
			messageHelper.setSubject(mailSubject);// 主题
			StringBuilder sb = new StringBuilder();
			sb.append("<html><body>");
			sb.append("<br>");
			sb.append("<a href='" + accessUrl + "'>"+ accessUrl + "</a><br>");
			sb.append("<br>");
			sb.append("This is an automatically generated email, please do not reply.");
			sb.append("</body></html>");
			messageHelper.setText(sb.toString(), true);
			javaMailSender.send(mimeMessage);
			logger.info("send mail to {} success.",new Object[]{sendTo});
		} catch (MessagingException e) {
			logger.warn("mail send fail:",e);
		} catch (Throwable e){
			logger.warn("mail send fail:",e);
		}
	}
	
	@Override
	public void sendNoticeSalesMail(String sendTo,String mailSubject,String content){
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			messageHelper.setTo(sendTo);// 接受者 
			messageHelper.setFrom(sendfrom);//发送者 
			messageHelper.setSubject(mailSubject);// 主题
			StringBuilder sb = new StringBuilder();
			sb.append("<html><body>");
			sb.append("<br>");
			sb.append(content);
			sb.append("<br>");
			sb.append("This is an automatically generated email, please do not reply.");
			sb.append("</body></html>");
			messageHelper.setText(sb.toString(), true);
			javaMailSender.send(mimeMessage);
			logger.info("send mail to {} success.",new Object[]{sendTo});
		} catch (MessagingException e) {
			logger.warn("mail send fail:",e);
		} catch (Throwable e){
			logger.warn("mail send fail:",e);
		}
	}

	@Override
	public void sendTotalTargetCompareEmail(String email, String bt ,String targetingValue ,String forecastValue) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			messageHelper.setTo(email.trim());// 接受者 
			messageHelper.setFrom(sendfrom);//发送者 
			messageHelper.setSubject(bt + " quarter sales targeting revenue " + targetingValue + " is less than forecast revenue " +  forecastValue + "." );// 主题
			StringBuilder sb = new StringBuilder();
			sb.append("<html><body>");
			sb.append("<br>");
			sb.append("<a href='" + accessUrl + "'>"+ accessUrl + "</a><br>");
			sb.append("<br>");
			sb.append("This is an automatically generated email, please do not reply.");
			sb.append("</body></html>");
			messageHelper.setText(sb.toString(), true);
			javaMailSender.send(mimeMessage);
			logger.info("send mail to {} success." , new Object[]{email.trim()});
		} catch (MessagingException e) {
			logger.warn("mail send fail:" , e);
		} catch (Throwable e){
			logger.warn("mail send fail:" , e);
		}
	}

	@Override
	public void sendPasswordMail(String username,String password,String sendTo) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			messageHelper.setTo(sendTo.trim());// 接受者 
			messageHelper.setFrom(sendfrom);//发送者 
			messageHelper.setSubject("Your Password Mail");// 主题
			StringBuilder sb = new StringBuilder();
			sb.append("<html><body>");
			sb.append("Hello " + username + ", your password is '" + password + "' .<br>");
			sb.append("<br>");
			sb.append("This is an automatic mail from Sales Incentive System. Please do not reply.");
			sb.append("</body></html>");
			messageHelper.setText(sb.toString(), true);
			javaMailSender.send(mimeMessage);
			logger.info("send mail to {} success.",new Object[]{sendTo.trim()});
		} catch (MessagingException e) {
			logger.warn("mail send fail:",e);
		} catch (Throwable e){
			logger.warn("mail send fail:",e);
		}
	}

}
