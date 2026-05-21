<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>userSignup</title>
</head>
<!-- 아이디 중복 확인 조회  -->
<script type="text/javascript">

$j(document).ready(function(){

    $j("#submit").on("click", function(){
        var $frm = $j('.Idcheck :input');
        var param = $frm.serialize();

        $j.ajax({
            url: "/user/userIdcheck.do",
            dataType: "json",
            type: "POST",
            data: param,
            success: function(data, textStatus, jqXHR) { 
                if(data === 1) {                         
                    alert("아이디 사용 불가능");
                    idCheckFlag = 0;
                } else if(data === 0) {
                    alert("아이디 사용 가능");
                    idCheckFlag = 1;
                }
            },                                             
            error: function(jqXHR, textStatus, errorThrown) {
                alert("실패");
            }
        });
    });
});
</script>
<body>
<form class="Idcheck" action="/user/userSignup.do" method="post">>
	<table align="center">
	<tr>
		<td align="left">
			<a href="/board/boardList.do">List</a>
		</td>
	</tr>
	<tr>
		<td>
			<table border ="1"> 
					<tr>
						<td align="center">id</td>
						<td>
							<input name="userId" id="userId" type="text" size="15" value="${user.userId}">
							<input id="submit" type="button" value="중복확인">
						</td>
					</tr>
					<tr>
						<td align="center">pw</td>
						<td>
							<input name="userPw" id="userPw" type="password" size="15"> 
						</td>
					</tr>
					<tr>
						<td align="center">pw check</td>
						<td>
							<input id="userPwCheck" type="password" size="15"> 
						</td>
					</tr>
					<tr>
						<td align="center">name</td>
						<td>
							<input name="userName" type="text" size="15" value="${user.userName}"> 
						</td>
					</tr>
					<tr>
						<td align="center">phone</td>
						<td>
							<select name="userPhone1">
							<option value="010" selected>010
							<option value="011">011
							<option value="012">012
							<option value="019">019						
							</select>
							- <input name="userPhone2" type="text" size="4" maxlength="4"> 
							- <input name="userPhone3" type="text" size="4" maxlength="4"> 	
						</td>
					</tr>
					<tr>
						<td align="center">postNo</td>
						<td>
							<input name="userAddr1" type="text" size="15" value="${user.userPostNo}"> 
						</td>
					</tr>
					<tr>
						<td align="center">address</td>
						<td>
							<input name="userAddr2" type="text" size="30" value="${user.userAddress}"> 
						</td>
					</tr>
					<tr>
						<td align="center">company</td>
						<td>
							<input name="userCompany" type="text" size="15" value="${user.userCompany}"> 
						</td>
					</tr>
				</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<button type="submit">join</button>
		</td>
	</tr>
</table>
</form>	

</body>
</html>