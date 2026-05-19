<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="mDao" class="member.MemberDao" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPw");
		String msg = "로그인에 실패하였습니다.";
		
		String userName = mDao.loginMember(id, pwd);
		
		if (userName != null) {
		    session.setAttribute("idKey", id);
		    session.setAttribute("nameKey", userName);
		    msg = userName + "님, 로그인에 성공하였습니다.";
		}
	%>
	<script>
		alert("<%=msg %>");
		location.href="MainJSP.jsp";
	</script>
</body>
</html>