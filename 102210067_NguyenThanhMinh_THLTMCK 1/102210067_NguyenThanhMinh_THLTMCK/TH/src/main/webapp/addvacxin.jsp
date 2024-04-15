<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm vacxin</title>
</head>
<body>
	<h2>Thêm vacxin</h2>
	<form action="insert" method="post">
		<label for="mavacxin">Mã vacxin:</label>
		<input type="text" id="mavacxin" name="mavacxin" required> <br>
		
		<label for="tenvacxin">Tên vacxin:</label>
		<input type="text" id="tenvacxin" name="tenvacxin" required> <br>
		
		<label for="somui">Số mũi:</label>
		<input type="text" id="somui" name="somui" required> <br>
		
		<label for="mota">Mô tả:</label>
		<input type="text" id="mota" name="mota" required> <br>
		
		<label for="giavacxin">Giá vacxin:</label>
		<input type="text" id="giavacxin" name="giavacxin" required> <br>
		
		<label for="tenhangsx">Tên hãng SX:</label>
		<input type="text" id="tenhangsx" name="tenhangsx" required> <br>
		
		<input type="submit" value="OK">
	</form>
</body>
</html>