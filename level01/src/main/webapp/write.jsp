<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
    <h2>이트리즈시스템 글 작성하기</h2><br/>
    </header>
    <form action="PostBoard.jsp" method="post">
    <button submit="submit" value="write">작성</button>
    <button>행 추가</button>
      <table border="1">
            <tr>
              <th>Type</th>
              <th><select name="boardType">
							<option value="일반" selected>일반
							<option value="Q&A">Q&A
							<option value="익명">익명
							<option value="자유">자유						
			</select>
            </tr>
            <tr>
            	<th>Title</th>
            	<th><input type="text" name=boardTitle></th>
            </tr>
            <tr>
            	<th>Comment</th>
            	<th><input type="text" name=boardComment></th>
            </tr>
            <tr>
            	<th>Writer</th>
            	<%
			    	String name = (String)session.getAttribute("nameKey");
			    	if(name != null){
			    %>
            	<th><%= name %>
            	<input type="hidden" name="creator" value="<%= name %>">
            	</th>
            	<%
			    	}
            	%> 
            </tr>
       </table>
       </form>
       <br/>
       <a href="MainJSP.jsp">List</a>
</body>
</html>