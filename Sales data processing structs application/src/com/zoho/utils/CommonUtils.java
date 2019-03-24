package com.zoho.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CommonUtils {
	public String getPath() throws UnsupportedEncodingException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/WEB-INF/classes/");

		fullPath = pathArr[0];
		String filePath = fullPath + "/upload";
		System.out.println("utils: " + fullPath);
		return filePath;
	}
}
