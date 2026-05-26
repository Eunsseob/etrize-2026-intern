<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MBTI 테스트</title>
	<script type="text/javascript">
	    var currentStep = 1;
	    var pageSize = 5;
	    
	    function showStep(step) {
	    	
	    	if (step > currentStep) {
	            // 현재 보여지고 있는 행(currentStep) 안의 라디오 버튼만 검사
	            var currentRows = $j(".mbti-row").filter(function(index) {
	                return index >= (currentStep - 1) * pageSize && index < currentStep * pageSize;
	            });
	            
	            var isAllChecked = true;
	            
	            // 전체 체크안되면 다음페이지 못가게
	            currentRows.each(function() {
	                // 이 행 안에 선택된 라디오 버튼이 있는지 확인
	                if ($j(this).find("input[type='radio']:checked").length === 0) {
	                    isAllChecked = false;
	                }
	            });

	            if (!isAllChecked) {
	                alert("페이지에 있는 모든 문항을 선택하세요");
	                return;
	            }
	        }
	    	
	        var totalRows = $j(".mbti-row").length;
	        var totalSteps = Math.ceil(totalRows / pageSize);
	
	        if(step < 1 || step > totalSteps) return;
	
	        // 나머지 질문들은 안보이게 하는 메서드
	        $j(".mbti-row").hide();
	        var startIdx = (step - 1) * pageSize;
	        var endIdx = startIdx + pageSize;
	        
	        $j(".mbti-row").each(function(index) {
	            if(index >= startIdx && index < endIdx) {
	                $j(this).show();
	            }
	        });
	        
	        currentStep = step;
	        
	        // 이전 버튼 제어
	        $j("#prevBtn").toggle(currentStep > 1);
	        
	        // 다음/결과 버튼 제어
	        if(currentStep === totalSteps) {
	            $j("#nextBtn").hide();
	            $j("#submitBtn").show();
	        } else {
	            $j("#nextBtn").show();
	            $j("#submitBtn").hide();
	        }
	    }
	
	    // 설문조사 1부터 스타트
	    $j(document).ready(function() {
	        if($j(".mbti-row").length > 0) {
	            showStep(1);
	        }
	    });
	</script>
</head>
<body>
    <h2>MBTI 테스트</h2>

    <c:choose>
        <c:when test="${empty boardList}">
            <p>등록된 테스트 문항이 없습니다.</p>
        </c:when>
        <c:otherwise>
            <form action="/board/mbtiResult.do" method="post" onsubmit="return validateForm()">
                <table align="center">
				    <c:forEach var="board" items="${boardList}" varStatus="status">
				        <tbody class="mbti-row">
				            <tr>
				                <td colspan="9"><h3><strong>${board.boardComment}</strong></h3></td>
				            </tr>
				            <tr>
				                <td>그렇다</td>
				                <c:forEach begin="1" end="7" var="score">
				                    <td><input type="radio" name="q_${board.boardNum}" value="${score}"></td>
				                </c:forEach>
				                <td>그렇지 않다</td>
				            </tr>
				        </tbody>
				    </c:forEach>
				</table>

                <div align="center" style="margin-top:20px;">
	                <button type="button" id="prevBtn" onclick="showStep(currentStep - 1)">이전 단계</button>
                    <button type="button" id="nextBtn" onclick="showStep(currentStep + 1)">다음 단계</button>
                    <button type="submit" id="submitBtn" style="display:none;">결과 보기</button>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>