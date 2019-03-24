package com.zoho.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zoho.beans.DateFilter;
import com.zoho.dao.SalesDAO;

public class FilterController extends Action {
	public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		DateFilter dateFilter = (DateFilter) form;
		System.out.println(dateFilter);
		SalesDAO salesDAO =  new SalesDAO();
		req.setAttribute("filteredRecords",salesDAO.filterRecordsBasedOnDates(dateFilter));
		return map.findForward("filtered");
	}
}
