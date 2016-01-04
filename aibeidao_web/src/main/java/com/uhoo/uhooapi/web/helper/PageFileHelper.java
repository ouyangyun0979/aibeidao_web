package com.uhoo.uhooapi.web.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uhoo.uhooapi.app.api.conf.MyConfig;
import com.uhoo.uhooapi.app.api.util.GUIDHexGenerator;

/**
 * 
* @ClassName: FileUtils
* @Description: 存取文件帮助类
* @author yang.yun
* @date 2014年6月20日 下午3:31:22
*
 */
public class PageFileHelper {
	
	public static final Logger logger = LoggerFactory.getLogger(PageFileHelper.class);
	
	public static final String rootDir = MyConfig.get("file.root.dir");
	
	public static void updatePageFile(String fileid , String content){
		if(content != null){
			String rootpath = getRootDir();
			OutputStream fos = null;
			try {
				File targetFile = new File(rootpath + fileid);
				fos = new FileOutputStream(targetFile,false);
				Writer os = new OutputStreamWriter(fos, "UTF-8");
				os.write(content);
	            os.flush();  
	            os.close();
			} catch (FileNotFoundException e) {
				logger.error("Save file error",e);
			} catch (UnsupportedEncodingException e) {
				logger.error("Save file error",e);
			} catch (IOException e) {
				logger.error("Save file error",e);
			}finally{
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e) {
						logger.warn("Close streem error",e);
					}
				}
			}
		}
	}
	
	/**
	 * 将页面文件String内容写入文件
	 * @param filepath
	 * @param content
	 */
	public static String writePageFile(String content){
		String fileid = null;
		if(content != null){
			String rootpath = getRootDir();
			OutputStream fos = null;
			try {
				fileid = GUIDHexGenerator.getInstance().generateId();
				File targetFile = new File(rootpath + fileid);
				fos = new FileOutputStream(targetFile,false);
				Writer os = new OutputStreamWriter(fos, "UTF-8");
				os.write(content);
	            os.flush();  
	            os.close();
			} catch (FileNotFoundException e) {
				logger.error("Save file error",e);
			} catch (UnsupportedEncodingException e) {
				logger.error("Save file error",e);
			} catch (IOException e) {
				logger.error("Save file error",e);
			}finally{
				if(fos != null){
					try {
						fos.close();
					} catch (IOException e) {
						logger.warn("Close streem error",e);
					}
				}
			}
		}
		return fileid;
	}
	
	/**
	 * 读取页面文件为String
	 * @param fileid
	 * @return
	 */
	public static String readPageFile(String fileid){
		File targetFile = new File(getRootDir() + fileid);
		FileReader fr = null;
		BufferedReader br = null;
		StringBuffer buffer = new StringBuffer();
		String line = "";
		try {
			fr = new FileReader(targetFile);
			br = new BufferedReader(fr);
			while((line = br.readLine()) !=null){
				buffer.append(line);
			}
			return buffer.toString();
		} catch (FileNotFoundException e) {
			logger.error("Save file error",e);
		} catch (IOException e) {
			logger.error("Save file error",e);
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					logger.warn("Close streem error",e);
				}
			}
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					logger.warn("Close streem error",e);
				}
			}
		}
		return null;
	}
	
	/**
	 * 删除页面文件
	 * @param fileid
	 */
	public static void deletePageFile(String fileid){
		File targetFile = new File(getRootDir() + fileid);
		if(targetFile.exists()){
			targetFile.delete();
		}
	}
	
	/**
	 * 获得文件跟目录
	 * @return
	 */
	public static String getRootDir(){
		String rootDirs = null;
		if(rootDir.endsWith("/")){
			File fileDir = new File(rootDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			rootDirs = rootDir;
		}else{
			rootDirs = rootDir + "/";
		}
		return rootDirs;
	}

}
