<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mDao" class="member.MemberDao"/>
<jsp:useBean id="bean" class="member.Member"/>
<jsp:setProperty property="*" name="bean" />
<%
	
	String msg = "회원가입에 실패하였습니다.";
	String location = "member.jsp";
	
			
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