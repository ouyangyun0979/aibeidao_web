package com.uhoo.uhooapi.web.util;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.uhoo.uhooapi.app.api.conf.MyConfig;

public class SendMailUtil {

	//发送找回密码邮件
    public static String sendLoginEmail(String email,String code) throws Exception{
		String notice="Send Success!";
		String notice1="Send E-mail failed!";
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		
		String host = MyConfig.get("mail.host");
		String mailfrom = MyConfig.get("mail.sendfrom");
		String mailusername = MyConfig.get("mail.username");
		String mailpassword = MyConfig.get("mail.password");
		// 设定mail server
		senderImpl.setHost(host);
		//senderImpl.setPort(193);
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
		// multipart模式 为true时发送附件 可以设置html格式
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
			// 设置收件人，寄件人
			messageHelper.setTo(email);
			messageHelper.setFrom(mailfrom);
			StringBuffer html = new StringBuffer();
			html.append("<h2>您好，欢迎注册良医城，你此次邮件验证码是:</h2><br/>");
			html.append("<h1>"+code+"</h1>");
			// true 表示启动HTML格式的邮件
			messageHelper.setSubject("良医城注册验证码邮件");
			messageHelper.setText("<html><head></head><body>"+html+"<br/>"+"</body></html>",true);
			senderImpl.setUsername(mailusername); // 根据自己的情况,设置username
			senderImpl.setPassword(mailpassword); // 根据自己的情况, 设置password
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
			prop.put("mail.smtp.timeout", "25000");
			senderImpl.setJavaMailProperties(prop);
			// 发送邮件
			senderImpl.send(mailMessage);
		} catch (MessagingException e) {
			return notice1;
		}
		return notice;
	}
}
