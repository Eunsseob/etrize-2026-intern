<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<script>
	// 추가 버튼
    $j(document).ready(function() {

		$j("#eduAdd").on("click", function(e) {
		    e.preventDefault();
		    const idx = $j("#eduTable tbody tr").length;
		    $j("#eduTable tbody").append(`
		        <tr>
		            <td><input type="checkbox" name="eduCheck"></td>
		            <td>
		                <input type="text" name="educationList[${idx}].startPeriod"><br>
		                ~<br>
		                <input type="text" name="educationList[${idx}].endPeriod">
		            </td>
		            <td>
		                <select name="educationList[${idx}].division">
		                    <option value="재학">재학</option>
		                    <option value="중퇴">중퇴</option>
		                    <option value="졸업">졸업</option>
		                </select>
		            </td>
		            <td><input type="text" name="educationList[${idx}].schoolName"><br>
			            <select name="educationList[${idx}].location">
			            <option value="서울">서울</option>
			            <option value="경기">경기</option>
			            <option value="지방">지방</option>
			        </select></td>
		            <td><input type="text" name="educationList[${idx}].major"></td>
		            <td><input type="text" name="educationList[${idx}].grade"></td>
		        </tr>
		    `);
		});

		$j("#carAdd").on("click", function(e) {
		    e.preventDefault();
		    const idx = $j("#carTable tbody tr").length;
		    $j("#carTable tbody").append(`
		        <tr>
		            <td><input type="checkbox" name="carCheck"></td>
		            <td>
		                <input type="text" name="careerList[${idx}].startPeriod">
		                ~<br>
		                <input type="text" name="careerList[${idx}].endPeriod">
		            </td>
		            
		            <td>
		            	<input type="text" name="careerList[${idx}].compName">
			 		</td>
		            <td><input type="text" name="careerList[${idx}].task"></td>
		            // <td><input type="text" name="salary"></td>
		        </tr>
		    `);
		});
		
		$j("#cerAdd").on("click", function(e) {
		    e.preventDefault();
		    const idx = $j("#cerTable tbody tr").length;
		    $j("#cerTable tbody").append(`
		        <tr>
		            <td><input type="checkbox" name="cerCheck"></td>
		            <td><input type="text" name="certificateList[${idx}].qualifiName"></td>
		            <td><input type="text" name="certificateList[${idx}].acquDate"></td>
		            <td><input type="text" name="certificateList[${idx}].organizeName"></td>
		        </tr>
		    `);
		});
		// 삭제 버튼
		$j("#eduDel").on("click", function(e) {
		    e.preventDefault();
		    $j("#eduTable tbody tr").each(function() {
		        if($j(this).find("input[type='checkbox']").is(":checked")) {
		            $j(this).remove();
		        }
		    });
		});
		
		$j("#carDel").on("click", function(e) {
		    e.preventDefault();
		    $j("#carTable tbody tr").each(function() {
		        if($j(this).find("input[type='checkbox']").is(":checked")) {
		            $j(this).remove();
		        }
		    });
		});
		
		$j("#cerDel").on("click", function(e) {
		    e.preventDefault();
		    $j("#cerTable tbody tr").each(function() {
		        if($j(this).find("input[type='checkbox']").is(":checked")) {
		            $j(this).remove();
		        }
		    });
		});
		
		$j("#saveBt").on("click", function() {

			// 학력 index 재정렬
		    $j("#eduTable tbody tr").each(function(i) {
		        $j(this).find("[name*='educationList']").each(function() {
		            var name = $j(this).attr("name");
		            $j(this).attr("name", name.replace(/educationList\[\d*\]/, "educationList[" + i + "]"));
		        });
		    });

		    // 경력 index 재정렬
		    $j("#carTable tbody tr").each(function(i) {
		        $j(this).find("[name*='careerList']").each(function() {
		            var name = $j(this).attr("name");
		            $j(this).attr("name", name.replace(/careerList\[\d*\]/, "careerList[" + i + "]"));
		        });
		    });

		    // 자격증 index 재정렬
		    $j("#cerTable tbody tr").each(function(i) {
		        $j(this).find("[name*='certificateList']").each(function() {
		            var name = $j(this).attr("name");
		            $j(this).attr("name", name.replace(/certificateList\[\d*\]/, "certificateList[" + i + "]"));
		        });
		    });
			    
		    var param = $j(".check :input").serialize();
		    $j.ajax({
		        url: "/recruit/userSignup.do",
		        type: "POST",
		        data: param,
		        success: function() {
		            alert("저장완료");
		        },
		        error: function() {
		            alert("실패");
		        }
		    });
		});
		    });
	
	
</script>

<body>
	<h1>입사지원서</h1>
		<form class="check" action="/recruit/userSignup.do" method="post">
	<table align="center">
	<tr>
		<td>
			<table border ="1"> 
					<tr>
						<td align="center">
							이름
						</td>
						<td>
							<input type="text" name="name" value="${name}">
						</td>
						<td>생년월일</td>
						<td>
							<input type="text" name="birth" value="${recruit.birth}">
						</td>
					</tr>
					<tr>
						<td>성별</td>
						<td>
							<select name="gender">
					            <option value="남자" ${recruit.gender == '남자' ? 'selected' : ''}>남자</option>
					            <option value="여자" ${recruit.gender == '여자' ? 'selected' : ''}>여자</option>
					        </select> 
						</td>
						<td align="center">연락처</td>
						<td>
							<input type="text" name="phone" value="${phone}">
						</td>
					</tr>
					<tr>
						<td align="center">email</td>
						<td>
							<input type="text" name="email" value="${recruit.email}">
						</td>
						<td>주소</td>
						<td>
							<input type="text" name="addr" value="${recruit.addr}">
						</td>
					</tr>
					<tr>
						<td>희망근무지</td>
						<td>
							<select name="location">
					            <option value="서울" ${recruit.location == '서울' ? 'selected' : ''}>서울</option>
					            <option value="전국" ${recruit.location == '전국' ? 'selected' : ''}>전국</option>
					        </select> 
						</td>
						<td>근무형태</td>
						<td>
							<select name="workType">
					            <option value="정규직" ${recruit.workType == '정규직' ? 'selected' : ''}>정규직</option>
					            <option value="계약직" ${recruit.workType == '계약직' ? 'selected' : ''}>계약직</option>
					        </select>  
						</td>
					</tr>
				</table>
		
		</br>
		
		<h2><strong>학력</strong></h2>
		<button type="button" id="eduAdd">추가</button>
		<button type="button" id="eduDel">삭제</button>
		</br>
		
		<table border=1 id="eduTable">
		<thead>
				<tr>
					<td>
					</td>
					<td>
						재학기간
					</td>
					<td>
						구분
					</td>
					<td>
						학교명(소재지)
					</td>
					<td>
						전공
					</td>
					<td>
						학점
					</td>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				    <c:when test="${not empty educationList}">
		<c:forEach items="${educationList}" var="edu" varStatus="s">
			<tr>
				<td><input type="checkbox"></td>
				<td>
					<input type="text" name="educationList[${s.index}].startPeriod" value="${edu.startPeriod}"><br>~<br>
					<input type="text" name="educationList[${s.index}].endPeriod" value="${edu.endPeriod}">
				</td>
				<td>
					<select name="educationList[${s.index}].division">
						<option value="재학" ${edu.division == '재학' ? 'selected' : ''}>재학</option>
						<option value="중퇴" ${edu.division == '중퇴' ? 'selected' : ''}>중퇴</option>
						<option value="졸업" ${edu.division == '졸업' ? 'selected' : ''}>졸업</option>
					</select>
				</td>
				<td>
					<input type="text" name="educationList[${s.index}].schoolName" value="${edu.schoolName}"><br>
					<select name="educationList[${s.index}].location">
						<option value="서울" ${edu.location == '서울' ? 'selected' : ''}>서울</option>
						<option value="경기" ${edu.location == '경기' ? 'selected' : ''}>경기</option>
						<option value="지방" ${edu.location == '지방' ? 'selected' : ''}>지방</option>
					</select>
				</td>
				<td><input type="text" name="educationList[${s.index}].major" value="${edu.major}"></td>
				<td><input type="text" name="educationList[${s.index}].grade" value="${edu.grade}"></td>
			</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
		<tr>
				<td><input type="checkbox"></td>
				<td>
					<input type="text" name="educationList[0].startPeriod" value="${edu.startPeriod}"><br>~<br>
					<input type="text" name="educationList[0].endPeriod" value="${edu.endPeriod}">
				</td>
				<td>
					<select name="educationList[0].division">
						<option value="재학"}>재학</option>
						<option value="중퇴"}>중퇴</option>
						<option value="졸업"}>졸업</option>
					</select>
				</td>
				<td>
					<input type="text" name="educationList[0].schoolName" value="${edu.schoolName}"><br>
					<select name="educationList[0].location">
						<option value="서울">서울</option>
						<option value="경기">경기</option>
						<option value="지방">지방</option>
					</select>
				</td>
				<td><input type="text" name="educationList[0].major" value="${edu.major}"></td>
				<td><input type="text" name="educationList[0].grade" value="${edu.grade}"></td>
			</tr>
		</c:otherwise>
		</c:choose>
		</tbody>
		</table>
		
		</br>
		
		<h2><strong>경력</strong></h2>
<button type="button" id="carAdd">추가</button>
<button type="button" id="carDel">삭제</button>
		</br>
		<table border=1 id="carTable" varStatus="k">
		<thead>
				<tr>
					<td>
					</td>
					<td>
						근무기간
					</td>
					<td>
						회사명
					</td>
					<td>
						부서/직급/직책
					</td>
					<td>
						지역
					</td>
				</tr>
				</thead>
				<tbody>
					<c:choose>
				    <c:when test="${not empty careerList}">
				<c:forEach items="${careerList}" var="car" varStatus="k">
				<tr>
					<td>
					<input type="checkbox">
					</td>
					<td>
						<input name="careerList[${k.index}].startPeriod" type="text" value="${car.startPeriod}">
						~</br>
						<input name="careerList[${k.index}].endPeriod" type="text" value="${car.endPeriod}">
					</td>
					<td>
						<input name="careerList[${k.index}].compName" type="text" value="${car.compName}">
					</td>
					<td>
						<input name="careerList[${k.index}].task" type="text" value="${car.task}">
					 </td>
					<td>
						<input name="careerList[${k.index}].location" type="text" value="${car.location}">
					</td>
				</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
				<tr>
					<td>
					<input type="checkbox">
					</td>
					<td>
						<input name="careerList[0].startPeriod" type="text">
						~</br>
						<input name="careerList[0].endPeriod" type="text">
					</td>
					<td>
						<input name="careerList[0].compName" type="text">
					</td>
					<td>
						<input name="careerList[0].task" type="text">
					 </td>
					<td>
						<input name="careerList[0].location" type="text">
					</td>
				</tr>
				</c:otherwise>
				</c:choose>
				</tbody>
		</table>
	
		</br>
		
		<h2><strong>자격증</strong></h2>
<button type="button" id="cerAdd">추가</button>
<button type="button" id="cerDel">삭제</button>
		</br>
		<table border=1 id="cerTable">
		<thead>
				<tr>
					<td>
					</td>
					<td>
						자격증명
					</td>
					<td>
						취득일
					</td>
					<td>
						발행처
					</td>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				    <c:when test="${not empty certificateList}">
				<c:forEach items="${certificateList}" var="cer" varStatus="w">
				<tr>
					<td>
					
					<input type="checkbox">
					</td>
					 <td>
					 	<input name="certificateList[${w.index}].qualifiName" type="text" value="${cer.qualifiName}">
					</td>
					 <td>
					 	<input name="certificateList[${w.index}].acquDate" type="text" value="${cer.acquDate}">
					</td>
					<td>
						<input name="certificateList[${w.index}].organizeName" type="text" value="${cer.organizeName}">
					</td>
				</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<td>
					<input type="checkbox">
					</td>
					 <td>
					 	<input name="certificateList[0].qualifiName" type="text">
					</td>
					 <td>
					 	<input name="certificateList[0].acquDate" type="text">
					</td>
					<td>
						<input name="certificateList[0].organizeName" type="text">
					</td>
				</c:otherwise>
				</c:choose>
				</tbody>
		</table>
			
		</br>
		</br>
		<button type="button" id="saveBt">저장</button>
		<button id="#" value="삭제">제출</button>
		</br>
</table>
</form>	
</body>
</html>