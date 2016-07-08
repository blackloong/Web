package com.fq.util.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件上传工具类
 * @author lp
 *
 */
public class UploadUitl {
	
	public static final String SUFFIX = ".html";

	/**
	 * 上传文件
	 * @param file
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static String upload (MultipartFile file,HttpServletRequest request,String path)throws Exception{
		    if(file == null){
			   return "";	
		    }
		    String filename = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			Random r = new Random();
			filename = r.nextInt(20)+filename;
//			path ="/"+path;
			String realPath = request.getSession().getServletContext().getRealPath(path);
			String nowFile =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			realPath = realPath+"/"+nowFile;
			filename = filename+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath,filename));
			return path+"/"+nowFile+"/"+filename;
	}
	
	
	public static void main(String[] args) throws Exception {
		//imgUpload();
		//System.err.println(htmlUpload(null, "<h1>HTML 模板</h2>", "shopimage"));
	}
}
