<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login Page</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], input[type=password] {
	width: 50%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}
</style>
</head>

<body>
	<h1>Login Page</h1>
	<div class="container">
		<form name="loginForm" method="POST" action="j_security_check">
			<p>
				<label for="fname">User Name</label> <input type="text"
					name="j_username" size="20" />
			</p>
			<p>
				<label for="pwd">Password &nbsp;</label> <input type="password"
					size="20" name="j_password" />
			</p>
			<p>
				<input type="submit" value="Login" />
			</p>
		</form>
	</div>
</body>
</html>