<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>    
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식품 정보 검색</title>
<style>

	/* 모든 tag에 적용하는 style */
	* {
		box-sizing: border-box;
	}
	
	/*
	tag에 padding을 설정하면 실제 width보다 더 커지는 현상이 발생한다
	이러한 현상은 UI 화면을 눈에 거슬리게 하는 결과가 된다.
	padding 주었을 때 좌, 우의 padding을 밖으로 보내지 않고 내부로 흡수하는 옵션
	
	이 설정을 style의 맨 상단에 설정하면 padding으로 인한 Layout의 흐트러짐을 막을 수 있다
	*/
	/* h1, form, table tag에 공통된 style 지정 */
	h1, form, table {
		width:80%;
		margin:0px auto;	
	}
	
	h1 {
		background-color: #3EADBB;
		padding : 40px 0px; 
		align-content: center ;
		text-align: center ;
		color: white;
	}
	
	form {
		padding-left : 10px;
	}
	
	table {
		border-top : 1px solid #ccc;
		border-collapse: collapse;
	}	
	
	th,td {	
		padding : 15px 10px; 
		border-top : 1px solid #ccc;
		text-align: center
	}
	
	tr:nth-child(odd) {
		background-color: #eee;
	}
	
	input {	
		width : 40%;
		border : 0.5px solid #ccc;
		border-radius : 5px ;
		margin : 8px;
		padding: 5px;
	}
	
	/*
		table의 어떤 row에 마우스가 올라가면 바탕색을 #aaa로 마우스 커서 모양을 손모양으로 바꾼다 
	*/
	tr:hover {
		background-color: #A7E2DD;
		cursor: pointer; 
		color:  #296cff;
	}
	
	a {
		text-decoration: none ;
		/*style의 상속*/
		/* a태그를 감싸고 있는 태그(td)의 색이 바뀌면 같이 바뀐다. */
		color : inherit ;
	}
	
	body { font-family : "Noto Sans CJK KR Light", "돋움", dotum, arial,"굴림", gulim}
</style>
</head>
<body>
	<h1>무엇을 먹을까</h1>
	<%--
	form의 method를 POST로 지정하면 Controller의 doPost() 함수가 받아서 처리를 한다
	
	보여지는 search.jsp 파일을 요청한 요청 주소는 /food/search이다.
	
	form에 action을 자동으로 지정하지 않으면 action="/form/search"가 자동으로 지정된다
	 --%>
	<form method="POST">
		<label>식품명</label>
		<%-- input placeholder : 입력박스의 안내메시지 --%>
		<input name="f_name" placeholder="식품명을 입력한 후 Enter..."/>
	</form>
	<div>
		<%--
			c taglib에는 if else가 없어서 choose를 사용한다
			<c:choose>
				<c:when> : if에 해당하는 부분
				</c:when>
				<c:otherwise> : else에 해당하는 부분
				</c:otherwise>
			</c:choose>
		 --%>
		<c:choose>
			<c:when test="${not empty FOODS}">
				<table>
					<tr>
						<th>식품코드</th>
						<th>식품명</th>
						<th>출시연도</th>
						<th>제공량</th>
						<th>총내용량</th>
						<th>에너지</th>
						<th>단백질</th>
						<th>지방</th>
						<th>탄수화물</th>
						<th>총당류</th>
					</tr>
					<%-- 
						Controller에서 전달받은 FOODS(List Type) Attribute를 for 반복문으로 반복하면서
						item을 getter하여 FOOD(DTO Type)에 담아라
					--%>
					<c:forEach items="${FOODS}" var="FOOD">
					<tr>
					<%-- forEach가 만든 FOOD(DTO)에서 각 변수값을 getter하여 화면에 보여라 --%>
						<td>${FOOD.fd_code}</td>
						<td>
							<a href="${rootPath}/food/insert?fd_code=${FOOD.fd_code}">
								${FOOD.fd_name}
							</a>
						</td>
						<td>${FOOD.fd_year}</td>
						<td>${FOOD.fd_ones}</td>
						<td>${FOOD.fd_capa}</td>
						<td>${FOOD.fd_cal}</td>
						<td>${FOOD.fd_protein}</td>
						<td>${FOOD.fd_fat}</td>
						<td>${FOOD.fd_carbo}</td>
						<td>${FOOD.fd_sugar}</td>			
					</tr>
					</c:forEach>
				</table>
			</c:when>
		</c:choose>
	</div>
</body>
</html>