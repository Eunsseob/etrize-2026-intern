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
            var $frm = $j('.login :input');
            var param = $frm.serialize();

            $j.ajax({
                url: "/userSignup.do",
                type: "POST",
                data: param,
                success: function(data, textStatus, jqXHR) {
                    location.href = "/recruit/main.do";
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert("실패");
                }
            });
        });
    });
</script>
<body>
    <h2>입사지원 로그인</h2>
		<form class = "login">
				<table border="1" align="center">
					<tr>
						<th>이름</th>
						<th><input type="text" name="name" value="${recruit.name}"></th>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<th><input type="text" name="phone" value="${recruit.phone}"></th>
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