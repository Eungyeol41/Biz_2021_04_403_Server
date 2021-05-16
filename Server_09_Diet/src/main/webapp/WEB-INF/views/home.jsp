<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1 {
		background-color: #3EADBB;
		color : #fff ;
		padding: 1.2rem;
		text-align: center;
	}
	
	a {
		text-decoration: none;
	}
	
	/*
	CSS 3를 사용한 interactive 스타일 지정
	동적(Dynamic) 스타일 지정
	*/
	a:hover {
		color: 	#FF4500;
		background-color: #ddd ;
		text-decoration: underline;
	}
	
</style>
</head>
<body>
	<h1>다이어트를 도와줘</h1>
	<%-- /diet/food/search --%>
	<a href="${pageContext.request.contextPath}/food/search">섭취정보 등록</a>
</body>
</html>