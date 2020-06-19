//input 메세지 노출 제어
function inputTextVal(id, focusid){
	$("#"+id).focus();
	$("#"+focusid+"_chk").css("display", "");
}

// 예시 : fn_submit(insertForm, "/nhmin/aboutus/insertAboutusForm.nh", "_self", "post");
function fn_submit(frm, actionName, targetLayer, methode){
	frm.method = methode;
	frm.target = targetLayer;
	frm.action = actionName;
	frm.submit();
}

//유효성 체크
function fn_validation(id, msg) {
	var value = $("#" + id).val();
	if (value == "") {
		alert(msg);
		return false;
	}
	
	return true;
}

function getTimeStamp(){
	var d = new Date();
	var s = leadingZeros(d.getFullYear(), 4) + "-" + leadingZeros(d.getMonth() + 1, 2) + "-" + leadingZeros(d.getDate(), 2);
	return s;
}

function leadingZeros(n, digits){
	var zero = "";
	n = n.toString();

	if(n.length < digits){
		for (i=0; i<digits - n.length; i++)
			zero += "0";
	}
	
	return zero + n;
}

function fn_onlyNumber(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if(( keyID >=48 && keyID <= 57 ) || ( keyID >=96 && keyID <= 105 ) || keyID == 8 || keyID == 9 || keyID == 46 || keyID == 37 || keyID == 39 ){
		return;
	}else{
		return false;
	}
}

function onlyNum(str){
	var inText = str.value;
	var ret;

	for(var i=0; i<inText.length; i++){
		ret = inText.charCodeAt(i);
		if (!((ret > 47) && (ret < 58))){
			alert("숫자만 입력 가능합니다.");
			str.value = "";
			str.focus();
			return false;
		}
	}
	return true;
}

function onlyNum2(str){
	var inText = str.value;
	var ret;

	for(var i=0; i<inText.length; i++){
		ret = inText.charCodeAt(i);
		if((event.keyCode > 57 || event.keyCode < 48) && (event.keyCode > 105 || event.keyCode < 96)
				&& (event.keyCode > 8 || event.keyCode < 8) && (event.keyCode > 46 || event.keyCode < 46)){
			str.value = "";
			alert("숫자만 입력 가능합니다.");
			str.focus();
			return false;
		}
	}
	return true;
}

function onlyNum3(str){
	var inText = str.value;
	var ret;

	for(var i=0; i<inText.length; i++){
		ret = inText.charCodeAt(i);
		if((event.keyCode > 57 || event.keyCode < 48) && (event.keyCode > 105 || event.keyCode < 96)
				&& (event.keyCode > 8 || event.keyCode < 8) && (event.keyCode > 46 || event.keyCode < 46)){
			str.value = inText.substring(0, inText.length-1);
			alert("숫자만 입력 가능합니다.");
			str.focus();
			return false;
		}
	}
	return true;
}

function allClear(frm){
	for(var i = 0 ; i < frm.length ; i++ ){
		if(frm.elements[i].type == 'text'){
			frm.elements[i].value = "";
		}else if(frm.elements[i].type == 'password'){
			frm.elements[i].value = "";
		}
	}
}

//byte수 체크
function fnChkByte(obj, maxByte){
	var str      = obj.value;
	var str_len  = str.length;
	var rbyte    = 0;
	var rlen     = 0;
	var one_char = "";
	var str2     = "";

	for(var i=0; i<str_len; i++){
		one_char = str.charAt(i);
		if(escape(one_char).length > 4){
			rbyte += 2;	//한글2Byte
		}else{
			rbyte ++;	//영문 등 나머지 1Byte
		}

		if(rbyte <= maxByte){
			rlen = i+1;	//return할 문자열 갯수
		}
	}

	if(rbyte > maxByte){
		alert("한글 "+(maxByte/2)+"자 / 영문 "+maxByte+"자를 초과 입력할 수 없습니다.");

		str2 = str.substr(0,rlen);	//문자열 자르기
		obj.value = str2;

		frm.subway.value =obj.value;
		fnChkByte(obj, maxByte);
	}
}