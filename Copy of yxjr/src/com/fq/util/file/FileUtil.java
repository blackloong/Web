package com.fq.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fq.util.DateUtil;

public class FileUtil {
	/**
	 * 文件上传
	 * (本地储存)
	 * @param file
	 * @param request
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String fileUpload(MultipartFile file,
			HttpServletRequest request, String path) throws Exception {
		String filename = DateUtil.getTimeStr();
		Random r = new Random();
		filename = r.nextInt(2000) + filename;
		if (file.isEmpty())
			return "";
		String realPath = request.getSession().getServletContext()
				.getRealPath(path);
		String nowFile = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		realPath = realPath + "/" + nowFile;
		filename = filename
				+ file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf("."),
						file.getOriginalFilename().length());
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
				realPath, filename));
		return path + "/" + nowFile + "/" + filename;
	}

	/**
	 * 删除文件
	 * 
	 * @param session
	 * @param filename
	 */
	public static void delPic(String realPath, String filename) {
		if (filename != null) {
			if (filename.endsWith("/"))
				return;
			if (!filename.equals("")) {
				filename = filename.trim().replace("/", "\\");
				if (filename != null) {
					File file = new File(realPath, filename);
					if (file.exists()) {
						file.delete();
					}
				}
			}
		}
	}

	public HttpServletResponse download(String path,
			HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			String ext = filename.substring(filename.lastIndexOf(".") + 1)
					.toUpperCase();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public static String upfile(String filename, Object files,
			HttpServletResponse response) {
		try {

			if (filename != null) {
				OutputStream fos = null;
				try {
					byte[] b = (byte[]) files;
					response.setContentType("application/octet-stream");
					response.setHeader("Content-disposition", "filename="
							+ URLEncoder.encode(filename + "", "UTF-8"));
					response.addHeader("Content-Length", "" + b.length);
					fos =new BufferedOutputStream(response.getOutputStream());
					fos.write(b);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (fos != null)
						fos.flush();
					if (fos != null)
						fos.close();
				}
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
