package com.uhoo.uhooapi.web.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.uhoo.uhooapi.app.api.dto.MyFile;
import com.uhoo.uhooapi.app.api.util.GUIDHexGenerator;
import com.uhoo.uhooapi.app.services.interfaces.IFileService;
import com.uhoo.uhooapi.web.helper.ServiceHelper;


/**
 * 文件上传工具类，实现多文件上传功能
 * @author yang.yun
 *
 */
public class FileUploadUtil {
	
	private static String rootdir="E:/upload/new_lyc";
	public final static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
	private static IFileService fileService = (IFileService)ServiceHelper.getService("fileService");
	
   public static List<String> uploadFile(HttpServletRequest request){
	   List<String> ids=new ArrayList<String>();
	   try{
		    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		    //判断 request 是否有文件上传,即多部分请求  
			MyFile res = null;
			if(multipartResolver.isMultipart(request)){
		        //转换成多部分request    
		        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
		        //取得request中的所有文件名  
		        Iterator<String> iter = multiRequest.getFileNames();  
		        int i=0;
		        while(iter.hasNext()){  
		        	i++;
		            //记录上传过程起始时的时间，用来计算上传时间  
		            int pre = (int) System.currentTimeMillis();  
		            //取得上传文件  
		            MultipartFile file = multiRequest.getFile(iter.next());  
		            if(file != null){
		                //取得当前上传文件的文件名称  
		                String origfilename = file.getOriginalFilename();  
		                String filename = origfilename.substring(origfilename.lastIndexOf("\\")+1);
		                String regx=filename.substring(filename.indexOf("."));
		                //System.out.println("......得到文件后缀名："+regx);
		                //long timesatap=(new Date()).getTime();
		                String resid = GUIDHexGenerator.getInstance().generateId();
		                ids.add(resid);
		                filename=resid+regx;
		                String path = rootdir+"/file";
		                File outfile = new File(path);
		        	    //判断上传文件的保存目录是否存在
		        		if (!outfile.exists() && !outfile.isDirectory()) {
		        			// System.out.println("......"+path+"目录不存在，需要创建");
		        			 outfile.mkdirs();
		        		}
		        		File localFile = new File(path+"/"+filename);
		        		file.transferTo(localFile); 
		        		res = new MyFile();
		    			res.setId(resid);
		    			res.setName(origfilename);
		    			res.setFileName(filename);
		    			res.setFileSize(String.valueOf(file.getSize()/1024));
		    			res.setFileType(regx);
		    			res.setPath("/file/"+filename);
		    			res.setUpload_type("phone");
		    			res.setType(null);
		    			fileService.add(res);
	        	        System.out.println("......文件"+i+"上传成功");
	        	        System.out.println("......上传路径绝对路径："+path);
	        	        System.out.println("......数据库路径："+res.getPath());
		            }  
		            //记录上传该文件后的时间
		            int finaltime = (int) System.currentTimeMillis();  
		            System.out.println("......上传时间："+(finaltime - pre));  
		        }
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("-------------upload出现异常：{}",e.getMessage());
		}
	    return ids;
   }
	
	
}
