<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Welcome</title>
	<style>
		* {
			font-family: sans-serif;
			box-sizing: border-box;
		}
		body {
			display: flex;
			flex-direction: column;
			align-items: center;
			justify-content: center;
			height: 100vh;
			margin: 0;
			background-color: #f0f0f0;
			color: #333;
		}
		h1 {
			margin-bottom: 1em;
		}
		form {
			background-color: #fff;
			padding: 1em;
			border-radius: 8px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		}
		label {
			display: block;
			margin-bottom: 0.5em;
		}
		input[type="text"], input[type="password"] {
			width: 100%;
			padding: 0.5em;
			border: 1px solid #ddd;
			border-radius: 4px;
			margin-bottom: 1em;
		}
		input[type="submit"], input[type="reset"] {
			padding: 0.5em 1em;
			border: none;
			background-color: #007BFF;
			color: #fff;
			border-radius: 4px;
			cursor: pointer;
			transition: background-color 0.3s ease;
			margin-right: 0.5em;
		}
		input[type="submit"]:hover, input[type="reset"]:hover {
			background-color: #0056b3;
		}
	</style>
</head>
<body>
	<h1>Đăng nhập hệ thống</h1>
	<hr>
	<form action="CheckLoginServlet" method="POST">
		<label for="input-name">Tên đăng nhập:</label>
		<input id="input-name" type="text" name="username"><br>
		
		<label for="input-passwd">Mật khẩu:</label>
		<input id="input-passwd" type="password" name="password"><br>
		
		<input type="submit" value="Đăng nhập">
		<input type="reset" value="Hủy">		
	</form>
</body>
</html>