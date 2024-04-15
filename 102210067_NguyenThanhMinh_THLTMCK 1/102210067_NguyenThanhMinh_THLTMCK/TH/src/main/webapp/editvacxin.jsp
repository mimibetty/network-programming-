<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa vacxin</title>
</head>
<body>
	<h2>Sửa vacxin</h2>
	<form action="update" method="post">
		<label for="mavacxin">Mã vacxin:</label>
		<input type="text" id="mavacxin" name="mavacxin" value="${vacxin.maVacxin}" hidden required> <br>
		
		<label for="tenvacxin">Tên vacxin:</label>
		<input type="text" id="tenvacxin" name="tenvacxin" value="${vacxin.tenVacxin}" required> <br>
		
		<label for="somui">Số mũi:</label>
		<input type="text" id="somui" name="somui" value="${vacxin.soMui}" required> <br>
		
		<label for="mota">Mô tả:</label>
		<input type="text" id="mota" name="mota" value="${vacxin.moTa}" required> <br>
		
		<label for="giavacxin">Giá vacxin:</label>
		<input type="text" id="giavacxin" name="giavacxin" value="${vacxin.giaVacxin}" required> <br>
		
		<label for="tenhangsx">Tên hãng SX:</label>
		<input type="text" id="tenhangsx" name="tenhangsx" value="${vacxin.tenHangSX}" required> <br>
		
		<input type="submit" value="OK">
	</form>
</body>
</html>