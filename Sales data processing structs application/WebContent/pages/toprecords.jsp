<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.zoho.dao.SalesDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Top records by sales</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Using the jquery dataTables CSS CDN -->
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
<!-- Using the jquery dataTables API CDN -->
<script type="text/javascript" charset="utf8"
	src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

.label-logout {
	cursor: pointer;
}

.label-logout:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<a class="label-logout" style="float: right;" href="logout.do">Logout</a>
	<h2>Top sales</h2>
	<div class="nav-div" style="text-align: center;">
		<form name="topSalesForm"
			action="${pageContext.request.contextPath}/UploadPage.do"
			method="POST">
			<input type="hidden" name="buttonName">
			<button class="top-records"
					style="cursor: pointer; border: 1px solid; padding: 10px; background-color: lime;"
					type="submit">Show top 10 sales</button>
			<button class="top-records"
				style="cursor: pointer; border: 1px solid; padding: 10px;"
				type="submit" onclick="formSubmit()">Complete Sales List</button>
		</form>
		<script type="text/javascript">
			function formSubmit() {
				document.topSalesForm.buttonName.value = "yes";
				topSalesForm.submit();
			}
		</script>
	</div>
	<table data-order='[[ 2, "desc" ]]' id="sales_table2" class="display">
		<thead>
			<tr>
				<th>Area Code</th>
				<th>Date (dd/MM/yyyy)</th>
				<th>Sales</th>
			</tr>
		</thead>
		<tbody>
			<%
				try {
					// String requestType = request.getParameter("pageType");
					ResultSet resultSet = null;
					// System.out.println(requestType);
					resultSet = new SalesDAO().fetchTopSales();
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
			$('#sales_table2').DataTable({
				/* 'ajax': {
			        'url': "https://api.myjson.com/bins/2bsi1",
		      	  	'dataSrc': 'history'
			    },
			    'autoWidth': false,
		  	  	'lengthChange': false,
		    	'ordering': false,
		    	'pageLength': 50 */
			});//Basic DataTable API instance 
		});
	</script>
</body>
</html>