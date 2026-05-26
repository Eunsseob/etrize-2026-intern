<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MBTI_Test</title>
</head>
<body>
    <table align="center">
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