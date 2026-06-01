<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>입사지원 로그인</title>
</head>
<script type="text/javascript">
    $j(document).ready(function() {
    	$j("#submit").on("click", function() {
    	    var param = $j('.login :input').serialize();

    	    $j.ajax({
    	        url: "/userLogin.do",
    	        type: "POST",
    	        data: param,
    	        success: function(res) {
    	            if (res === "success") {
    	                location.href = "/recruit/main.do";
    	            } else {
    	                signupProcess(param);
    	            }
    	        }
    	    });
    	});

    	function signupProcess(param) {
    	    // 3. 회원가입 진행
    	    $j.ajax({
    	        url: "/userSignup.do",
    	        type: "POST",
    	        data: param,
    	        success: function(res) {
    	            if (res === "duplicated") {
    	                alert("이미 사용 중인 번호입니다. 다른 번호를 입력해주세요.");
    	            } else if (res === "signup_success") {
    	                alert("가입 성공!");
    	                location.href = "/recruit/main.do";
    	            }
    	        }
    	    });
    	}
    });
    
    const autoHyphen = (target) => {
        target.value = target.value
            .replace(/[^0-9]/g, '')
            .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/g, "$1-$2-$3")
            .replace(/(\-{1,2})$/g, "");
    }
</script>
<body>
    <h2>입사지원 로그인</h2>
		<form class = "login">
				<table border="1" align="center">
					<tr>
						<th>이름</th>
						<th><input type="text" name="name" value="${recruit.name}" maxlength="13"></th>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<th><input type="text" name="phone" value="${recruit.phone}", oninput="autoHyphen(this)"></th>
					</tr>
					<tr>
						<th colspan=2>	
							<input id="submit" type="button" value="입사지원">
						</th>
					</tr>
				</table>
		</form>
</body>
</html>