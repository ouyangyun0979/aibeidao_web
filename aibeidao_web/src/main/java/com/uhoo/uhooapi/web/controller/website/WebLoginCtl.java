package com.uhoo.uhooapi.web.controller.website;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uhoo.uhooapi.app.model.AbdUser;
import com.uhoo.uhooapi.app.services.interfaces.IAbdUserService;
import com.uhoo.uhooapi.web.controller.AbstractCtl;
import com.uhoo.uhooapi.web.helper.ServiceHelper;
import com.uhoo.uhooapi.web.util.SHA1;



@Controller
@RequestMapping(value="")
public class WebLoginCtl extends AbstractCtl{
	
	public final Logger logger = LoggerFactory.getLogger(WebLoginCtl.class);
	private IAbdUserService abdUserService = (IAbdUserService)ServiceHelper.getService("abdUserService");
	
	@RequestMapping(value="index",method={RequestMethod.GET,RequestMethod.POST})
	public String toindex(Model model) {
		return "index";
	}
	
	@RequestMapping(value="tologin",method={RequestMethod.GET,RequestMethod.POST})
	public String tologin(Model model,String type) {
		return "login";
	}
	
	@RequestMapping(value="toregister",method={RequestMethod.GET,RequestMethod.POST})
	public String toregister(Model model,String type,RedirectAttributes attrs) {
		attrs.addFlashAttribute("errortype", "1");
		return "redirect:/app/tologin";
	}
	
	@RequestMapping(value="logout",method={RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session,Model model) {
		session.removeAttribute("login_user");
		return "redirect:tologin";
	}
	
	@RequestMapping(value="login/submit",method={RequestMethod.GET,RequestMethod.POST})
	public String login(Model model,String login_username,String login_password,HttpSession session,RedirectAttributes attrs) {
		AbdUser user=abdUserService.loginValidate(login_username,login_password);
		if(user!=null){
			session.setAttribute("login_user", user);
			return "redirect:/app/index";
		}else{
			attrs.addFlashAttribute("errorMsg", "用户名或密码错误");
			return "redirect:/app/tologin";
		}
	}
	@RequestMapping(value="register/submit",method={RequestMethod.GET,RequestMethod.POST})
	public String register(Model model,String username,String password,String phone,String verifycode,HttpSession session,RedirectAttributes attrs) {
		try {
			String sessioncode=(String) session.getAttribute("verifycode");
			sessioncode="123456";
			if(!verifycode.toLowerCase().equals(sessioncode.toLowerCase())){
				attrs.addFlashAttribute("errorMsg", "验证码不正确");
				attrs.addFlashAttribute("errortype", "1");
				return "redirect:/app/tologin";
			}else{
				int errorcode=abdUserService.validate(username,phone);
				if(errorcode==1){
					attrs.addFlashAttribute("errorMsg", "用户名已经存在");
					attrs.addFlashAttribute("errortype", "1");
					return "redirect:/app/tologin";
				}else if(errorcode==2){
					attrs.addFlashAttribute("errorMsg", "手机已经存在");
					attrs.addFlashAttribute("errortype", "1");
					return "redirect:/app/tologin";
				}
				AbdUser user=new AbdUser();
				user.setAccount(phone);
				user.setPassword(SHA1.hex_sha1(password));
				user.setNick_name(username);
				user=abdUserService.save(user);
				session.setAttribute("login_user", user);
				return "redirect:/app/index";
			}
		} catch (Exception e) {
			attrs.addFlashAttribute("errorMsg", "系统异常");
			attrs.addFlashAttribute("errortype", "1");
			e.printStackTrace();
			return "redirect:/app/tologin";
		}
	}
	
}