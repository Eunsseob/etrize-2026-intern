<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
	
		// 체크박스
		$j("input[name='codeName'][value='전체']").on("change", function(){
		    $j("input[name='codeName']").prop("checked", $j(this).is(":checked"));
		});
		
		// 개별 체크박스
		$j("input[name='codeName']:not([value='전체'])").on("change", function(){
		    var total = $j("input[name='codeName']:not([value='전체'])").length;
		    var checked = $j("input[name='codeName']:not([value='전체']):checked").length;
		    $j("input[name='codeName'][value='전체']").prop("checked", total === checked);
		});
	
	});
	
	function boardFilter() {
	    var checked = [];
	    $j("input[name='codeName']:not([value='전체']):checked").each(function(){
	        checked.push($j(this).val());
	    });

	    var typeMap = {
	        "일반" : "a01",
	        "Q&A"  : "a02",
	        "익명" : "a03",
	        "자유" : "a04"
	    };

	    // 전체 체크거나 아무것도 없으면 전체조회
	    if(checked.length === 0 || $j("input[name='codeName'][value='전체']").is(":checked")) {
	        location.href = "/board/boardList.do?pageNo=1";
	        return;
	    }

	 // 여러개 선택시 boardType=a01&boardType=a02 형태로 넘김
	    var params = "pageNo=1";
	    $j.each(checked, function(i, val){
	        params += "&boardType=" + typeMap[val];
	    });

	    location.href = "/board/boardList.do?" + params;
	}

</script>
<body>
<table  align="center">
	<tr>
	    <td align="left">
	    <c:choose>
	        <c:when test="${not empty sessionScope.userName}">
	           ${userName}
	        </c:when>
	        <c:otherwise>
	            <a href="/user/userLogin.do">login</a>&emsp;
	            <a href="/user/userSignup.do">Join</a>
	        </c:otherwise>
	    </c:choose>
		</td>

		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							<c:choose>
							    <c:when test="${list.boardType == 'a01'}">일반</c:when>
							    <c:when test="${list.boardType == 'a02'}">Q&A</c:when>
							    <c:when test="${list.boardType == 'a03'}">익명</c:when>
							    <c:when test="${list.boardType == 'a04'}">자유</c:when>
							    <c:otherwise>${list.boardType}</c:otherwise>
							</c:choose>
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
	<td colspan="2" align="right">
	
			<a href ="/board/boardWrite.do">글쓰기</a>
	    <c:choose>
	        <c:when test="${not empty sessionScope.userName}">
	            &emsp;<a href="${pageContext.request.contextPath}/user/userLogout.do">로그아웃</a>
	        </c:when>
	    </c:choose>
	
		</td>
	</tr>
	<tr>
	<td colspan="2" algin="left">
	<input type="checkbox" name="codeName" value="전체">전체&nbsp;
	<input type="checkbox" name="codeName" value="일반">일반&nbsp;
	<input type="checkbox" name="codeName" value="Q&A">Q&A&nbsp;
	<input type="checkbox" name="codeName" value="익명">익명&nbsp;
	<input type="checkbox" name="codeName" value="자유">자유&nbsp;
	<input type="button" name="joi" value="조회" onclick="boardFilter()">
	</td>
	
</table>	
</body>
</html>