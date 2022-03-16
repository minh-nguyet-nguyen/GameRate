<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GameRate</title>
</head>
<body class="text-center">
	<form action="login" method="POST">
		<div align="center">
			<h2>GameRate</h2><br>
			<p>Username:</p>
			<input type="text" name="username" id="username"><br>
			<p>Password:</p>
			<input type="password" name ="password" id="password"><br><br>
			<input type="submit" id="loginButton">
			<p id="errorMessage" style="color: red">${error}</p>
		</div>
	</form>		
</body>
</html>