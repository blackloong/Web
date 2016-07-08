package com.fq.controller.appinterface;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fq.util.file.FileUtil;


/**
 * 
 * @author P
 * @date d2015-4-1
 */
@Controller
public class AppFileController extends ResponseBaseController {

	@RequestMapping(value="appFileUpload",method=RequestMethod.POST,produces={"application/json"})
	@ResponseBody
	public Map<String, Object> uploadFile(HttpServletRequest request)throws Exception{
		Map<String, Object> res = new HashMap<String, Object> ();
		if(ServletFileUpload.isMultipartContent(request)){
			MultipartHttpServletRequest r = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = r.getFileMap();
			String result = "";
			for (String key : files.keySet()){
				String fileName = FileUtil.fileUpload(files.get(key), request, "upload");
				result += fileName +",";
			}
			if(result.length()>0){
				result = result.substring(0, result.length()-1);
			}
			res.put("fileName", result);
			res.put("code", "00");
		}else{
			res.put("code", "-02");
		}
		return res ;
	}
}
