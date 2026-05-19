<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, board.*" %>
<jsp:useBean id="bDao" class="board.BoardDao" />
<%
    // 로그인 여부 상관없이 게시판 목록을 보여주기 위해 상단에서 공통으로 리스트를 조회
    String boardType = "";
    int boardNum = 1;
    String boardTitle = "";
    ArrayList<Board> alist = bDao.getBoardList(boardType, boardNum, boardTitle);
%>
<script type="text/javascript">
	function list(){
		document.listFrm.submit();
	}
</script>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>HOMEPAGE</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <style>
        .cen { text-align: center; }
    </style>
</head>
<body>
    <header>
        <h2>이트리즈시스템 공지사항</h2><br/>
    </header>

    <%
    	String id = (String)session.getAttribute("nameKey");
    	if(id != null){
    %>
        <b><%= id %>님</b> total : <%= (alist != null) ? alist.size() : 0 %>
    	<table border="1">
            <tr>
              <th>Type</th>
              <th>No</th>
              <th>Title</th>
            </tr>
            <%
                if(alist == null || alist.size() == 0) {
            %>
                <tr>
                    <td colspan="3" class="cen">등록된 게시글이 없습니다.</td>
                </tr>
            <%
                } else {
                    for(int i=0; i<alist.size(); i++) {
                        Board board = alist.get(i);
            %>
                <tr>
                    <td class="cen"><%= board.getBoardType() %></td>
                    <td class="cen"><%= board.getBoardNum() %></td>
                    <td><a href="read.jsp?boardNum=<%= board.getBoardNum() %>"><%= board.getBoardTitle() %></a></td>
                </tr>
            <%
                    } // for end
                } // if-else end
            %>
       </table>
       <br/>
       <a href="write.jsp">글쓰기</a> &emsp; <a href="logout.jsp">로그아웃</a>

    <%
    	} else {
    %>
        <a href="login.jsp">login</a> &emsp; <a href="signup.jsp">join</a>
        total : <%= (alist != null) ? alist.size() : 0 %>
        <br/>
    
        <table border="1">
            <tr>
              <th>Type</th>
              <th>No</th>
              <th>Title</th>
            </tr>
            <%
                if(alist == null || alist.size() == 0) {
            %>
                <tr>
                    <td colspan="3" class="cen">등록된 게시글이 없습니다.</td>
                </tr>
            <%
                } else {
                    for(int i=0; i<alist.size(); i++) {
                        Board board = alist.get(i);
            %>
                <tr>
                    <td class="cen"><%= board.getBoardType() %></td>
                    <td class="cen"><%= board.getBoardNum() %></td>
                    <td><a href="read.jsp?boardNum=<%= board.getBoardNum() %>"><%= board.getBoardTitle() %></a></td>
                </tr>
            <%
                    } // for end
                } // if-else end
            %>
        </table>
        <br/>
        <a href="write.jsp">글쓰기</a>
    <% 
        } // 상단 로그인 if-else 문 종료 
    %>
    
    <br/><br/>
    <input type="checkbox" name="hobby" value="전체" checked>전체&nbsp;
	<input type="checkbox" name="hobby" value="일반">일반&nbsp;
	<input type="checkbox" name="hobby" value="Q&A">Q&A&nbsp;
	<input type="checkbox" name="hobby" value="익명">익명&nbsp;
	<input type="checkbox" name="hobby" value="자유">자유&nbsp;
	<input type="button" name="joi" value="조회" onclick="list()">
</body>
</html>