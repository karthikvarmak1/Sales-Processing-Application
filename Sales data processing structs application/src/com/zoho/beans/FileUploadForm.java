package com.zoho.beans;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

public class FileUploadForm extends ActionForm {
	
	private static final long serialVersionUID = 3391843604960516020L;
	private FormFile file;

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		if (file.getFileSize() == 0) {
			errors.add("file.err", new ActionMessage("error.common.file.required"));
			return errors;
		}
		System.out.println("karthik : " + file.getContentType());

		if (!("application/octet-stream".equals(file.getContentType())
				|| "application/json".equals(file.getContentType()) || "application/vnd.ms-excel".equals(file.getContentType()))) {
			errors.add("file.err.ext", new ActionMessage("error.common.file.csvjson.only"));
			return errors;
		}

		System.out.println(getFile().getFileSize());

		if (file.getFileSize() > 102400) {
			errors.add("file.err.size", new ActionMessage("error.common.file.size.limit", 102400));
			return errors;
		}

		String filePath = getServlet().getServletContext().getRealPath("/") + "upload";
		System.out.println(filePath);
		// System.out.println(getServlet().getServletContext().getContextPath());

		String fileName = getFile().getFileName();

		if (!("".equals(fileName))) {
			File newFile = new File(filePath, fileName);

			if (newFile.exists()) {
				errors.add("file.err.exists", new ActionMessage("error.common.file.exits"));
				return errors;
			}
		}
		return errors;
	}
}