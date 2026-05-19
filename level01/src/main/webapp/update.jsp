<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.*" %>
<%
	Board board = (Board)session.getAttribute("bean");
%>
<!DOCTYPE html>
<html>
<head>
<title>수정하기</title>
</head>
<body>
<header>
	<h2>이트리즈 게시판 글 수정 페이지</h2>
</header>
<form method="post" action="boardUpdate">
	<div class="list">
		<table border="1">
			<tr>
				<th width=100px>Title</th>
				<td><input name="BOARD_TITLE" value="<%=board.getBoardTitle() %>"></td>
			</tr>
			<tr>
				<th>Comment</th>
				<td><textarea name="BOARD_COMMENT" rows="10" cols="50"><%=board.getBoardComment() %></textarea></td>
			</tr>
			<tr>
				<th>Writer</th>
				<td align="center"><%=board.getCreator() %></td>
			</tr>
		</table>
	</div>
	<a href="MainJSP.jsp">List</a> &emsp;
	<input type = "submit" value="Update">
</form>

</body>
</html>