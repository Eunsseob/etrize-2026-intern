function idCheck(idValue) {
	if(idValue === ""){
		alert("아이디를 입력하세요");
		frm.id.focus();
		return;
	}
	url = "idCheck.jsp?userId=" + idValue;;
	window.open(url, "idcheck", "width=300, height=150");
}