package com.uhoo.uhooapi.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uhoo.uhooapi.app.api.dto.OpResult;
import com.uhoo.uhooapi.web.util.FileUploadUtil;


//测试1
@Controller
@RequestMapping(value="/")
public class CommonCtl extends AbstractCtl{
	
	//日志配置初始化
	public final Logger logger = LoggerFactory.getLogger(CommonCtl.class);
	
	@RequestMapping(value="web/index",method={RequestMethod.GET,RequestMethod.POST})
	public String tologin(Model model) {
		return "index";
	} 
	@RequestMapping(value="manage",method={RequestMethod.GET,RequestMethod.POST})
	public String tomnglogin(Model model) {
		return "manage/login";
	}
	
	@RequestMapping(value = "file/upload",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody OpResult upload(HttpSession session,@RequestHeader Map<String,String> reqHeader,HttpServletRequest request,String username){
	   logger.info("-------进入api--api/file/upload--------------");
	   OpResult r = new OpResult();
	   try{
		   List<String> ids=FileUploadUtil.uploadFile(request);
		   r.setIsOk(true);
		   r.setDatas(ids);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("-------------upload出现异常：{}",e.getMessage());
		}
		return r;
	}
}