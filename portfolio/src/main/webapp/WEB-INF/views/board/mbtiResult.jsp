<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<title>MBTI 검사 결과</title>
</head>
<body>

<div>
    <h2>당신의 검사 결과는</h2>
    <div class="mbti-text">${mbtiResult}</div>
</div>

<div style="width: 400px; margin: 0 auto;">
    <h3>상세 점수</h3>
    <table class="score-table">
        <tr>
            <td>E: ${eScore}점 / I: ${iScore}점</td>
            <td><strong>${mbtiResult.substring(0,1)}</strong></td>
        </tr>
        <tr>
            <td>S: ${sScore}점 / N: ${nScore}점</td>
            <td><strong>${mbtiResult.substring(1,2)}</strong></td>
        </tr>
        <tr>
            <td>F: ${fScore}점 / T: ${tScore}점</td>
            <td><strong>${mbtiResult.substring(2,3)}</strong></td>
        </tr>
        <tr>
            <td>J: ${jScore}점 / P: ${pScore}점</td>
            <td><strong>${mbtiResult.substring(3,4)}</strong></td>
        </tr>
    </table>
    
    <div align="center" style="margin-top: 30px;">
        <a href="/board/mbti.do">다시 검사하기</a>
    </div>
</div>

</body>
</html>