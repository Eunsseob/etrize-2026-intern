<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "board.*" %>
<jsp:useBean id="bDao" class="board.BoardDao" />
<%
    String numParam = request.getParameter("boardNum");
    
    int boardNum = 1; // 기본값 설정
    if(numParam != null && !numParam.isEmpty()) {
        boardNum = Integer.parseInt(numParam); // 받아온 글 번호를 숫자로 변환
    }

    Board board = bDao.getBoard(boardNum); 
    session.setAttribute("bean", board);
    if(board == null) {
        board = new Board();
        board.setBoardTitle("존재하지 않는 게시글입니다.");
        board.setBoardComment("내용을 불러올 수 없습니다.");
        board.setCreator("알 수 없음");
    }

    Board sessionBoard = (Board)session.getAttribute("bean");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 상세 페이지</title>
</head>
<body>
<header>
	<h2>이트리즈 상세 페이지</h2>
</header>
	<table border="1">
		<tr>
			<td align="center" width="15%">Title</td>
			<td align="center" width="85%"><%=board.getBoardTitle() %></td>
		</tr>
		<tr>
			<td align="center" width="15%" height="500px">Comment</td>
			<td align="center" width="85%" height="500px"><%=board.getBoardComment() %></td>
		</tr>
		<tr>
			<td align="center" width="15%">Writer</td>
			<td align="center" width="85%"><%=board.getCreator() %></td>
		</tr>
	</table>
	<a href="MainJSP.jsp">List</a> &emsp;
	<a href="update.jsp?boardNum=<%=board.getBoardNum()%>">수정</a>
</body>
</html>