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
$j(document).ready(function() {

    if ("${recruit.submit}" === "Y") {
        $j("#saveBt, #submitBt, #eduAdd, #eduDel, #carAdd, #carDel, #cerAdd, #cerDel").hide();
    }

    var isAlerting = false;

    // 학력 기간 로직
    $j(document).on("blur", ".eduPeriod", function() {
        var row   = $j(this).closest("tr");
        var start = row.find(".eduPeriod:first").val().trim();
        var end   = row.find(".eduPeriod:last").val().trim();

        if (start === "" || end === "") return;

        // 생년월일 비교
        var birth = $j("input[name='birth']").val().trim();
        if (birth && start < birth.substring(0, 7)) {
            $j(this).css("border", "1px solid red");
            if (!isAlerting) {
                isAlerting = true;
                alert("재학 시작기간이 생년월일보다 빠를 수 없습니다.");
                isAlerting = false;
            }
            return;
        } else {
            $j(this).css("border", "");
        }

        // 시작 > 끝 체크
        if (start > end) {
            $j(this).css("border", "1px solid red");
            if (!isAlerting) {
                isAlerting = true;
                alert("시작일이 종료일보다 늦습니다.");
                isAlerting = false;
            }
            return;
        } else {
            $j(this).css("border", "");
        }

        // 학력 겹침
        var overlap = false;
        $j("#eduTable tbody tr").not(row).each(function() {
            var otherStart = $j(this).find(".eduPeriod:first").val().trim();
            var otherEnd   = $j(this).find(".eduPeriod:last").val().trim();
            if (otherStart && otherEnd && start <= otherEnd && otherStart <= end) {
                overlap = true;
                return false;
            }
        });
        if (overlap) {
            if (!isAlerting) {
                isAlerting = true;
                alert("학력기간이 겹칩니다.");
                isAlerting = false;
            }
            return;
        }

        // 경력 겹침
        $j("#carTable tbody tr").each(function() {
            var otherStart = $j(this).find(".carPeriod:first").val().trim();
            var otherEnd   = $j(this).find(".carPeriod:last").val().trim();
            if (otherStart && otherEnd && start <= otherEnd && otherStart <= end) {
                overlap = true;
                return false;
            }
        });
        if (overlap) {
            if (!isAlerting) {
                isAlerting = true;
                alert("재학기간과 근무기간이 겹칩니다.");
                isAlerting = false;
            }
            return;
        }
    });

    // 경력 기간 로직
    $j(document).on("blur", ".carPeriod", function() {
        var row   = $j(this).closest("tr");
        var start = row.find(".carPeriod:first").val().trim();
        var end   = row.find(".carPeriod:last").val().trim();

        if (start === "" || end === "") return;

        var birth = $j("input[name='birth']").val().trim();

        // 생년월일 비교
        if (birth && start < birth.substring(0, 7)) {
            $j(this).css("border", "1px solid red");
            row.find(".carError").text("생년월일보다 빠를 수 없습니다.");
            return;
        } else {
            $j(this).css("border", "");
            row.find(".carError").text("");
        }

        // 시작 > 끝 체크
        if (start > end) {
            $j(this).css("border", "1px solid red");
            row.find(".carError").text("시작일이 종료일보다 늦습니다.");
            return;
        } else {
            $j(this).css("border", "");
            row.find(".carError").text("");
        }

        // 경력 겹침
        var overlap = false;
        $j("#carTable tbody tr").not(row).each(function() {
            var otherStart = $j(this).find(".carPeriod:first").val().trim();
            var otherEnd   = $j(this).find(".carPeriod:last").val().trim();
            if (otherStart && otherEnd && start <= otherEnd && otherStart <= end) {
                overlap = true;
                return false;
            }
        });
        if (overlap) {
            $j(this).css("border", "1px solid red");
            row.find(".carError").text("경력기간이 겹칩니다.");
            return;
        }

        // 재학 겹침
        $j("#eduTable tbody tr").each(function() {
            var otherStart = $j(this).find(".eduPeriod:first").val().trim();
            var otherEnd   = $j(this).find(".eduPeriod:last").val().trim();
            if (otherStart && otherEnd && start <= otherEnd && otherStart <= end) {
                overlap = true;
                return false;
            }
        });
        if (overlap) {
            $j(this).css("border", "1px solid red");
            row.find(".carError").text("재학기간과 근무기간이 겹칩니다.");
            return;
        }

        // 정상
        $j(this).css("border", "");
        row.find(".carError").text("");
    });

    // 자격증 기간 로직
    $j(document).on("blur", ".acquDate", function() {
        var row  = $j(this).closest("tr");
        var acqu = row.find(".acquDate").val().trim();

        if (acqu === "") return;

        var birth = $j("input[name='birth']").val().trim();
        if (birth && acqu < birth.substring(0, 7)) {
            $j(this).css("border", "1px solid red");
            if (!isAlerting) {
                isAlerting = true;
                alert("자격증 취득일이 생년월일보다 빠를 수 없습니다.");
                isAlerting = false;
            }
            return;
        } else {
            $j(this).css("border", "");
        }
    });

    // 제출버튼
    $j("#submitBt").on("click", function() {
        if ("${recruit.submit}" != "S") {
            alert("저장 후 제출해주세요!");
            return;
        }
        $j.ajax({
            url: "/recruit/submitRecruit.do",
            type: "POST",
            data: { seq: "${recruit.seq}" },
            success: function() {
                alert("제출완료");
                location.reload();
            },
            error: function(xhr, status, err) {
                alert("실패");
            }
        });
    });

    // 부서/직급/직책
    $j(document).on("blur", ".taskInput", function() {
        var val = $j(this).val().trim();
        if (val === "") return;

        if (val.split("/").length !== 3) {
            $j(this).css("border", "1px solid red");
            $j(this).attr("placeholder", "예) 개발팀/대리/팀장");
        } else {
            $j(this).css("border", "");
            $j(this).attr("placeholder", "");
        }
    });

    // 이메일
    $j(document).on("blur", ".emailInput", function() {
        var val = $j(this).val().trim();
        if (val === "") return;

        if (val.split("@").length !== 2) {
            $j(this).css("border", "1px solid red");
        } else {
            $j(this).css("border", "");
        }
    });

    // 학점
    $j(document).on("blur", ".gradeInput", function() {
        var val = $j(this).val().trim();
        if (val === "") return;

        if (val < 0 || val > 4.5) {
            $j(this).css("border", "1px solid red");
        } else {
            $j(this).css("border", "");
        }
    });

    // 학력 추가
    $j("#eduAdd").on("click", function(e) {
        e.preventDefault();
        const idx = $j("#eduTable tbody tr").length;
        $j("#eduTable tbody").append(`
            <tr>
                <input type="hidden" name="educationList[${idx}].eduSeq" value="">
                <td><input type="checkbox" name="eduCheck"></td>
                <td>
                    <input type="month" name="educationList[${idx}].startPeriod" class="eduPeriod"><br>
                    ~<br>
                    <input type="month" name="educationList[${idx}].endPeriod" class="eduPeriod">
                </td>
                <td>
                    <select name="educationList[${idx}].division">
                        <option value="재학">재학</option>
                        <option value="중퇴">중퇴</option>
                        <option value="졸업">졸업</option>
                    </select>
                </td>
                <td>
                    <input type="text" name="educationList[${idx}].schoolName"><br>
                    <select name="educationList[${idx}].location">
                        <option value="서울">서울</option>
                        <option value="경기">경기</option>
                        <option value="지방">지방</option>
                    </select>
                </td>
                <td><input type="text" name="educationList[${idx}].major"></td>
                <td><input type="text" name="educationList[${idx}].grade" class="gradeInput" placeholder="ex) 0 ~ 4.5">/4.5</td>
            </tr>
        `);
    });

    // 경력 추가
    $j("#carAdd").on("click", function(e) {
        e.preventDefault();
        const idx = $j("#carTable tbody tr").length;
        $j("#carTable tbody").append(`
            <tr>
                <input type="hidden" name="careerList[${idx}].carSeq" value="">
                <td><input type="checkbox" name="carCheck"></td>
                <td>
                    <input type="month" name="careerList[${idx}].startPeriod" class="carPeriod">
                    ~<br>
                    <input type="month" name="careerList[${idx}].endPeriod" class="carPeriod">
                    <br><span class="carError" style="color:red; font-size:12px;"></span>
                </td>
                <td><input type="text" name="careerList[${idx}].compName"></td>
                <td><input type="text" name="careerList[${idx}].task" class="taskInput" placeholder="예) 개발팀/대리/팀장"></td>
                <td><input type="text" name="careerList[${idx}].location"></td>
            </tr>
        `);
    });

    // 자격증 추가
    $j("#cerAdd").on("click", function(e) {
        e.preventDefault();
        const idx = $j("#cerTable tbody tr").length;
        $j("#cerTable tbody").append(`
            <tr>
                <input type="hidden" name="certificateList[${idx}].certSeq" value="">
                <td><input type="checkbox" name="cerCheck"></td>
                <td><input type="text" name="certificateList[${idx}].qualifiName"></td>
                <td><input type="month" name="certificateList[${idx}].acquDate" class="acquDate"></td>
                <td><input type="text" name="certificateList[${idx}].organizeName"></td>
            </tr>
        `);
    });

    // 학력 삭제
    $j("#eduDel").on("click", function() {
        var checkedRows = $j("#eduTable tbody tr").filter(function() {
            return $j(this).find("input[type='checkbox']").is(":checked");
        });
        var totalRows = $j("#eduTable tbody tr").length;

        if (totalRows - checkedRows.length < 1) {
            checkedRows.remove();
            $j("#eduTable tbody").append(`
                <tr>
                    <td><input type="checkbox" name="eduCheck"></td>
                    <td>
                        <input name="educationList[0].startPeriod" type="month" class="eduPeriod"> ~<br>
                        <input name="educationList[0].endPeriod" type="month" class="eduPeriod">
                    </td>
                    <td>
                        <select name="educationList[0].division">
                            <option value="재학">재학</option>
                            <option value="중퇴">중퇴</option>
                            <option value="졸업">졸업</option>
                        </select>
                    </td>
                    <td>
                        <input name="educationList[0].schoolName" type="text"><br>
                        <select name="educationList[0].location">
                            <option value="서울">서울</option>
                            <option value="경기">경기</option>
                            <option value="지방">지방</option>
                        </select>
                    </td>
                    <td><input name="educationList[0].major" type="text"></td>
                    <td><input name="educationList[0].grade" type="text" class="gradeInput">/4.5</td>
                </tr>
            `);
        } else {
            checkedRows.remove();
        }
    });

    // 경력 삭제
    $j("#carDel").on("click", function() {
        var checkedRows = $j("#carTable tbody tr").filter(function() {
            return $j(this).find("input[type='checkbox']").is(":checked");
        });
        var totalRows = $j("#carTable tbody tr").length;

        if (totalRows - checkedRows.length < 1) {
            checkedRows.remove();
            $j("#carTable tbody").append(`
                <tr>
                    <td><input type="checkbox" name="carCheck"></td>
                    <td>
                        <input name="careerList[0].startPeriod" type="month" class="carPeriod"> ~<br>
                        <input name="careerList[0].endPeriod" type="month" class="carPeriod">
                        <br><span class="carError" style="color:red; font-size:12px;"></span>
                    </td>
                    <td><input name="careerList[0].compName" type="text"></td>
                    <td><input name="careerList[0].task" type="text" class="taskInput" placeholder="예) 개발팀/대리/팀장"></td>
                    <td><input name="careerList[0].location" type="text"></td>
                </tr>
            `);
        } else {
            checkedRows.remove();
        }
    });

    // 자격증 삭제
    $j("#cerDel").on("click", function() {
        var checkedRows = $j("#cerTable tbody tr").filter(function() {
            return $j(this).find("input[type='checkbox']").is(":checked");
        });
        var totalRows = $j("#cerTable tbody tr").length;

        if (totalRows - checkedRows.length < 1) {
            checkedRows.remove();
            $j("#cerTable tbody").append(`
                <tr>
                    <td><input type="checkbox" name="cerCheck"></td>
                    <td><input name="certificateList[0].qualifiName" type="text"></td>
                    <td><input name="certificateList[0].acquDate" type="month" class="acquDate"></td>
                    <td><input name="certificateList[0].organizeName" type="text"></td>
                </tr>
            `);
        } else {
            checkedRows.remove();
        }
    });

    // 저장버튼
    $j("#saveBt").on("click", function() {
        var birth = $j("input[name='birth']").val().trim();
        var email = $j("input[name='email']").val().trim();
        var addr  = $j("input[name='addr']").val().trim();

        if (birth === "") {
            alert("생년월일 입력해주세요.");
            $j("input[name='birth']").focus();
            return;
        } else if (email === "") {
            alert("이메일 입력해주세요.");
            $j("input[name='email']").focus();
            return;
        } else if (email.split("@").length !== 2) {
            alert("이메일 형식이 올바르지 않습니다. \n ex) example@com");
            $j("input[name='email']").focus();
            return;
        } else if (addr === "") {
            alert("주소 입력해주세요.");
            $j("input[name='addr']").focus();
            return;
        }

        var birthYear = birth.substring(0, 7);
        var periodValid = true;
        var eduValid = true;

        // 학력 검증
        $j("#eduTable tbody tr").each(function() {
            var row = $j(this);
            var fields = [
                { name: "startPeriod", msg: "학력 시작기간을 입력해주세요." },
                { name: "endPeriod",   msg: "학력 종료기간을 입력해주세요." },
                { name: "schoolName",  msg: "학교명을 입력해주세요." },
                { name: "major",       msg: "전공을 입력해주세요." },
                { name: "grade",       msg: "학점을 입력해주세요." }
            ];

            for (var i = 0; i < fields.length; i++) {
                if (row.find("[name*='" + fields[i].name + "']").val().trim() === "") {
                    alert(fields[i].msg);
                    row.find("[name*='" + fields[i].name + "']").focus();
                    eduValid = false;
                    return false;
                }
            }

            var startPeriod = row.find("[name*='startPeriod']").val().trim();
            if (startPeriod && startPeriod < birthYear) {
                row.find("[name*='startPeriod']").css("border", "1px solid red");
                periodValid = false;
            }

            var gradeVal = parseFloat(row.find("[name*='grade']").val());
            if (gradeVal < 0 || gradeVal > 4.5) {
                alert("학점은 0.0 ~ 4.5 사이 숫자로 입력해주세요.");
                row.find("[name*='grade']").focus();
                eduValid = false;
                return false;
            }
        });

        if (!eduValid) return;

        // 경력 검증
        var carValid = true;
        $j("#carTable tbody tr").each(function() {
            var row = $j(this);
            var carfields = [
                { name: "startPeriod", msg: "경력 근무 시작기간을 입력해주세요." },
                { name: "endPeriod",   msg: "경력 근무 종료기간을 입력해주세요." },
                { name: "compName",    msg: "회사명을 입력해주세요." },
                { name: "task",        msg: "부서/직급/직책을 입력해주세요." },
                { name: "location",    msg: "지역을 입력해주세요." }
            ];

            var hasAnyValue = carfields.some(function(f) {
                return row.find("[name*='" + f.name + "']").val().trim() !== "";
            });
            if (!hasAnyValue) return true;

            for (var i = 0; i < carfields.length; i++) {
                if (row.find("[name*='" + carfields[i].name + "']").val().trim() === "") {
                    alert(carfields[i].msg);
                    row.find("[name*='" + carfields[i].name + "']").focus();
                    carValid = false;
                    return false;
                }
            }

            var taskVal = row.find("[name*='task']").val();
            if (taskVal.split("/").length !== 3) {
                alert("부서/직급/직책 형식으로 입력해주세요.\n예) 개발팀/대리/팀장");
                row.find("[name*='task']").focus();
                carValid = false;
                return false;
            }

            var startPeriod = row.find("[name*='startPeriod']").val().trim();
            if (startPeriod && startPeriod < birthYear) {
                row.find("[name*='startPeriod']").css("border", "1px solid red");
                periodValid = false;
            }
        });

        if (!periodValid) {
            alert("재학/근무 시작기간이 생년월일보다 빠를 수 없습니다.");
            return;
        }

        if (!carValid) return;

        // 자격증 검증
        var cerValid = true;
        $j("#cerTable tbody tr").each(function() {
            var row = $j(this);
            var cerfields = [
                { name: "qualifiName",   msg: "자격증명을 입력해주세요." },
                { name: "acquDate",      msg: "자격증 취득일을 입력해주세요." },
                { name: "organizeName",  msg: "발행처를 입력해주세요." }
            ];

            var hasAnyValue = cerfields.some(function(f) {
                return row.find("[name*='" + f.name + "']").val().trim() !== "";
            });
            if (!hasAnyValue) return true;

            for (var i = 0; i < cerfields.length; i++) {
                if (row.find("[name*='" + cerfields[i].name + "']").val().trim() === "") {
                    alert(cerfields[i].msg);
                    row.find("[name*='" + cerfields[i].name + "']").focus();
                    cerValid = false;
                    return false;
                }
            }

            var acquDate = row.find("[name*='acquDate']").val().trim();
            if (acquDate && acquDate < birthYear) {
                row.find("[name*='acquDate']").css("border", "1px solid red");
                periodValid = false;
            }
        });

        if (!cerValid) return;
        if (!periodValid) {
            alert("재학/근무 시작기간 또는 자격증 취득일이 생년월일보다 빠를 수 없습니다.");
            return;
        }

        // index 재정렬
        $j("#eduTable tbody tr").each(function(i) {
            $j(this).find("[name*='educationList']").each(function() {
                var name = $j(this).attr("name");
                $j(this).attr("name", name.replace(/educationList\[\d*\]/, "educationList[" + i + "]"));
            });
        });

        $j("#carTable tbody tr").each(function(i) {
            $j(this).find("[name*='careerList']").each(function() {
                var name = $j(this).attr("name");
                $j(this).attr("name", name.replace(/careerList\[\d*\]/, "careerList[" + i + "]"));
            });
        });

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
                location.reload();
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
					<table border="1" align="center">
						<c:choose>
							<c:when test="${recruit.submit == 'Y'}">
								<tr>
									<td align="center">이름</td>
									<td>${name}</td>
									<td align="center">생년월일</td>
									<td>${recruit.birth}</td>
								</tr>
								<tr>
									<td align="center">성별</td>
									<td>${recruit.gender}</td>
									<td align="center">연락처</td>
									<td>${phone}</td>
								</tr>
								<tr>
									<td align="center">email</td>
									<td>${recruit.email}</td>
									<td align="center">주소</td>
									<td>${recruit.addr}</td>
								</tr>
								<tr>
									<td align="center">희망근무지</td>
									<td>${recruit.location}</td>
									<td align="center">근무형태</td>
									<td>${recruit.workType}</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td align="center">이름</td>
									<td><input type="hidden" name="name" value="${name}">${name}</td>
									<td>생년월일</td>
									<td><input type="date" name="birth"
										value="${recruit.birth}" autofocus></td>
								</tr>
								<tr>
									<td>성별</td>
									<td><select name="gender">
											<option value="남자"
												${recruit.gender == '남자' ? 'selected' : ''}>남자</option>
											<option value="여자"
												${recruit.gender == '여자' ? 'selected' : ''}>여자</option>
									</select></td>
									<td align="center">연락처</td>
									<td><input type="hidden" name="phone" value="${phone}">${phone}</td>
								</tr>
								<tr>
									<td align="center">email</td>
									<td><input type="email" name="email"
										value="${recruit.email}" class="emailInput"
										placeholder="예) example@com"> <span class="emailError"
										style="color: red; font-size: 12px;"></span></td>
									<td>주소</td>
									<td><input type="text" name="addr" value="${recruit.addr}">
									</td>
								</tr>
								<tr>
									<td>희망근무지</td>
									<td><select name="location">
											<option value="서울"
												${recruit.location == '서울' ? 'selected' : ''}>서울</option>
											<option value="전국"
												${recruit.location == '전국' ? 'selected' : ''}>전국</option>
									</select></td>
									<td>근무형태</td>
									<td><select name="workType">
											<option value="정규직"
												${recruit.workType == '정규직' ? 'selected' : ''}>정규직</option>
											<option value="계약직"
												${recruit.workType == '계약직' ? 'selected' : ''}>계약직</option>
									</select></td>
								</tr>

							</c:otherwise>

						</c:choose>
					</table> <br> <br> <c:choose>
						<c:when test="${recruit.submit == 'Y' || recruit.submit == 'S'}">
							<table border="1" align="center">
								<tr>
									<td>학력사항</td>
									<td>경력사항</td>
									<td>희망연봉</td>
									<td>희망근무지/근무형태</td>
								</tr>
								<tr>
									<td>대학교(${eduPeriod}) ${educationList[0].division}</td>
									<td>경력 ${carPeriod}</td>

									<td>회사내규에 따름</td>
									<td>${recruit.location}<br>${recruit.workType}</td>
								</tr>
							</table>
						</c:when>
					</c:choose>
					<h2>
						<strong>학력</strong>
					</h2>

					<button type="button" id="eduAdd">추가</button>
					<button type="button" id="eduDel">삭제</button> <br>

					<table border=1 id="eduTable">
						<thead>
							<tr>
								<c:if test="${recruit.submit != 'Y'}">
									<td></td>
								</c:if>
								<td>재학기간</td>
								<td>구분</td>
								<td>학교명(소재지)</td>
								<td>전공</td>
								<td>학점</td>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty educationList}">
									<c:forEach items="${educationList}" var="edu" varStatus="s">
										<c:choose>
											<c:when test="${recruit.submit == 'Y'}">
												<tr>
													<td>${edu.startPeriod}<br>~<br>${edu.endPeriod}</td>
													<td>${edu.division}</td>
													<td>${edu.schoolName}<br>${edu.location}</td>
													<td>${edu.major}</td>
													<td>${edu.grade}/4.5</td>
												</tr>
											</c:when>
											<c:otherwise>
												<tr>
													<td><input type="checkbox"> <input
														type="hidden" name="educationList[${s.index}].eduSeq"
														value="${edu.eduSeq}"></td>
													<td><input type="month"
														name="educationList[${s.index}].startPeriod"
														value="${edu.startPeriod}" class="eduPeriod"><br>~<br>
														<input type="month"
														name="educationList[${s.index}].endPeriod"
														value="${edu.endPeriod}" class="eduPeriod"></td>
													<td><select name="educationList[${s.index}].division">
															<option value="재학"
																${edu.division == '재학' ? 'selected' : ''}>재학</option>
															<option value="중퇴"
																${edu.division == '중퇴' ? 'selected' : ''}>중퇴</option>
															<option value="졸업"
																${edu.division == '졸업' ? 'selected' : ''}>졸업</option>
													</select></td>
													<td><input type="text"
														name="educationList[${s.index}].schoolName"
														value="${edu.schoolName}"><br> <select
														name="educationList[${s.index}].location">
															<option value="서울"
																${edu.location == '서울' ? 'selected' : ''}>서울</option>
															<option value="경기"
																${edu.location == '경기' ? 'selected' : ''}>경기</option>
															<option value="지방"
																${edu.location == '지방' ? 'selected' : ''}>지방</option>
													</select></td>
													<td><input type="text"
														name="educationList[${s.index}].major"
														value="${edu.major}"></td>
													<td><input type="text"
														name="educationList[${s.index}].grade"
														value="${edu.grade}" class="gradeInput">/4.5</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td><input type="checkbox"> <input type="hidden"
											name="educationList[0].eduSeq" value="${edu.eduSeq}">
										</td>
										<td><input type="month"
											name="educationList[0].startPeriod"
											value="${edu.startPeriod}" class="eduPeriod"><br>~<br>
											<input type="month" name="educationList[0].endPeriod"
											value="${edu.endPeriod}" class="eduPeriod"></td>
										<td><select name="educationList[0].division">
												<option value="재학">재학</option>
												<option value="중퇴">중퇴</option>
												<option value="졸업">졸업</option>
										</select></td>
										<td><input type="text" name="educationList[0].schoolName"
											value="${edu.schoolName}"><br> <select
											name="educationList[0].location">
												<option value="서울">서울</option>
												<option value="경기">경기</option>
												<option value="지방">지방</option>
										</select></td>
										<td><input type="text" name="educationList[0].major"
											value="${edu.major}"></td>
										<td><input type="number" name="educationList[0].grade"
											value="${edu.grade}" class="gradeInput"
											placeholder="예) 0 ~ 4.5 사이로 입력">/4.5</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table> <br>

					<h2>
						<strong>경력</strong>
					</h2>
					<button type="button" id="carAdd">추가</button>
					<button type="button" id="carDel">삭제</button> <br> <c:if
						test="${recruit.submit != 'Y' || not empty careerList}">
						<table border=1 id="carTable">
							<thead>
								<tr>
									<c:if test="${recruit.submit != 'Y'}">
										<td></td>
									</c:if>
									<td>근무기간</td>
									<td>회사명</td>
									<td>부서/직급/직책</td>
									<td>지역</td>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty careerList}">
										<c:forEach items="${careerList}" var="car" varStatus="k">
											<c:choose>
												<c:when test="${recruit.submit == 'Y'}">
													<tr>
														<td>${car.startPeriod}~<br> ${car.endPeriod}
														</td>
														<td>${car.compName}</td>
														<td>${car.task}</td>
														<td>${car.location}</td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td><input type="checkbox"> <input
															type="hidden" name="careerList[${k.index}].carSeq"
															value="${car.carSeq}"></td>
														<td><input name="careerList[${k.index}].startPeriod"
															type="month" value="${car.startPeriod}" class="carPeriod">
															~<br> <input name="careerList[${k.index}].endPeriod"
															type="month" value="${car.endPeriod}" class="carPeriod">
															<br><span class="carError" style="color:red; font-size:12px;"></span></td>
														<td><input name="careerList[${k.index}].compName"
															type="text" value="${car.compName}"></td>
														<td><input name="careerList[${k.index}].task"
															type="text" class="taskInput" value="${car.task}"
															placeholder="ex) 개발팀/대리/팀장"></td>
														<td><input name="careerList[${k.index}].location"
															type="text" value="${car.location}"></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td><input type="checkbox"> <input type="hidden"
												name="careerList[0].carSeq" value="${car.carSeq}"></td>
											<td><input name="careerList[0].startPeriod" type="month"
												class="carPeriod"> ~<br> <input
												name="careerList[0].endPeriod" type="month"
												class="carPeriod">
												<br><span class="carError" style="color:red; font-size:12px;"></span></td>
											<td><input name="careerList[0].compName" type="text">
											</td>
											<td><input name="careerList[0].task" type="text"
												class="taskInput" placeholder="예) 개발팀/대리/팀장"></td>
											<td><input name="careerList[0].location" type="text">
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</c:if> <br>

					<h2>
						<strong>자격증</strong>
					</h2>
					<button type="button" id="cerAdd">추가</button>
					<button type="button" id="cerDel">삭제</button> <br> <c:if
						test="${recruit.submit != 'Y' || not empty certificateList}">
						<table border=1 id="cerTable">
							<thead>
								<tr>
									<c:if test="${recruit.submit != 'Y'}">
										<td></td>
									</c:if>
									<td>자격증명</td>
									<td>취득일</td>
									<td>발행처</td>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty certificateList}">
										<c:forEach items="${certificateList}" var="cer" varStatus="w">
											<c:choose>
												<c:when test="${recruit.submit == 'Y'}">
													<tr>
														<td>${cer.qualifiName}</td>
														<td>${cer.acquDate}</td>
														<td>${cer.organizeName}</td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td><input type="checkbox"> <input
															type="hidden" name="certificateList[${w.index}].certSeq"
															value="${cer.certSeq}"></td>
														<td><input
															name="certificateList[${w.index}].qualifiName"
															type="text" value="${cer.qualifiName}"></td>
														<td><input
															name="certificateList[${w.index}].acquDate" type="month"
															value="${cer.acquDate}" class="acquDate"></td>
														<td><input
															name="certificateList[${w.index}].organizeName"
															type="text" value="${cer.organizeName}"></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td><input type="checkbox"> <input type="hidden"
												name="certificateList[0].certSeq" value="${cer.certSeq}"></td>
											<td><input name="certificateList[0].qualifiName"
												type="text"></td>
											<td><input name="certificateList[0].acquDate"
												type="month" class="acquDate"></td>
											<td><input name="certificateList[0].organizeName"
												type="text"></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</c:if> <br> <br>
					<button type="button" id="saveBt">저장</button>
					<button type="button" id="submitBt">제출</button> <br>
		</table>
	</form>
</body>
</html>