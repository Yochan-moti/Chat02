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
<title>津田チャット-数学科-</title>
</head>
<body bgcolor="#00ffcc">

<h1>津田チャットメイン-数学科-</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中
<a href = "/Chat03/Logout">ログアウト</a>
</p>
<p><a href = "/Chat03/Math">更新</a></p>
<form action = "/Chat03/Math" method = "post">
<input type = "text" name = "text">
<input type = "submit" value = "つぶやく">
</form>
<% for (Mutter mutter : mutterList) { %>
	<p><%= mutter.getUserName() %>:<%=mutter.getText() %></p>
<%} %>
	
</body>
</html>