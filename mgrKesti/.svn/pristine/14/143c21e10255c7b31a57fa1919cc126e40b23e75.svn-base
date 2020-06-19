$(function (){
});

//로그인버튼 클릭시 처리
function fn_join(){
	//경고문구 숨기기
	$("#id_chk").css("display", "none");
	$("#pw_chk").css("display", "none");
	
	//validation 체크
	var userId  = $("#userId").val().trim();
	var userPw  = $("#userPw").val().trim();
	
	if(userId == ""){
		inputTextVal("userId", "id");
		return;
	}
	
	if(userPw == ""){
		inputTextVal("pw_chk", "pw");
		return;
	}
	
	//프로젝트리스트 화면 이동
	fn_submit(lForm, "/mgrKesti/logInfo/adminLogin.do", "_self", "post");
	
	//var frm = document.lForm;
	//frm.action = "/mgrKesti/logInfo/adminLogin.do";
	//frm.submit();
}