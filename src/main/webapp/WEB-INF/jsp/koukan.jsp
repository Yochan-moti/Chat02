<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.PostKoukan,java.util.List" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
// アプリケーションスコープに保存されたつぶやきリストを取得
List<PostKoukan> koukanList =
(List<PostKoukan>) application.getAttribute("koukanList");
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>交換物投稿ページ</title>
</head>
<body>
<h1>交換物投稿ページ</h1>
<p>
<%= loginUser.getName() %>さん、ログイン中
<a href="/Chat03/Logout">ログアウト</a>
</p>
<p><a href="/Chat03/Koukan">更新</a></p><br>

<form action="/Chat03/Koukan" method="post">
交換するもの<br>
<input type="text" name="title"><br>
説明<br>
<textarea name="epi" rows="4" cols="40"></textarea><br>
<input type="submit" value="投稿する">
</form>

<% if(errorMsg != null){ %>
<p><%= errorMsg %></p>
<% } %>
交換物一覧<br>
ユーザー/出品物
<% for(PostKoukan koukan : koukanList){%>
<p>
<b><%=koukan.getUserName() %>/<%=koukan.getItem()%></b><br>
説明<br>
<%=koukan.getEpi() %>
</p>
<% } %>

</body>
</html>