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
		    $j("#eduTable tbody").append(`
		        <tr>
		            <td><input type="checkbox" name="eduCheck"></td>
		            <td>
		                <input type="text" name="eduStart"><br>
		                ~<br>
		                <input type="text" name="eduEnd">
		            </td>
		            <td>
		                <select name="eduStatus">
		                    <option value="M">재학</option>
		                    <option value="F">중퇴</option>
		                    <option value="X">졸업</option>
		                </select>
		            </td>
		            <td><input type="text" name="eduSchool"><br>
			            <select name="edy">
			            <option value="z">서울</option>
			            <option value="x">경기</option>
			            <option value="c">지방</option>
			        </select></td>
		            <td><input type="text" name="eduMajor"></td>
		            <td><input type="text" name="eduGrade"></td>
		        </tr>
		    `);
		});

		$j("#carAdd").on("click", function(e) {
		    e.preventDefault();
		    $j("#carTable tbody").append(`
		        <tr>
		            <td><input type="checkbox" name="carCheck"></td>
		            <td>
		                <input type="text" name="startPeriod">
		                ~<br>
		                <input type="text" name="endPeriod">
		            </td>
		            
		            <td>
		            	<input type="text" name="compName">
			 		</td>
		            <td><input type="text" name="task"></td>
		            <td><input type="text" name="salary"></td>
		        </tr>
		    `);
		});
		
		$j("#cerAdd").on("click", function(e) {
		    e.preventDefault();
		    $j("#cerTable tbody").append(`
		        <tr>
		            <td><input type="checkbox" name="cerCheck"></td>
		            <td><input type="text" name="qualifiName"></td>
		            <td><input type="text" name="acquDate"></td>
		            <td><input type="text" name="organizeName"></td>
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
							<input type="text" name="birth">
						</td>
					</tr>
					<tr>
						<td>성별</td>
						<td>
							<select name="gender">
					            <option value="남자">남자</option>
					            <option value="여자">여자</option>
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
							<input type="text" name="email">
						</td>
						<td>주소</td>
						<td>
							<input type="text" name="addr">
						</td>
					</tr>
					<tr>
						<td>희망근무지</td>
						<td>
							<select name="location">
					            <option value="서울">서울</option>
					            <option value="전국">전국</option>
					        </select> 
						</td>
						<td>근무형태</td>
						<td>
							<select name="workType">
					            <option value="정규직">정규직</option>
					            <option value="계약직">계약직</option>
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
				<tr>
					<td>
					<input type="checkbox">
					</td>
					<td>
						<input type="text" name="educationList[0].startPeriod"></br>
						~</br>
						<input type="text" name="educationList[0].endPeriod"></br>
					</td>
					<td>
						<select name="educationList[0].division">
					         <option value="재학">재학</option>
					         <option value="중퇴">중퇴</option>
					         <option value="졸업">졸업</option>
					     </select> 
					</td>
					<td>
						<input type="text" name="educationList[0].schoolName"></br>
						<select name="educationList[0].location">
					         <option value="서울">서울</option>
					         <option value="경기">경기</option>
					         <option value="지방">지방</option>
					     </select> 
					 </td>
					 <td>
					 	<input type="text" name="educationList[0].major">
					</td>
					<td>
					 	<input type="text" name="educationList[0].grade">
					</td>
				</tr>
		</table>
		
		</br>
		
		<h2><strong>경력</strong></h2>
<button type="button" id="carAdd">추가</button>
<button type="button" id="carDel">삭제</button>
		</br>
		<table border=1 id="carTable">
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
				<tr>
					<td>
					<input type="checkbox">
					</td>
					<td>
						<input id="address" type="text">
						~</br>
						<input id="address" type="text">
					</td>
					<td>
						<input id="address" type="text">
					</td>
					<td>
						<input id="address" type="text">
					 </td>
					<td>
						<input id="address" type="text">
					</td>
				</tr>
		</table>
	
		</br>
		
		<h2><strong>자격증</strong></h2>
<button type="button" id="cerAdd">추가</button>
<button type="button" id="cerDel">삭제</button>
		</br>
		<table border=1 id="cerTable">
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
				<tr>
					<td>
					
					<input type="checkbox">
					</td>
					 <td>
					 	<input id="address" type="text">
					</td>
					 <td>
					 	<input id="address" type="text">
					</td>
					<td>
						<input id="address" type="text">
					</td>
				</tr>
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