package com.zoho.controller;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zoho.dao.SalesDAO;

public class MyServletContextListener implements ServletContextListener  {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Start of the listener");
		SalesListener listener = new SalesListener();
		/*SalesDAO salesDAO = new SalesDAO();
		salesDAO.fetchTopSales();*/
		/*try {
			listener.insertMockData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Thread thread = new Thread(listener);
		thread.start();
	}
}
