<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>
<c:set value="${pageContext.request.contextPath }" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--
	Chrome Browser의 캐시 때문에 css, jsp 등 외부 파일을 변경해도 적용이 안 되는 경우가 있다
	?ver=숫자 값을 변경하면 Chrome Browser가 파일이 변경된 것으로 인식하여 새로고침을 해준다.
--%>
<link rel="stylesheet" href="/diet/CSS/home.css?ver=2021-05-18-001" />

<!-- 외부의 CSS file 가져오기 -->
<%-- 
	외부의 CSS file 가져오기
	webapp 폴더는 프로젝트의 외부에서 접근할 때 root Folder로 인식된다
	슬래시(/)로 시작되는 경로는 webapp 폴더로 인식된다
	
	그런데 지금 프로젝트에서 슬래시(/)로 접근하는 모든 요청은 HomeController가 catcher하도록 만들어져 있기 때문에 어떠한 파일로 연결을 할 수 없다.
 --%>

</head>
<body>
	<h1>다이어트를 도와줘</h1>
	<%-- /diet/food/search --%>
	<a href="${pageContext.request.contextPath}/food/search">섭취정보 등록</a>
	<div>
        <form>
            <label>날짜 </label><input name="mf_date">    
        </form>
    </div>
	<!-- JSP 파일에서 다른 JSP파일 연결하기 -->
	<%@ include file="/WEB-INF/views/list.jsp" %>
</body>
</html>