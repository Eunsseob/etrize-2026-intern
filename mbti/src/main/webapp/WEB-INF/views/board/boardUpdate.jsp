<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardUpdate</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
	});

</script>
<body>
<form action="/board/${board.boardType}/${board.boardNum}/boardUpdate.do" method="post">
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
						Title
					</td>
					<td width="400">
						<input name="boardTitle" value="${board.boardTitle}">
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
						<textarea name="boardComment" rows="10" cols="50">${board.boardComment}</textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href="/board/boardList.do">리스트로 가기</a>&emsp;
			<input type="hidden" name="boardNum" value="${board.boardNum}">            
            <button type="submit">수정완료</button>
      	</td>
	</tr>
</table>
</form>
</body>
</html>