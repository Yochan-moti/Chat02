<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
	//セッションスコープからユーザ情報を取得
	User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>津田チャット</title>
</head>
<body>
<h1>津田チャット</h1>
<% if(loginUser != null) { %>
<p>ログインに成功しました</p>
<p>ようこそ <%=loginUser.getName()%>さん</p>
<p>
<a href = "/Chat03/Main">全体のチャット投稿・閲覧へ</a><br>
<a href = "/Chat03/English">英語英文科学科チャット投稿・閲覧へ</a><br>
<a href = "/Chat03/Inter">国際関係学科チャット投稿・閲覧へ</a><br>
<a href = "/Chat03/Culture">多文化・協力学科チャット投稿・閲覧へ</a><br>
<a href = "/Chat03/Math">数学科チャット投稿・閲覧へ</a><br>
<a href = "/Chat03/Info">情報科学科チャット投稿・閲覧へ</a><br>
<a href = "/Chat03/Many">総合政策学科チャット投稿・閲覧へ</a>
</p>

<p>
<a href="/Chat03/Koukan">物々交換しよう！</a>
</p>

<% } else { %>
<p>ログインに失敗しました</p>
<a href = "/Chat03/">TOPへ</a>
<% } %>
</body>
</html>