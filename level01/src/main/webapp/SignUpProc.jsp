<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mDao" class="member.MemberDao" />
<jsp:useBean id="bean" class="member.Member"/>
<jsp:setProperty property="*" name="bean" />
<%
	boolean result = mDao.insertMember(bean);
	String msg = "회원가입에 실패하였습니다.";
	String location = "signup.jsp";
	if(result) {
		msg = "회원가입을 하였습니다.";
		location = "MainJSP.jsp";
	}
			
%>
<script type="text/javascript">
	alert("<%=msg %>");
	location.href="<%=location %>";
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>