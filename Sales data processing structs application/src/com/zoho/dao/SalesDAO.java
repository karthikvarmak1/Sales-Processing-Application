package com.zoho.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zoho.beans.DateFilter;

public class SalesDAO {

	public Statement getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"varma");
		return connection.createStatement();
	}

	public ResultSet fetchSales() {
		ResultSet resultSet = null;
		try {
			Statement statement = getConnection();
			String sql = "select * from supermarket_salesdata";
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	public ResultSet fetchTopSales() {
		ResultSet resultSet = null;
		try {
			Statement statement = getConnection();
			String sql = "select * from supermarket_salesdata order by sales desc limit 10";
			resultSet = statement.executeQuery(sql);
			/*
			 * while(resultSet.next()) {
			 * System.out.print(resultSet.getString("areacode") + " ");
			 * System.out.print(resultSet.getString("saledate") + " ");
			 * System.out.println(resultSet.getString("sales") + " "); }
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	public ResultSet filterRecordsBasedOnDates(DateFilter dateFilter) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			String sql = "";
			if ("".equals(dateFilter.getStartDate())) {
				sql = "select * from supermarket_salesdata where saledate<="
						+ format.parse(dateFilter.getEndDate()).getTime();
			} else if ("".equals(dateFilter.getEndDate())) {
				sql = "select * from supermarket_salesdata where saledate>="
						+ format.parse(dateFilter.getStartDate()).getTime();
			} else {
				sql = "select * from supermarket_salesdata where saledate>="
						+ format.parse(dateFilter.getStartDate()).getTime() + " and saledate<="
						+ format.parse(dateFilter.getEndDate()).getTime();
			}
			System.out.println(sql);

			Statement statement = getConnection();
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

}
