<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bDao" class="board.BoardDao"/>
<jsp:useBean id="bean" class="board.Board"/>
<jsp:setProperty property="*" name="bean" />
<%
	boolean result = bDao.insertBoard(bean);
	String msg = "게시물 생성 실패하였습니다.";
	String location = "write.jsp";
	if(result) {
		msg = "게시물을 생성 하였습니다.";
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