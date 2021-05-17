<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	* {
		box-sizing: border-box;
	}
	
	h1, form {
		width: 80%;
		margin: 10px auto;
	}
	
	h1 {
		background-color: #3EADBB;
		color: white;
		padding: 1.2rem;
		text-align: center;
	}
	
	form {
		border: 1px solid #ddd ;
		border-radius : 10px ;
		padding: 10px ;
	}
	
	label {
		display : inline-block;
		width: 20% ;
		text-align: right;
	}
	
	input {
		width: 50%;
		padding: 10px ;
		margin: 10px ;
	}
	button {
		background-color: #A7E2DD;
		color : #080B17;
		font-weight : bolder;
		padding: 5px;
		width: 10%;
		height : 50px;
		outline: 0 ;
		border: 0 ;
		border-radius: 5px ;
		cursor: pointer;
	}	
	
	button:hover {
		/* x-offset y-offset blue spread color */
		box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.3);
	}
</style>
</head>
<body>
	<h1>식품 섭취 내역 입력</h1>
	
	<form method="POST">
		<p>
			<label>식품코드</label> <input name="mf_code" value="${FOOD.fd_code}">
		<p>
			<label>식품이름</label> <input name="mf_name" value="${FOOD.fd_name}">
		<p>
			<label>섭취일자</label> <input type="date" name="mf_date" value="${TODAY}">
		<p>
			<label>섭취량</label> <input type="number" name="mf_amt" value="1">
		<p>
			<button>등록</button>
	</form>

</body>
</html>