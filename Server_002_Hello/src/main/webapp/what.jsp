<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = request.getParameter("name");
	int num = (int)(Math.random() * 100);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>반갑습니다 <%= name %> 님</h1>
	<h3>생성된 수는 ? <%= num %></h3>
	<p>=========================</p>
	<p>7 구구단 </p>
	<p>-------------------------</p>
	<%
	for(int i = 0; i < 9; i++) {
		int num1 = i + 1;
		int dan = 7;
		int times = dan * num1;
	%>
		<p><%= dan %> x <%= num1 %> = <%= times %>
	<%
	}
	%>
</body>
</html>