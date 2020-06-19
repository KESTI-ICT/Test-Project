$(function (){
});

//로그아웃 버튼 클릭시 처리
function fn_logout(){
	$("#userId").val("");
	$("#userPw").val("");

	fn_submit(lForm, "/mgrKesti/logInfo/login.do", "_self", "post");
}

/*function fn_saveMm(){
	//validation 체크
	if($("#projectName1").val() == ""){
		alert("프로젝트명을 입력해주세요");
		return;
	}
	
	if($("#mmYear1").val() == ""){
		alert("기준년도를 입력해주세요");
		return;
	}
	
	if($("#managerId1").val() == ""){
		alert("담당자를 입력해주세요");
		return;
	}
	
	if($("#pmsUserRoleCode1 option:selected").val() == ""){
		alert("담당자역할을 선택 해주세요");
		return;
	}
	
	//$("#pmsUserRoleCode1").attr("disabled", "");
	fn_submit(lForm, "/mgrKesti/predictInfo/insertPredictMmInfo.do", "_self", "post");
	
	var params = $("#lForm").serialize();
	
	$.ajax({
		type : "post",
		url : "/mgrKesti/predictInfo/insertPredictMmInfo.do",
		data : params,
		dataType : "json",
		success : function(result){
			alert(result);
			
			layerClose('lay_pop1', 'all_body1');
			
			fn_submit(lForm, "/mgrKesti/predictInfo/predictMmListLookup.do", "_self", "post");
		},error:function(request, status, error){

		}, beforeSend:function() {
			//wrapWindowByMask();0
		}, complete:function() {
			//closeWindowByMask();
		}
	});
}*/

//수정 or 삭제 버튼 클릭시 처리
/*function fn_saveMm(num, pmsSeqId, managerId, pmsUserRoleCode){
	if(pmsSeqId != "" && managerId != "" && pmsUserRoleCode != ""){
		if(num == "1"){
			$.ajax({
				type : "post",
				url : "/mgrKesti/predictInfo/predictMmLookup.do",
				data : {pmsSeqId : pmsSeqId, managerId : managerId, pmsUserRoleCode : pmsUserRoleCode},
				dataType : "json",
				success : function(result){
					//화면 셋팅
					$("#pmsUserRoleCode2").val(result['predictMmInfo'].pmsuserrolecode).prop("selected", true);
					
					$("#projectName").val(result['predictMmInfo'].projectname);
					$("#mmYear").val(result['predictMmInfo'].mmyear);
					$("#pmsUserRole").val(result['predictMmInfo'].projectuserroledetail);
					$("#mmOne").val(result['predictMmInfo'].mmone);
					$("#mmTwo").val(result['predictMmInfo'].mmtwo);
					$("#mmThree").val(result['predictMmInfo'].mmthree);
					$("#mmFour").val(result['predictMmInfo'].mmfour);
					$("#mmFive").val(result['predictMmInfo'].mmfive);
					$("#mmSix").val(result['predictMmInfo'].mmsix);
					$("#mmSeven").val(result['predictMmInfo'].mmseven);
					$("#mmEight").val(result['predictMmInfo'].mmeight);
					$("#mmNine").val(result['predictMmInfo'].mmnine);
					$("#mmTen").val(result['predictMmInfo'].mmten);
					$("#mmEleven").val(result['predictMmInfo'].mmeleven);
					$("#mmTwelve").val(result['predictMmInfo'].mmtwelve);
					
					pushLayer();
				},error:function(request, status, error){

				}, beforeSend:function() {
					//wrapWindowByMask();0
				}, complete:function() {
					//closeWindowByMask();
				}
			});
		}else{
			$("#pmsSeqId").val(pmsSeqId);
			$("#managerId").val(managerId);
			$("#pmsUserRoleCode").val(pmsUserRoleCode);
			
			if(!confirm("프로젝트를 삭제 하시겠습니까?")) return;
			fn_submit(lForm, "/mgrKesti/predictInfo/deletePredictMmInfo.do", "_self", "post");
		}
	}
	
}*/

//레이어팝업 오픈
/*function pushLayer(){
	var $width=parseInt($("#lay_pop1").css("width"));
	var $height=parseInt($("#lay_pop1").css("height"));
	var left=($(window).width()-$width)/2;
	var sctop=$(window).scrollTop()*2;
	var top=($(window).height()-$height+sctop)/2;
	//var height=document.getElementsByTagName("body")[0].scrollHeight;
	$("#lay_pop1").css("left",left);
	$("#lay_pop1").css("top",top);
	$("#lay_pop1").css("display","block");
	$("#lay_pop1").css("z-index","555");
	$("#all_body1").css("display","block");
	$("#all_body1").css("width",$(window).width());
	$("#all_body1").css("height","887");
}*/

//레이어팝업 닫기
/*function layerClose(lay1,lay2){
	
	$("#"+lay1).css("display", "none");
	$("#"+lay2).css("display", "none");
}*/

//상세화면 이동
function fn_projectDetail(pmsSeqId){
	$("#pmsSeqId").val(pmsSeqId);
	fn_submit(lForm, "/mgrKesti/predictInfo/predictMmDetailListLookup.do", "_self", "post");
}

//페이징 선택
function fn_searchPage(pageIndex){
	$("#pageIndex").val(pageIndex);
	fn_submit(lForm, "/mgrKesti/predictInfo/predictMmListLookup.do", "_self", "post");
}