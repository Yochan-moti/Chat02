<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Mutter, java.util.List" %>
<%
User loginUser = (User) session.getAttribute("loginUser");
List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
String errorMsg = (String) request.getAttribute("errorMsg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>津田チャット-英語英文科学科-</title>
</head>
<body bgcolor="#ff99ff">

<h1>津田チャットメイン-英語英文科学科-</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中
<a href = "/Chat03/Logout">ログアウト</a>
</p>
<p><a href = "/Chat03/English">更新</a></p>
<form action = "/Chat03/English" method = "post">
<input type = "text" name = "text">
<input type = "submit" value = "つぶやく">
</form>
<% for (Mutter mutter : mutterList) { %>
	<p><%= mutter.getUserName() %>:<%=mutter.getText() %></p>
<%} %>
	
</body>
</html>