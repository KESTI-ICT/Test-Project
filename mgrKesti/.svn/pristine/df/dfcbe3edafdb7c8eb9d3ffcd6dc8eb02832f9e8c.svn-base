$(function (){
	var v_year = new Date().getFullYear();
	$("#mmYear1").val(v_year);
});


//로그아웃 버튼 클릭시 처리
function fn_logout(){
	$("#userId").val("");
	$("#userPw").val("");

	fn_submit(lForm, "/mgrKesti/logInfo/login.do", "_self", "post");
}

//수정 or 삭제 버튼 클릭시 처리
function fn_saveMm(num, pmsSeqId, managerId, pmsUserRoleCode){
	if(pmsSeqId != "" && managerId != "" && pmsUserRoleCode != ""){
		if(num == "1"){
			$.ajax({
				type : "post",
				url : "/mgrKesti/predictInfo/predictMmLookup.do",
				data : {pmsSeqId : pmsSeqId, managerId : managerId, pmsUserRoleCode : pmsUserRoleCode},
				dataType : "json",
				success : function(result){
					//화면 셋팅
					$("#pmsSeqId").val(result['predictMmInfo'].pmsseqid);
					$("#pmsUserRoleCode2").val(result['predictMmInfo'].pmsuserrolecode).prop("selected", true);
					$("#managerId2").val(result['predictMmInfo'].managerid);
					$("#mmYear2").val(result['predictMmInfo'].mmyear);
					$("#pmsUserRole2").val(result['predictMmInfo'].projectuserroledetail);
					$("#mmOne2").val(result['predictMmInfo'].mmone);
					$("#mmTwo2").val(result['predictMmInfo'].mmtwo);
					$("#mmThree2").val(result['predictMmInfo'].mmthree);
					$("#mmFour2").val(result['predictMmInfo'].mmfour);
					$("#mmFive2").val(result['predictMmInfo'].mmfive);
					$("#mmSix2").val(result['predictMmInfo'].mmsix);
					$("#mmSeven2").val(result['predictMmInfo'].mmseven);
					$("#mmEight2").val(result['predictMmInfo'].mmeight);
					$("#mmNine2").val(result['predictMmInfo'].mmnine);
					$("#mmTen2").val(result['predictMmInfo'].mmten);
					$("#mmEleven2").val(result['predictMmInfo'].mmeleven);
					$("#mmTwelve2").val(result['predictMmInfo'].mmtwelve);
					
					pushLayer(2);
				},error:function(request, status, error){

				}, beforeSend:function() {
					//wrapWindowByMask();0
				}, complete:function() {
					//closeWindowByMask();
				}
			});
		}else{
			//$("#pmsSeqId").val(pmsSeqId);
			$("#managerId").val(managerId);
			$("#pmsUserRoleCode").val(pmsUserRoleCode);
			
			if(!confirm("프로젝트를 삭제 하시겠습니까?")) return;
			fn_submit(lForm, "/mgrKesti/predictInfo/deletePredictMmInfo.do", "_self", "post");
		}
	}
	
}

//저장 버튼 클릭시 처리
function layerSave(lay1, lay2, num){
	/*if($("#predictMm2").val() == ""){
		$("#predictMm1").val(0)
	}*/

	
	if($("#mmOne"+num).val() == ""){
		$("#mmOne"+num).val(0)
	}
	
	if($("#mmTwo"+num).val() == ""){
		$("#mmTwo"+num).val(0)
	}
	
	if($("#mmThree"+num).val() == ""){
		$("#mmThree"+num).val(0)
	}
	
	if($("#mmFour"+num).val() == ""){
		$("#mmFour"+num).val(0)
	}
	
	if($("#mmFive"+num).val() == ""){
		$("#mmFive"+num).val(0)
	}
	
	if($("#mmSix"+num).val() == ""){
		$("#mmSix"+num).val(0)
	}
	
	if($("#mmSeven"+num).val() == ""){
		$("#mmSeven"+num).val(0)
	}
	
	if($("#mmEight"+num).val() == ""){
		$("#mmEight"+num).val(0)
	}
		
	if($("#mmNine"+num).val() == ""){
		$("#mmNine"+num).val(0)
	}
	
	if($("#mmTen"+num).val() == ""){
		$("#mmTen"+num).val(0)
	}
	
	if($("#mmEleven"+num).val() == ""){
		$("#mmEleven"+num).val(0)
	}
	
	if($("#mmTwelve"+num).val() == ""){
		$("#mmTwelve"+num).val(0)
	}
	

	if($("#managerId"+num).val() == ""){
		alert("담당자를 입력 해주세요");
		return;
	}
	
	if($("#mmYear"+num).val() == ""){
		alert("기준년도를 입력 해주세요");
		return;
	}

	if($("#pmsUserRoleCode"+num+" option:selected").val() == ""){
		alert("담당자역할을 선택 해주세요");
		return;
	}
	
	$("#"+lay1).css("display", "none");
	$("#"+lay2).css("display", "none");
	
	if(num == 1){
		//insert 플로우
		fn_submit(lForm, "/mgrKesti/predictInfo/insertPredictMmDetail.do", "_self", "post");	//수정
	}else{
		$("#managerId").val($("#managerId2").val());
		$("#pmsUserRoleCode").val($("#pmsUserRoleCode2 option:selected").val());
		
		//update 플로우
		fn_submit(lForm, "/mgrKesti/predictInfo/updatePredictMmInfo.do", "_self", "post");	//수정
	}
	
}

//레이어팝업 오픈
function pushLayer(num){
	var $width=parseInt($("#lay_pop"+num).css("width"));
	var $height=parseInt($("#lay_pop"+num).css("height"));
	var left=($(window).width()-$width)/2;
	var sctop=$(window).scrollTop()*2;
	var top=($(window).height()-$height+sctop)/2;
	//var height=document.getElementsByTagName("body")[0].scrollHeight;
	$("#lay_pop"+num).css("left",left);
	$("#lay_pop"+num).css("top",top);
	$("#lay_pop"+num).css("display","block");
	$("#lay_pop"+num).css("z-index","555");
	$("#all_body"+num).css("display","block");
	$("#all_body"+num).css("width",$(window).width());
	$("#all_body"+num).css("height","887");
}

//레이어팝업 닫기
function layerClose(lay1,lay2, num){
	
	//초기화
	$("#pmsUserRoleCode"+num+" option:eq(0)").prop("selected", true);
	
	$("#mmYear1"+num).val(2020);
	$("#mmYear"+num).val("");
	$("#pmsUserRole"+num).val("");
	$("#mmOne"+num).val(0);
	$("#mmTwo"+num).val(0);
	$("#mmThree"+num).val(0);
	$("#mmFour"+num).val(0);
	$("#mmFive"+num).val(0);
	$("#mmSix"+num).val(0);
	$("#mmSeven"+num).val(0);
	$("#mmEight"+num).val(0);
	$("#mmNine"+num).val(0);
	$("#mmTen"+num).val(0);
	$("#mmEleven"+num).val(0);
	$("#mmTwelve"+num).val(0);
	
	if(num == 1){
		var v_year = new Date().getFullYear();
		$("#mmYear1").val(v_year);
	}else{
		
	}
	
	
	$("#"+lay1).css("display", "none");
	$("#"+lay2).css("display", "none");
}

function fn_historyBack(){
	$("#mmYear2").val(0);
	$("#managerId2").val("");
	$("#pmsUserRole2").val("");
	$("#mmOne2").val(0);
	$("#mmTwo2").val(0);
	$("#mmThree2").val(0);
	$("#mmFour2").val(0);
	$("#mmFive2").val(0);
	$("#mmSix2").val(0);
	$("#mmSeven2").val(0);
	$("#mmEight2").val(0);
	$("#mmNine2").val(0);
	$("#mmTen2").val(0);
	$("#mmEleven2").val(0);
	$("#mmTwelve2").val(0);
	fn_submit(lForm, "/mgrKesti/predictInfo/predictMmListLookup.do", "_self", "post");	//수정
}

//페이징 선택
function fn_searchPage(pageIndex){
	$("#pageIndex").val(pageIndex);
	fn_submit(lForm, "/mgrKesti/predictInfo/predictMmDetailListLookup.do", "_self", "post");
}
