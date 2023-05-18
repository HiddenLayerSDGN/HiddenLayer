package com.hiddenlayer.dalabel.fileupload;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class FileUpload {
	public String profileUpload(HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("resources/image/profile");
		MultipartRequest mr = null;

		try {
			mr = new MultipartRequest(req, path, 50 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			// 파일 용량 안맞으면
			e.printStackTrace();
			return null;
		}

		try {
			String fileName = mr.getParameter("fileName");
			return fileName;
		} catch (Exception e) {
			return null;
		}
	}
	
	public String labellingFileUpload(HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("resources/image/labellingFile");
		MultipartRequest mr = null;
		
		try {
			mr = new MultipartRequest(req, path, 2 * 1024 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			// 파일 용량 안맞으면
			e.printStackTrace();
			return null;
		}
		
		try {
			String fileName = mr.getParameter("fileName");
			return fileName;
		} catch (Exception e) {
			return null;
		}
	}
}