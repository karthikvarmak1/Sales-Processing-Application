package com.zoho.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zoho.beans.FileUploadForm;
import com.zoho.utils.CommonUtils;

public class SalesController extends Action {
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws IOException, ClassNotFoundException, SQLException, InterruptedException {

		FileUploadForm fileUploadForm = (FileUploadForm) form;
		FormFile formFile = fileUploadForm.getFile();

		String filePath = new CommonUtils().getPath();
		System.out.println(filePath);

		File folder = new File(filePath);

		if (!folder.exists()) {
			folder.mkdir();
		}

		String fileName = formFile.getFileName();

		if (!("".equals(fileName))) {
			File newFile = new File(filePath, fileName);

			if (!newFile.exists()) {
				FileOutputStream outputStream = new FileOutputStream(newFile);
				outputStream.write(formFile.getFileData());
				outputStream.flush();
				outputStream.close();
			}
		}

		req.setAttribute("uploaded", "File has been uploaded successfully");

		return map.findForward("uploaded");
	}
}
