package com.fq.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	/**
	 * 文件上传
	 * @param file
	 * @param request
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String fileUpload (MultipartFile file,HttpServletRequest request,String path)throws Exception{
		String filename = DateUtil.getTimeStr();
		Random r = new Random();
		filename = r.nextInt(20)+filename;
		if(file.isEmpty())
			return "";
		String realPath = request.getSession().getServletContext().getRealPath(path);
		String nowFile =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		realPath = realPath+"/"+nowFile;
		filename = filename+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath,filename));
		return path+"/"+nowFile+"/"+filename;
	}
	/**
	 * 删除文件
	 * @param session
	 * @param filename
	 */
	public static void delPic (String realPath,String filename ){
		if(filename != null ){
			if(filename.endsWith("/"))
				return ;
			if(!filename.equals("")){
				filename = filename.trim().replace("/", "\\");
				if(filename != null){
					File file = new File(realPath,filename);
					if(file.exists()){
						file.delete();
					}
				}
			}
		}
	}
	
	 
	  
}
