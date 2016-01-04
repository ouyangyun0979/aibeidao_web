package com.uhoo.uhooapi.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.uhoo.uhooapi.app.api.conf.MyConfig;
import com.uhoo.uhooapi.app.api.dto.FileMetaDTO;
import com.uhoo.uhooapi.app.api.dto.FileUploadResultDTO;
import com.uhoo.uhooapi.app.api.dto.MyFile;
import com.uhoo.uhooapi.app.api.util.GUIDHexGenerator;
import com.uhoo.uhooapi.app.services.interfaces.IFileService;
import com.uhoo.uhooapi.web.helper.ServiceHelper;

@Controller
@RequestMapping(value="/file/json")
public class FileUploadJsonCtl {
	
	public final Logger logger = LoggerFactory.getLogger(FileUploadJsonCtl.class);
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 资源文件存放根目录
	 */
	private static String rootPath = MyConfig.get("file.root.dir");
	
	private IFileService resourceService = (IFileService)ServiceHelper.getService("fileService");

	
	/**
	 * 文件上传
	 * @param rsName
	 * @param mediaType
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="upload", method = {RequestMethod.GET,RequestMethod.POST})
	public void upload(String filePath ,String fileName ,String replaceFileId ,MultipartHttpServletRequest request ,HttpServletResponse response){
		//通过replaceFileId删除原有文件
		if(replaceFileId != null && replaceFileId.trim().length() > 0){
			MyFile replaceFile = resourceService.queryResourceById(replaceFileId);
			if(replaceFile != null){
				resourceService.delete(replaceFileId);
			}
		}
		FileUploadResultDTO result = new FileUploadResultDTO();
		List<FileMetaDTO> files = new ArrayList<FileMetaDTO>();
		Iterator<String> itr =  request.getFileNames();
		FileMetaDTO fileMeta = null;
		MultipartFile firstMpf = null;
		FileMetaDTO firstFileMeta = null;
		int i = 0;
		while(itr.hasNext()){
			MultipartFile mpf = request.getFile(itr.next());
			fileMeta = new FileMetaDTO();
            fileMeta.setFileName(mpf.getOriginalFilename());
            fileMeta.setFileSize(String.valueOf(mpf.getSize()/1024));
            fileMeta.setFileType(mpf.getContentType());
            files.add(fileMeta);
			if(i == 0){
				firstMpf = mpf;
				firstFileMeta = fileMeta;
			}
            i++;
		}
		//目前只支持一次上传一个资源
		if(files.size() > 0){
			//生成资源id
			String resid = null;
			if(replaceFileId != null && replaceFileId.trim().length() > 0){//继续使用原来图片的id
				resid = replaceFileId;
			}else{
				resid = GUIDHexGenerator.getInstance().generateId();
			}
			File targetDir = new File(rootPath + "/" + filePath);
			if(!targetDir.exists()){
				targetDir.mkdirs();
			}
			//保存文件
			System.out.println("-----------fileName:"+fileName);
			String endpres=firstFileMeta.getFileName().substring(firstFileMeta.getFileName().lastIndexOf(".")+1);
			System.out.println("-----------endpres:"+endpres);
			File targetFile = new File(rootPath + "/" + filePath + "/" + resid+ "." + endpres);
			if(!targetFile.exists()){
				try {
					targetFile.createNewFile();
				} catch (IOException e) {
					logger.error("Write Upload File Error:",e);
					result.setIsOk(false);
					result.setOpErrorMsg("Write Upload File Error:" + e.getMessage());
					processResult(result,response);
					return;
				}
			}
			InputStream is = null;
			OutputStream os = null;
			try {
				is = firstMpf.getInputStream();
				os = new FileOutputStream(targetFile, false);
				byte[] bytes = new byte[1024];
				int n = 0;
				 while((n = is.read(bytes)) != -1){
					 os.write(bytes, 0, n);
				 }
			} catch (IOException e) {
				logger.error("Write Upload File Error:",e);
				result.setIsOk(false);
				result.setOpErrorMsg("Write Upload File Error:" + e.getMessage());
				processResult(result,response);
				return;
			}finally{
				if(os != null){
					try {
						os.close();
					} catch (IOException e) {
						logger.warn("OutputStream close error:",e);
					}
				}
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						logger.warn("OutputStream close error:",e);
					}					
				}				
			}
			//保存资源数据
			MyFile res = new MyFile();
			res.setId(resid);
			res.setName(fileName);
			res.setFileName(firstFileMeta.getFileName());
			res.setFileSize(firstFileMeta.getFileSize());
			res.setFileType(firstFileMeta.getFileType());
			res.setPath("/" + filePath + "/" + resid+"." + endpres);
			//web端上传
			res.setUpload_type("web");
			res.setType(null);
			resourceService.add(res);
			result.setFileId(resid);
		}
		result.setFiles(files);
		result.setIsOk(true);
		processResult(result,response);
	}
	
	private void processResult(FileUploadResultDTO result,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		try {
			response.getWriter().print(toJsonString(result));
		} catch (IOException e) {
			logger.error("file upload response error.",e);
		}
	}
	
	/**
	 * 将文件上传结果转换为json格式
	 * @param result
	 * @return
	 */
	private String toJsonString(FileUploadResultDTO result){
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(result);
		} catch (JsonGenerationException e) {
			logger.error("To json string error",e);
			jsonString = toErrorString(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("To json string error",e);
			jsonString = toErrorString(e.getMessage());
		} catch (IOException e) {
			logger.error("To json string error",e);
			jsonString = toErrorString(e.getMessage());
		}
		return jsonString;
	}
	
	/**
	 * 将错误消息转换为错误串
	 * @param mes
	 * @return
	 */
	private String toErrorString(String mes){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"isOk\":\"").append("false").append("\",");
		sb.append("\"opErrorMsg\":\"").append("To json string error:").append(mes).append("\",");
		sb.append("}");
		return sb.toString();
	}	
	
	
}
