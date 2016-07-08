package com.fq.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.fq.util.FormData;

/***
 * 首页滚动图接口
 * 
 * @author Admincx
 * 
 */
public interface BannerService {
	List<Map<String, String>> BannerList() throws Exception;
	
	void bannerSub (MultipartFile img,HttpServletRequest request,String uid) throws Exception;
	
	void bannerDel (FormData data) throws Exception;
	
	int sqInsert(FormData formData) throws Exception;
	
	int squpdatastate (FormData formData) throws Exception;
	
	int sqInsertCopy(FormData formData) throws Exception;//添加申请流水
	
	Map<String, Object> sqcodeList (FormData formData)throws Exception ;//根据申请编号查询 订单 
	
	int sqInsertCopys( Map<String, Object> map) throws Exception;//添加申请流水 方法 2

	List<Map<String, Object>> sqList(FormData formData) throws Exception;//进度查询
	List<Map<String, Object>> sqDesList(FormData formData) throws Exception;//进度详情
	Map<String, Object>  StateList (FormData formData)throws Exception ;//根据 审核状态 ，查询 状态 提示 
	int squpdataSqcode (FormData formData) throws Exception;//更新申请编号
	
	Map<String, Object> indexCount(FormData formData) throws Exception;//咨询人数，完成人数统计 

   /*翼勋二期项目**/
	int creditInsert(FormData formData) throws Exception;//授信申请添加
	int creditupdataSqcode (FormData formData) throws Exception;//更新授信申请状态 
	List<Map<String, Object>> creditList(FormData formData) throws Exception;//授信订单查询
	
	int insertImg(FormData formData) throws Exception;//商户添加合同
	
	List<Map<String, Object>> pdfList(FormData formData) throws Exception;//pdf 文件List


}
