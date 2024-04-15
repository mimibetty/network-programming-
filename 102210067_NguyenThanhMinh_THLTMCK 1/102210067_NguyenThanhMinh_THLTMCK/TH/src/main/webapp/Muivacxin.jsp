<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách vacxin</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f0f0f0;
}
.container {
    width: 80%;
    margin: auto;
    background-color: #ffffff;
    padding: 20px;
    box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
}
table {
    width: 100%;
    border-collapse: collapse;
}
table, th, td {
    border: 1px solid #dddddd;
    padding: 10px;
    text-align: left;
}
th {
    background-color: #4CAF50;
    color: white;
}
a {
    color: #0066cc;
    text-decoration: none;
}
a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>

<div class="container">
    <h2>Danh sách vacxin</h2>
    <table>
        <tr>
            <th>Mã vacxin</th>
            <th>Tên vacxin</th>
            <th>Số mũi</th>
        </tr>
        <c:forEach items="${vacxins}" var="vacxin">
            <tr>
                <td>${vacxin.maVacxin}</td>
                <td>${vacxin.tenVacxin}</td>
                <td>${vacxin.soMui}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
</div>

</body>
</html>