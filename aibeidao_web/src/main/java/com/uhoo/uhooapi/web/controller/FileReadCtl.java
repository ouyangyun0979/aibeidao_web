package com.uhoo.uhooapi.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uhoo.uhooapi.app.api.conf.MyConfig;
import com.uhoo.uhooapi.app.api.dto.MyFile;
import com.uhoo.uhooapi.app.services.interfaces.IFileService;
import com.uhoo.uhooapi.web.helper.ServiceHelper;

@Controller
@RequestMapping(value="/file")
public class FileReadCtl {
	
	public final Logger logger = LoggerFactory.getLogger(FileReadCtl.class);
	
	/**
	 * 资源文件存放根目录
	 */
	private static String rootPath = MyConfig.get("file.root.dir");
	
	private IFileService resourceService = (IFileService)ServiceHelper.getService("fileService");
	
	
	/**
	 * 文件读取
	 * @param username
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="get/{resourceid}", method = {RequestMethod.GET})
	public void get(@PathVariable("resourceid") String resourceid , HttpServletRequest request,HttpServletResponse response,Model model){
		logger.debug("Entre background main page.");
		MyFile res = resourceService.queryResourceById(resourceid);
		if(res != null){
			response.setContentType(res.getFileType());
			InputStream is = null;
			OutputStream os = null;
			File targetFile = new File(rootPath + res.getPath());
			try {
				os = response.getOutputStream();
				is = new FileInputStream(targetFile);
				byte[] b=new byte[1024]; 
	            int length;  
	            while((length = is.read(b))>0){  
	                os.write(b,0,length);  
	            }
			} catch (FileNotFoundException e) {
				//文件不存在就返回404异常
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException e2) {
					logger.warn("Write Response Error:",e2);
				}
			} catch (IOException e) {
				//文件不存在就返回404异常
				try {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException e2) {
					logger.warn("Write Response Error:",e2);
				}
			}finally{
				if(os != null){
					try {
						os.close();
					} catch (IOException e) {
						logger.error("close inputstream error", e);
					}
				}
				
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						logger.error("close outputstream error", e);
					}
				}
			}
		}else{
			//文件不存在就返回404异常
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e) {
				logger.warn("Write Response Error.",e);
			}
		}
	}
	
}
