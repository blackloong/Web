package com.fq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fq.dao.DaoSupport;
import com.fq.service.BannerService;
import com.fq.util.FileUtil;
import com.fq.util.FormData;
/***
 * 首页滚动图
 * @author Admincx
 *
 */
@Service
@SuppressWarnings("unchecked")
public class BannerServiceImpl implements BannerService {
	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
	@Override
	public List<Map<String, String>> BannerList() throws Exception {
		return (List<Map<String, String>>) daoSupport.findForList("bannerMapper.bannerList", null);
	}

	@Override
	public void bannerSub(MultipartFile img,HttpServletRequest request,String uid) throws Exception {
		if(img != null && !img.isEmpty()){
			String imgsource  = FileUtil.fileUpload(img, request, "banner");
			Map<String, Object> param = new HashMap<String, Object> ();
			param.put("imgsource", imgsource);
			param.put("uid", uid);
			daoSupport.save("bannerMapper.insertBanner", param);
		}
	}

	@Override
	public void bannerDel(FormData data) throws Exception {
		daoSupport.delete("bannerMapper.delBanner", data);
	}

	@Override
	public int sqInsert(FormData formData) throws Exception {
		 
		return (Integer) daoSupport.save("bannerMapper.sqInsert", formData);
	}
	@Override
	public int squpdatastate(FormData formData) throws Exception {
		 
		return (Integer) daoSupport.update("bannerMapper.squpdatastate", formData);
	}

	@Override
	public int sqInsertCopy(FormData formData) throws Exception {
		return (Integer) daoSupport.save("bannerMapper.sqInsertCopy", formData);
	}

	@Override
	public Map<String, Object> sqcodeList(FormData formData) throws Exception {
		return (Map<String, Object>) daoSupport.findForObject("bannerMapper.sqcodeList", formData);
	}

	@Override
	public int sqInsertCopys(Map<String, Object> map) throws Exception {
		
		return (Integer) daoSupport.save("bannerMapper.sqInsertCopys", map);

	}

	@Override
	public List<Map<String, Object>> sqList(FormData formData) throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("bannerMapper.sqList", formData);
	}

	@Override
	public List<Map<String, Object>> sqDesList(FormData formData)
			throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("bannerMapper.sqDesList", formData);

	}

	@Override
	public Map<String, Object> StateList(FormData formData) throws Exception {
		return (Map<String, Object>) daoSupport.findForObject("bannerMapper.StateList", formData);
	}

	@Override
	public int squpdataSqcode(FormData formData) throws Exception {
		
		return (Integer) daoSupport.update("bannerMapper.squpdataSqcode", formData);
	}

	@Override
	public Map<String, Object> indexCount(FormData formData) throws Exception {
		return (Map<String, Object>) daoSupport.findForObject("bannerMapper.indexCount", formData);
	}

	/***
	 * 翼勋二期
	 */
	@Override
	public int creditInsert(FormData formData) throws Exception {
		return (Integer) daoSupport.save("bannerMapper.creditInsert", formData);

	}

	@Override
	public int creditupdataSqcode(FormData formData) throws Exception {
		return (Integer) daoSupport.update("bannerMapper.creditupdataSqcode", formData);

	}

	@Override
	public List<Map<String, Object>> creditList(FormData formData)
			throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("bannerMapper.creditList", formData);

	}

	@Override
	public int insertImg(FormData formData) throws Exception {
		return (Integer) daoSupport.save("bannerMapper.insertImg", formData);
	}

	@Override
	public List<Map<String, Object>> pdfList(FormData formData)
			throws Exception {
		return (List<Map<String, Object>>) daoSupport.findForList("bannerMapper.pdfList", formData);
	}
	

}
