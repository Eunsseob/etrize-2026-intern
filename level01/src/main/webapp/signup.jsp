<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="script.js"></script>
</head>
<body>
<h2>회원가입 화면</h2>
<a href="MainJSP.jsp">List</a>
<form name="frm" action="SignUpProc.jsp">
<table border="1">
        <tr>
        	<th>id</th>
        	<th><input type="text" name="userId" id="userId"><input type="button" value="중복확인" 
        	onclick="idCheck(document.frm.userId.value);"></th>
        </tr>
        <tr>
	        <th>pw</th>
	        <th><input type="password" name="userPw">
        </th>
        <tr>
	        <th>pw check</th>
	        <th><input type="password" name="pw">
        </th>
        <tr>
	        <th>name</th>
	        <th><input type="text" name="userName">
        </th>
        <tr>
	        <th>phone</th>
	        <th><select name="userPhone1">
							<option value="010" selected>010
							<option value="011">011
							<option value="012">012
							<option value="019">019						
			</select>
	        - <input type="number" name="userPhone2" style="width: 30px;">
	        - <input type="number" name="userPhone3" style="width: 30px;">
        </th>
        <tr>
	        <th>postNo</th>
	        <th><input type="text" name="creator">
        </th>
        <tr>
	        <th>address</th>
	        <th><input type="text" name="userAddr1">
        </th>
        <tr>
	        <th>company</th>
	        <th><input type="text" name="userCompany">
        </th>
    </table>
    <input type="submit" value="Join">
    </form>
    <br/>
</body>
</html>