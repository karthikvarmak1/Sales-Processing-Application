<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.zoho.dao.SalesDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Table list</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Using the jquery dataTables CSS CDN -->
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
<!-- Using the jquery dataTables API CDN -->
<script type="text/javascript" charset="utf8"
	src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
	
</head>
<body>
	<table id="sales_table1" class="display">
		<thead>
			<tr>
				<th>Area Code</th>
				<th>Date(dd/MM/yyyy)</th>
				<th>Sales</th>
			</tr>
		</thead>
		<tbody>
			<%
				try {
					String requestType = request.getParameter("pageType");
					ResultSet resultSet = null;
					if(request.getAttribute("filteredRecords") != null) {
						resultSet = (ResultSet) request.getAttribute("filteredRecords");
					} else {
						resultSet = new SalesDAO().fetchSales();
					}
					while (resultSet.next()) {
			%>
			<tr>
				<td><%=resultSet.getString("areacode")%></td>
				<td><%=new SimpleDateFormat("dd/MM/yyyy")
							.format(new Date(Long.parseLong(resultSet.getString("saledate"))))%></td>
				<td><%=resultSet.getInt("sales")%></td>
			</tr>
			<%
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
		</tbody>
	</table>
	<!-- JQuery code to initializate the DataTables API -->
	<script>
		$(document).ready(function() {
			$('#sales_table1').DataTable({
				responsive: true,
			});//Basic DataTable API instance 
		});
	</script>
</body>
</html>