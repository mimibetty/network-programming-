<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Sinh Vien</title>
</head>
<body>
	<h1>Thêm mới sinh vien</h1>
	<form action="AddServlet" method="POST">
		Ma SV: <input type="text" name="mssv">
		Ho Ten: <input type="text" name="hoten">
		Tuoi: <input type="text" name="tuoi">
		Truong: <input type="text" name="truong">
		<input type="submit" value="Lưu lại">
		<input type="button" value="Quay lại" onClick="history.back()">
	</form>
</body>
</html>