package com.wzxy.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadFileUtils {
	
	/**
	 * 读出上传的TXT内容
	 * @param request
	 * @return
	 */
	public static List<String> readUploadTxtFile(HttpServletRequest request){
		MultipartHttpServletRequest requestUpload = (MultipartHttpServletRequest) request;
		MultipartFile file = requestUpload.getFile("file");
		if (file == null){
			System.out.println("fuck off");
			return null;
		}
		String rec = null;// 一行  
		List<String> list = new LinkedList<String>();
		try {
			InputStream in = file.getInputStream();
			InputStreamReader fr = new InputStreamReader(in, "gbk");
			BufferedReader br = new BufferedReader(fr);
			while((rec = br.readLine()) != null){
				//把刚获取的列存入list
			    list.add(rec);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

}
