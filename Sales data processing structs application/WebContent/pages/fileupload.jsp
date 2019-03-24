<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$(".top-10-sales").hide();
		$(".records-list").hide();
		$(".nav-div").click(function() {
			$(".top-10-sales").toggle();
			$(".table-list").toggle();
			$(".records-list").toggle();
			$(".top-records").toggle();
		});
	});
</script>
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

.top-records:hover {
	background-color: #45a049;
}

.records-list:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<a class="label-logout" style="float: right;" href="logout.do">Logout</a>
	<logic:present role="customer">
		<h1>
			<bean:message key="label.common.title" />
		</h1>

		<html:form action="/Upload" method="post"
			enctype="multipart/form-data">
			<br>
			<bean:message key="label.common.file.label" /> : <html:file
				property="file" size="50"></html:file>
			<html:messages id="err_name" property="file.err">
				<div style="color: red; padding-left: 100px;">
					<bean:write name="err_name" />
				</div>
			</html:messages>
			<html:messages id="err_name" property="file.err.ext">
				<div style="color: red; padding-left: 100px;">
					<bean:write name="err_name" />
				</div>
			</html:messages>
			<html:messages id="err_name" property="file.err.size">
				<div style="color: red; padding-left: 100px;">
					<bean:write name="err_name" />
				</div>
			</html:messages>
			<html:messages id="err_name" property="file.err.exists">
				<div style="color: red; padding-left: 100px;">
					<bean:write name="err_name" />
				</div>
			</html:messages>
			<p>
				<html:submit
					style=" background-color: #4CAF50;
					  color: white;
					  padding: 12px 20px;
					  border: none;
					  border-radius: 4px;
					  cursor: pointer;">
					<bean:message key="label.common.button.submit" />
				</html:submit>
			</p>
		</html:form>
	</logic:present>
	<logic:present role="admin">
		<h2>Sales Information</h2>
		<div class="nav-div" style="text-align: center;">
			<form name="topSalesForm"
				action="${pageContext.request.contextPath}/topSales.do"
				method="POST">
				<input type="hidden" name="buttonName">
				<button class="top-records"
					style="cursor: pointer; border: 1px solid; padding: 10px;"
					type="submit" onclick="formSubmit()">Show top 10 sales</button>
				<button class="top-records"
					style="cursor: pointer; border: 1px solid; padding: 10px; background-color: lime;"
					type="submit">Complete sales list</button>
			</form>
			<script type="text/javascript">
				function formSubmit() {
					document.topSalesForm.buttonName.value = "yes";
					topSalesForm.submit();
				}
			</script>

			<!-- <button class="records-list"
				style="cursor: pointer; border: 1px solid; padding: 10px; background-color: lime;">
				Complete sales list</button> -->
		</div>
		<div style="padding: 10px; text-align: center;">
			<html:form action="/filterByDate" method="post">
				Start date: <html:text
					style="border-radius: 10px;height: 30px;border: 2px solid;"
					property="startDate" />
				End date: <html:text
					style="border-radius: 10px;height: 30px;border: 2px solid;"
					property="endDate" />
				<html:submit
					style="cursor: pointer; border: 1px solid; padding: 10px; border-radius: 10px;">Search</html:submit>
			</html:form>
		</div>
		<div style="text-align: center;">
			<html:messages id="err_name" property="date.err">
				<div style="color: red; padding-left: 100px;">
					<bean:write name="err_name" />
				</div>
			</html:messages>
			<html:messages id="err_name" property="date.err.invalid">
				<div style="color: red; padding-left: 100px;">
					<bean:write name="err_name" />
				</div>
			</html:messages>
			<html:messages id="err_name" property="date.err.invalid.format">
				<div style="color: red; padding-left: 100px;">
					<bean:write name="err_name" />
				</div>
			</html:messages>
		</div>
		<div class="table-list">
			<jsp:include page="table-list.jsp">
				<jsp:param name="pageType" value="table-list" />
			</jsp:include>
		</div>
		<%-- <div class="top-10-sales">
			<jsp:include page="toprecords.jsp">
				<jsp:param name="pageType" value="toprecords" />
			</jsp:include>
		</div> --%>
	</logic:present>
</body>
</html>