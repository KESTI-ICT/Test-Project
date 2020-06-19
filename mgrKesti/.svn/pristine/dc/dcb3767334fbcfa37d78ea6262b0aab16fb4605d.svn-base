$(function (){
	$("#strDate1").datepicker({
		  language : "ko", // 화면에 출력될 언어를 한국어로 설정한다.
		  defalutDate : $("#strDate1").val(getTimeStamp()), // 기본값으로 오늘 날짜를 입력한다. 기본값을 해제하려면 defaultDate 옵션을 생략한다.
		  minDate: 0,
		  onSelect: function(dateText, inst){
				$("#endDate1").val(dateText);
				
				if($("#chk_undef1").is(":checked") == true){
					$("input:checkbox[id='chk_undef1']").prop("checked", false);
				}
			}
		});

	$("#endDate1").datepicker({
		  language : "ko", // 화면에 출력될 언어를 한국어로 설정한다.
		  defalutDate : $("#endDate1").val($("#strDate1").val()), // 기본값으로 오늘 날짜를 입력한다. 기본값을 해제하려면 defaultDate 옵션을 생략한다.
		  minDate: 0,
		  onSelect: function(dateText, inst){
				var strDate = $("#strDate1").val();
				var tmp = new Date(dateText) - new Date(strDate);
				if(tmp < 0 ){
					alert("종료날짜가 시작날짜보다 이전일이 될 수 없습니다.");
					$("#endDate2").val(strDate);
					return;
				}
			}
		});

	$("#strDate2").datepicker({
		  language : "ko", // 화면에 출력될 언어를 한국어로 설정한다.
		  defalutDate : $("#strDate2").val(getTimeStamp()), // 기본값으로 오늘 날짜를 입력한다. 기본값을 해제하려면 defaultDate 옵션을 생략한다.
		  minDate: 0,
		  onSelect: function(dateText, inst){
				$("#endDate2").val(dateText);
				
				if($("#chk_undef2").is(":checked") == true){
					$("input:checkbox[id='chk_undef2']").prop("checked", false);
				}
			}
		});

	$("#endDate2").datepicker({
		  language : "ko", // 화면에 출력될 언어를 한국어로 설정한다.
		  defalutDate : $("#endDate2").val($("#strDate2").val()), // 기본값으로 오늘 날짜를 입력한다. 기본값을 해제하려면 defaultDate 옵션을 생략한다.
		  minDate: 0,
		  onSelect: function(dateText, inst){
				var strDate = $("#strDate2").val();
				var tmp = new Date(dateText) - new Date(strDate);
				if(tmp < 0 ){
					alert("종료날짜가 시작날짜보다 이전일이 될 수 없습니다.");
					$("#endDate2").val(strDate);
					return;
				}

			}
		});

	
	$("#chk_undef1").click(function(){
		if($("#chk_undef1").is(":checked") == true){
			$("#strDate1").val(getTimeStamp());
			$("#endDate1").val("2020-12-31");
		}
	});
	
	$("#chk_undef2").click(function(){
		if($("#chk_undef2").is(":checked") == true){
			$("#strDate2").val(getTimeStamp());
			$("#endDate2").val("2020-12-31");
		}
	});
});

//조회 버튼 클릭시 처리
function fn_search(){
	//초기화
	$("#data").html("");
	var authId = $("#authId").val();

	$.ajax({
		type : "post",
		url : "/mgrKesti/pgtInfo/projectListLookup.do",
		//data : {t1 : "Hello", t2 : "World"},
		dataType : "json",
		success : function(result) {
			var listHtml = "";
			if(result.resultList.length > 0){
				for(var i=0; i < result.resultList.length; i++){
					listHtml += "<tr>";
					listHtml += "<td scope='row' style='text-align:left;'>" + result["resultList"][i].projectName + "</td>";
					listHtml += "<td>" + result["resultList"][i].startDate + " ~ " + result["resultList"][i].endDate +"</td>";
					listHtml += "<td>" + result["resultList"][i].customerName + "</td>";
					listHtml += "<td>" + result["resultList"][i].agentName + "</td>";
					listHtml += "<td>" + result["resultList"][i].suggestionType + "</td>";
					listHtml += "<td>" + result["resultList"][i].predictMm + "</td>";
					listHtml += "<td>" + result["resultList"][i].mmTotal +	"</td>";

					if(authId == 'A0002'){	//관리자 인 경우
						listHtml += "<td>"
						listHtml += "<input type='button' id='btn_modify' value='수정' " +
								"onclick=" + "fn_modifyForm(" +"\'"+ result["resultList"][i].pmsSeq +"\')\>"
						listHtml += "<input type='button' value='삭제'onclick="+"'fn_delete(\""+ result["resultList"][i].pmsSeq + "\")'>"
						listHtml += "</td>"
					}else{
						listHtml += "<td></td>";
					}

					listHtml += "</tr>";
				}
				//$("#data").append(listHtml);
			}else{
				listHtml += "<tr>";
				listHtml += "<td colspan='8'>조회 된 내역이 없습니다.</td>";
				listHtml += "</tr>";
				
			}
			$("#data").append(listHtml);

		},error:function(request, status, error){

		}, beforeSend:function() {
			//wrapWindowByMask();0
		}, complete:function() {
			//closeWindowByMask();
		}
	});
}

//삭제버튼 클릭시 처리
function fn_delete(pmsSeq){
	if(pmsSeq != ""){
		$("#pmsSeq").val(pmsSeq);
		
		if(!confirm("프로젝트를 삭제 하시겠습니까?")) return;
		fn_submit(lForm, "/mgrKesti/pgtInfo/deleteProjectInfo.do", "_self", "post");
	}
}

//수정 버튼 클릭시 처리
function fn_modifyForm(pmsSeq){
	//레이어 초기화
	$("#pmsSeq").val("");
	$("#projectName2").val("");
	$("#strDate2").val("");
	$("#endDate2").val("");
	$("#projectTypeCode2 option:eq(0)").prop("selected", true);
	$("#predictMm2 option:eq(0)").prop("selected", true);
	$("#customerId2 option:eq(0)").prop("selected", true);
	$("#agentId2 option:eq(0)").prop("selected", true);
	$("#suggestionTypeCode2 option:eq(0)").prop("selected", true);

	$.ajax({
		type : "post",
		url : "/mgrKesti/pgtInfo/selectProjectInfo.do",
		data : {pmsSeq : pmsSeq},
		dataType : "json",
		success : function(result){
			//화면 셋팅
			$("#pmsSeq").val(result['projectInfo'].pmsseq);
			$("#projectName2").val(result['projectInfo'].projectname);
			$("#strDate2").val(result['projectInfo'].startdate);
			$("#endDate2").val(result['projectInfo'].enddate);
			$("#projectTypeCode2").val(result['projectInfo'].projecttypecode).prop("selected", true);
			$("#predictMm2").val(result['projectInfo'].predictmm);
			$("#customerId2").val(result['projectInfo'].customerid).prop("selected", true);
			$("#agentId2").val(result['projectInfo'].agentid).prop("selected", true);
			$("#suggestionTypeCode2").val(result['projectInfo'].suggestiontypecode).prop("selected", true);
			
			pushLayer2();
		},error:function(request, status, error){

		}, beforeSend:function() {
			//wrapWindowByMask();0
		}, complete:function() {
			//closeWindowByMask();
		}
	});
}

//로그아웃 버튼 클릭시 처리
function fn_logout(){
	$("#userId").val("");
	$("#userPw").val("");

	fn_submit(lForm, "/mgrKesti/logInfo/login.do", "_self", "post");
}

//등록버튼 클릭시 처리(레이어팝업 오픈)
function pushLayer1(){
	//초기화
	$("#projectName1").val("");
	
	$("#strDate1").val(getTimeStamp())
	$("#endDate1").val(getTimeStamp())
	
	$("#projectTypeCode1 option:eq(0)").prop("selected", true);
	$("#customerId1 option:eq(0)").prop("selected", true);
	$("#agentId1 option:eq(0)").prop("selected", true);
	$("#suggestionTypeCode1 option:eq(0)").prop("selected", true);
	$("#predictMm1").val("");
	
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
}

//레이어팝업 오픈
function pushLayer2(){
	var $width=parseInt($("#lay_pop2").css("width"));
	var $height=parseInt($("#lay_pop2").css("height"));
	var left=($(window).width()-$width)/2;
	var sctop=$(window).scrollTop()*2;
	var top=($(window).height()-$height+sctop)/2;
	//var height=document.getElementsByTagName("body")[0].scrollHeight;
	$("#lay_pop2").css("left",left);
	$("#lay_pop2").css("top",top);
	$("#lay_pop2").css("display","block");
	$("#lay_pop2").css("z-index","555");
	$("#all_body2").css("display","block");
	$("#all_body2").css("width",$(window).width());
	$("#all_body2").css("height","887");
}

//저장 버튼 클릭시 처리
function layerSave(lay1, lay2, num){
	if($("#predictMm" + num).val() == ""){
		$("#predictMm1" + num).val(0)
	}

	//validation 체크
	if($("#projectName" + num).val() == ""){
		alert("프로젝트명을 입력해주세요");
		return;
	}

	if($("#projectTypeCode"+num+" option:selected").val() == ""){
		alert("프로젝트유형을 선택 해주세요");
		return;
	}

	if($("#customerId"+num+" option:selected").val() == ""){
		alert("고객사를 선택 해주세요");
		return;
	}

	if($("#agentId"+num+" option:selected").val() == ""){
		alert("수행사를 선택 해주세요");
		return;
	}

	if($("#suggestionTypeCode"+num+" option:selected").val() == ""){
		alert("계약현황을 선택 해주세요");
		return;
	}
	
	//selectBox req 셋팅
	$("#projectType"+num).val($("#projectTypeCode"+num+" option:selected").text());
	$("#customerName"+num).val($("#customerId"+num+" option:selected").text());
	$("#agentName"+num).val($("#agentId"+num+" option:selected").text());
	$("#suggestionType"+num).val($("#suggestionTypeCode"+num+" option:selected").text());
	
	$("#"+lay1).css("display", "none");
	$("#"+lay2).css("display", "none");
	
	if(num == "1"){
		//insert 플로우
		fn_submit(lForm, "/mgrKesti/pgtInfo/insertProjectInfo.do", "_self", "post");	//등록
	}else{
		//update 플로우
		fn_submit(lForm, "/mgrKesti/pgtInfo/updateProjectInfo.do", "_self", "post");	//수정
	}
}

function layerClose(lay1,lay2){
	$("#"+lay1).css("display", "none");
	$("#"+lay2).css("display", "none");
}

//페이징 선택
function fn_searchPage(pageIndex){
	$("#pageIndex").val(pageIndex);
	fn_submit(lForm, "/mgrKesti/pgtInfo/projectLookup.do", "_self", "post");
}