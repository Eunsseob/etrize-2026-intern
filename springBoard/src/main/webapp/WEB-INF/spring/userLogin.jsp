<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>userLogin</title>
</head>
<body>
<form action="/user/userLogin.do" method="post">
<table align="center">
	<tr>
		<td>
			<table border ="1"> 
					<tr>
						<td width="120" align="center">
						id
						</td>
						<td>
						<input name="userId" type="text" size="20" value="${user.userName}"> 
						</td>
					</tr>
					<tr>
						<td align="center">
						pw
						</td>
						<td>
						<input name="userPw" type="password" size="20" value="${user.userPw}"> 
						</td>
					</tr>
				</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<button type="submit">로그인하기</button>
		</td>
	</tr>
</table>
</form>	
</body>
</html>