<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MbtiMainPage</title>
</head>
<body>
	<h2>MBTI 테스트</h2>
	
	<table border="1">

    <%-- EI 문항 --%>
    <tr><th colspan="3">E / I 유형</th></tr>
    <c:forEach var="board" items="${eiList}">
    <tr>
        <td>${board.boardComment}</td>
        <td><input type="radio" name="ei_${board.boardNum}" value="Y"> 동의</td>
        <td><input type="radio" name="ei_${board.boardNum}" value="N"> 비동의</td>
    </tr>
    </c:forEach>

    <%-- NS 문항 --%>
    <tr><th colspan="3">N / S 유형</th></tr>
    <c:forEach var="board" items="${nsList}">
    <tr>
        <td>${board.boardComment}</td>
        <td><input type="radio" name="ns_${board.boardNum}" value="Y"> 동의</td>
        <td><input type="radio" name="ns_${board.boardNum}" value="N"> 비동의</td>
    </tr>
    </c:forEach>

    <%-- FT 문항 --%>
    <tr><th colspan="3">F / T 유형</th></tr>
    <c:forEach var="board" items="${ftList}">
    <tr>
        <td>${board.boardComment}</td>
        <td><input type="radio" name="ft_${board.boardNum}" value="Y"> 동의</td>
        <td><input type="radio" name="ft_${board.boardNum}" value="N"> 비동의</td>
    </tr>
    </c:forEach>

    <%-- JP 문항 --%>
    <tr><th colspan="3">J / P 유형</th></tr>
    <c:forEach var="board" items="${jpList}">
    <tr>
        <td>${board.boardComment}</td>
        <td><input type="radio" name="jp_${board.boardNum}" value="Y"> 동의</td>
        <td><input type="radio" name="jp_${board.boardNum}" value="N"> 비동의</td>
    </tr>
    </c:forEach>

</table>

<button onclick="submitMbti()">결과 보기</button>

<script>
function submitMbti() {

    // 미선택 항목 체크
    var unAnswered = false;
    $j("input[type='radio']").each(function() {
        var name = $j(this).attr("name");
        if ($j("input[name='" + name + "']:checked").length === 0) {
            unAnswered = true;
            return false; // each 중단
        }
    });

    if (unAnswered) {
        alert("모든 문항에 답변해주세요!");
        return;
    }

    // 동의 개수 계산
    var eiCount = $j("input[name^='ei_'][value='Y']:checked").length;  // E 동의 수
    var nsCount = $j("input[name^='ns_'][value='Y']:checked").length;  // N 동의 수
    var ftCount = $j("input[name^='ft_'][value='Y']:checked").length;  // F 동의 수
    var jpCount = $j("input[name^='jp_'][value='Y']:checked").length;  // J 동의 수

    var eiTotal = $j("input[name^='ei_'][value='Y']").length;
    var nsTotal = $j("input[name^='ns_'][value='Y']").length;
    var ftTotal = $j("input[name^='ft_'][value='Y']").length;
    var jpTotal = $j("input[name^='jp_'][value='Y']").length;

    // MBTI 계산
    var result = "";
    result += eiCount >= eiTotal / 2 ? "E" : "I";
    result += nsCount >= nsTotal / 2 ? "N" : "S";
    result += ftCount >= ftTotal / 2 ? "F" : "T";
    result += jpCount >= jpTotal / 2 ? "J" : "P";

    location.href = "/board/mbtiResult.do?mbtiType=" + result;
}
</script>

</body>
</html>
</body>
</html>