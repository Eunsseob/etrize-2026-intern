<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 화면</h2>
	<form action="LoginProc.jsp" method="post">
		<table border="1" align="center">
			<tr>
				<th>id</th>
				<th><input type="text" name="userId"></th>
			</tr>
			<tr>
				<th>pw</th>
				<th><input type="password" name="userPw"></th>
		</table>
		<input type="submit" value="login">
	</form>
	<br />
</body>
</html>