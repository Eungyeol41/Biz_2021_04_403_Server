<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<h1>반갑습니다</h1>
	<form method="POST">
		<p><input name="t_name">
		<p><input name="t_tel">
		<p><input name="t_age">
		<p><button>전송</button>
	</form>
	<p>${MY.t_name}
	<p>${MY.t_tel}
	<p>${MY.t_age}
</body>
</html>