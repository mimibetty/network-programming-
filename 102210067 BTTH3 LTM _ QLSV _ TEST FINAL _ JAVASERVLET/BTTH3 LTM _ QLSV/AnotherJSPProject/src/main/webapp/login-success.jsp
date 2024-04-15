<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login successful</title>
	<style>
		table, td, th {
			border: 1px solid black;
		}
	</style>
</head>
<body>
	<%@page import="model.Admin"%>
	<%  
		Admin u=(Admin) request.getAttribute("user");
		if (u != null) {
			session.setAttribute("user", u);
		}
		u = (Admin) session.getAttribute("user");
		
		if (u == null) {
	%>
			<h1>Session expired! Please login again!</h1>
			<hr>
			<a href="">Back</a>
	<%
		} else {
 	%>
 			<%@ page import="model.DAO"%>
 			<%@ page import="java.util.ArrayList"%>
 			<%@page import="model.SinhVien"%>
 			<%
 				DAO dao = new DAO();
 				ArrayList<SinhVien> arr = dao.getListOfUser();
 			%>
 			<h1>Hello my friend :  <%= u.getUsername()%>!</h1>
			<hr>
			<a href="addSV.jsp">Thêm mới</a>
			<!-- Thêm form tìm kiếm -->
			<form action="SearchServlet" method="GET">
				<label for="search-department">Khoa cần tìm:</label>
				<input id="search-department" type="text" name="department">
				<button type="submit">Search</button>
			</form>
			<table>
				<thead>	<tr>
					<th>MSSV</th>
					<th>Họ Tên</th>
					<th>Tuổi </th>
					<th>Trường</th>
					<th></th>
					<th></th>
				</tr> </thead>
				<tbody>
				<%
					for (SinhVien sv : arr) {
						%>
						<tr>
							<td><%=sv.getMSSV()%></td>
							<td><%=sv.getHoTen()%></td>
							<td><%=sv.getAge()%></td>
							<td><%=sv.getUniversity()%></td>
							<td><a href="editSV.jsp?mssv=<%=sv.getMSSV()%>">Edit</a></td>
							<td><a href="deleteSV.jsp?mssv=<%=sv.getMSSV()%>">Delete</a></td>
						</tr>
						<%
					}
				%>
				</tbody>
			</table>
 	<%
		}
	%>  	
</body>
</html>