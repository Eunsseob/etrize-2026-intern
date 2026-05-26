<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
</head>
<script type="text/javascript">
	$j(document).ready(function(){
		
		$j("#submit").on("click", function(){
		    if(!confirm('삭제하시겠습니까?')) return;
		    
		    var $frm = $j('.boardDelete :input');
			var param = $frm.serialize();


		    $j.ajax({
		        url : "/board/boardDeleteAction.do",
		        dataType: "json",
		        type: "POST",
		        data : param,
		        success: function(data, textStatus, jqXHR) {
		            if(data.success === "Y") {
		                alert("게시물 삭제 성공");
		                location.href = "/board/boardList.do";
		            } else {
		                alert("이미 삭제된 게시물입니다.");
		                location.href = "/board/boardList.do";
		            }
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		            alert("실패");
		        }
		    });
		});
	});
	
</script>
<body>
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					${board.boardComment}
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
			<a href="/board/boardList.do">List</a>&emsp;
			<a href="/board/${board.boardType}/${board.boardNum}/boardUpdate.do">업데이트</a>
			<form action="/board/boardDeleteAction.do" method="post" class="boardDelete">
				<input type="hidden" name="boardNum" value="${board.boardNum}">
		        <input type="hidden" name="boardType" value="${board.boardType}">
	            <input id="submit" type="button" value="삭제하기">
        	</form>
		</td>
	</tr>
</table>	
</body>
</html>