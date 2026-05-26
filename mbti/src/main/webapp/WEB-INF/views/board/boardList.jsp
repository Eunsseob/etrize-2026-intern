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

    $j(document).ready(function() {

        // 전체 체크박스 이벤트
        // $ 안에 넣지마라 한글
        // 맵을 사용해서 코드Id가 전체이면 change 실행
        // 코드Id 인풋의 체크 상태를 현재 클릭한 체크박스가 체크 되면 다 체크, 해제되면 다 해제
        $j("input[name='codeId'][value='전체']").on("change", function() {
            $j("input[name='codeId']").prop("checked", $j(this).is(":checked"));
        });

        // 개별 체크박스 이벤트
        // 토탈이 4개고
        // 체크 길이가 4개면
        // 4개다 체크 되면 다 체크되었다고 말하기
        $j("input[name='codeId']:not([value='전체'])").on("change", function() {
            var total = $j("input[name='codeId']:not([value='전체'])").length;
            var checked = $j("input[name='codeId']:not([value='전체']):checked").length;
            $j("input[name='codeId'][value='전체']").prop("checked", total === checked);
        });

    });

    function boardFilter() {
    	// 배열 값으로 받고 있습니다.
        var checked = [];
    	// value에 '일반' 같은 이름 대신 'a01' 같은 코드ID가 직접 들어있음
        // 순회하면서 전체 체크박스 배열을 봄
        $j("input[name='codeId']:not([value='전체']):checked").each(function() {
            checked.push($j(this).val());
        });

        // 전체 체크거나 아무것도 체크되지 않았으면 전체조회
        if (checked.length === 0 || $j("input[name='codeId'][value='전체']").is(":checked")) {
            location.href = "/board/boardList.do?pageNo=1";
            return;
        }

        // 여러 개 선택 시 boardType=a01&boardType=a02 형태로 파라미터 빌드
        var params = "pageNo=1";
        $j.each(checked, function(i, val) {
            params += "&boardType=" + val;
        });

        location.href = "/board/boardList.do?" + params;
    }

</script>
<body>
    <table align="center">
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
                <table id="boardTable" border="1">
                    <tr>
                        <td width="80" align="center">Type</td>
                        <td width="40" align="center">No</td>
                        <td width="300" align="center">Title</td>
                    </tr>
                    <c:forEach items="${boardList}" var="list">
                        <tr>
                            <td align="center">
                                ${codeMap[list.boardType]}
                            </td>
                            <td>${list.boardNum}</td>
                            <td>
                                <a href="/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="right">
                <a href="/board/boardWrite.do">글쓰기</a>
                <c:choose>
                    <c:when test="${not empty sessionScope.userName}">
                        &emsp;<a href="${pageContext.request.contextPath}/user/userLogout.do">로그아웃</a>
                    </c:when>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="left">
                <input type="checkbox" name="codeId" value="전체">전체&nbsp;
                <c:forEach items="${codeList}" var="code">
                    <input type="checkbox" name="codeId" value="${code.codeId}">${code.codeName}&nbsp;
                </c:forEach>
                <input type="button" name="joi" value="조회" onclick="boardFilter()">
            </td>
        </tr>
    </table>
</body>
</html>