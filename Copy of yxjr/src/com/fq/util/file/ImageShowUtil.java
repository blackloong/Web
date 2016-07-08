package com.fq.util.file;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fq.controller.base.BaseController;
import com.fq.util.db.DBUtil;


@Controller
public class ImageShowUtil  extends BaseController{

	

	
	
	@RequestMapping(value = "showImages")
	public  String showImages(HttpServletResponse response){
		
		try {
		String sql=getsql(this.getFormData().getString("type"));
			List<Map<String, Object>> list =  DBUtil.query2List(sql);
			if(list!=null&&list.size()>0){
				Map<String,Object> map=list.get(0);
				byte[] b=(byte[]) map.get(this.getFormData().getString("type"));
				response.setContentType("image/jpeg");                  
				response.setContentLength(b.length);        
				/*response.setHeader("Content-Disposition", "attachment;"
						+ " filename="
						+ new String(fileName.getBytes(), "ISO-8859-1"));*/
				ServletOutputStream ss = response.getOutputStream(); 
				
				ss.write(b);       
				ss.flush();
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "images_table")
	public  String images_table(HttpServletResponse response){
		
		try {
			String table =this.getFormData().getString("table");
			String value=this.getFormData().getString("value");
			String id=this.getFormData().getString("id");
		    String sql="select "+value+" from "+table+" where id="+id;//getsql(this.getFormData().getString("type"));
			
			List<Map<String, Object>> list =  DBUtil.query2List(sql);
			if(list!=null&&list.size()>0){
				Map<String,Object> map=list.get(0);
				byte[] b=(byte[]) map.get(this.getFormData().getString("value"));
				response.setContentType("image/jpeg");                  
				response.setContentLength(b.length);        
				/*response.setHeader("Content-Disposition", "attachment;"
						+ " filename="
						+ new String(fileName.getBytes(), "ISO-8859-1"));*/
				ServletOutputStream ss = response.getOutputStream(); 
				
				ss.write(b);       
				ss.flush();
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "upfile")
	public  String upfile(HttpServletResponse response){
		
		try {
		String sql=getsql(this.getFormData().getString("type"));
			List<Map<String, Object>> list =  DBUtil.query2List(sql);
			if(list!=null&&list.size()>0){
				Map<String,Object> map=list.get(0);
				FileUtil.upfile(map.get("audit_file_value")+"",map.get("audit_file"), response);
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "upfile_table")
	public  String upfile_table(HttpServletResponse response){
		
		try {
			
			String table =this.getFormData().getString("table");
			String value=this.getFormData().getString("value");
			String name=this.getFormData().getString("name");
			String id=this.getFormData().getString("id");
		    String sql="select "+name+","+value+" from "+table+" where id="+id;//getsql(this.getFormData().getString("type"));
			List<Map<String, Object>> list =  DBUtil.query2List(sql);
			if(list!=null&&list.size()>0){
				Map<String,Object> map=list.get(0);
				FileUtil.upfile(map.get(this.getFormData().getString("name"))+"",map.get(this.getFormData().getString("value")), response);
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "up_table")
	@ResponseBody
	public  String up_table(HttpServletResponse response){
		
		try {
			 
			String table=this.getFormData().getString("table");  
			int s=DBUtil.jdbc(table);
			return s+"";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public  String getsql(String type){
		
		if("logo".equals(type)){
			return " SELECT logo FROM tb_about_notice WHERE id="+getFormData().get("id");
		}else if("logoone".equals(type)){
			return " SELECT logoone FROM tb_product WHERE id="+getFormData().get("id");
		}else if("logotwo".equals(type)){
			return " SELECT logotwo FROM tb_product WHERE id="+getFormData().get("id");
		}else if("loan_upfile".equals(type)){
			return "SELECT audit_file,audit_file_value FROM tb_loan WHERE id="+getFormData().get("id");
		}
		
		
		return null;
	}
}
