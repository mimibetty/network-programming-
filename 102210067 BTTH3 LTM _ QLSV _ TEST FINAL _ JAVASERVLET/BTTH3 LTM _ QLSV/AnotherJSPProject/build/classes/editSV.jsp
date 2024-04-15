
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Sinh Vien</title>
</head>
<body>
<%@ page import="model.DAO" %>
<%@ page import="model.SinhVien" %>
<%! SinhVien sv = null; %>
<%
	System.out.println("im here");
	try {
		String mssv = (request.getParameter("mssv"));
		DAO dao = new DAO();
		sv = dao.getSinhVien(mssv);
	} catch (Exception e) {}
%>
<h1>Sua sinh vien <%= sv.getHoTen()%></h1>
<form action="EditServlet" method="POST">
	Ma SV: <input type="text" value="<%=sv.getMSSV() %>" name="mssv" readonly>
	Ho Ten: <input type="text" value="<%=sv.getHoTen() %>" name="hoten">
	Tuoi: <input type="text" value="<%=sv.getAge() %>" name="tuoi">
	Truong: <input type="text" value="<%=sv.getUniversity() %>" name="truong">
	<input type="submit" value="Lưu lại">
	<input type="button" value="Quay lại" onClick="history.back()">
</form>
</body>
</html>