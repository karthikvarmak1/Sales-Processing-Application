package com.zoho.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class DateFilter extends ActionForm {

	private String startDate;
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "DateFilter [startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			if ("".equals(startDate) && "".equals(endDate)) {
				errors.add("date.err", new ActionMessage("error.common.date.required"));
				return errors;
			}
			long stDate = 0;
			if ((!("".equals(startDate)))) {
				stDate = new Date(startDate).getTime();
			}
			long enDate = 0;
			if ((!("".equals(endDate)))) {
				enDate = new Date(endDate).getTime();
			}

			if (stDate != 0 && enDate != 0
					&& format.parse(getStartDate()).getTime() > format.parse(getEndDate()).getTime()) {
				errors.add("date.err.invalid", new ActionMessage("error.common.date.invalid"));
				return errors;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.add("date.err.invalid.format", new ActionMessage("error.common.date.invalid.format"));
			return errors;
		}

		return errors;
	}
}
