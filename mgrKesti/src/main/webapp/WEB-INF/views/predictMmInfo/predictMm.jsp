<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
* @Class Name : 
* @Description : 
* @Modification Information
*
*   수정일         수정자                   수정내용
*  -------    --------    ---------------------------
*  2020.03.30            최초 생성
*
* author 실행환경 개발팀
* since 2020.03.31
* 
* Copyright (C) 2020 by Korea Environmental Science All right reserved.
*/
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/views/typelink.jsp" %>

<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/sample.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/common/common.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/common/board.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/predictMmInfo/predictMm.css'/>"/>

<%-- <script type="text/javascript" src="<c:url value="/resources/js/common/jquery.form2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common/jquery.MetaData.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common/jquery.MultiFile.js"/>"></script> --%>

<script type="text/javascript" src="<c:url value="/resources/js/predictMmInfo/predictMm.js"/>"></script>

<script type="text/javaScript" language="javascript" defer="defer"></script>

<script type="text/javascript">
$(document).ready(function(){
});
</script>
</head>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<form id="lForm" name="lForm" method="post">
	<!-- gnb영역  -->
	<jsp:include page="/WEB-INF/views/inc/gnb.jsp" flush="true" />
	<!-- 로그인 정보 -->
	<input type="hidden" id="userId" name="userId" value="${userId}" />
	<input type="hidden" id="authId" name="authId" value="${authId}" />
	
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
	
	<!-- PK -->
	<input type="hidden" id="pmsSeqId" name="pmsSeqId" value="0" />
	<!-- <input type="hidden" id="managerId" name="managerId" value="" /> -->
	<!-- <input type="hidden" id="pmsUserRoleCode" name="pmsUserRoleCode" value="" /> -->
	
	<c:choose>
		<c:when test="${authId == 'A0001'}">
			<div style="color:blue;">일반으로 로그인 하셨습니다.</div>
		</c:when>
		<c:when test="${authId == 'A0002'}">
			<div style="color:red;">관리자로 로그인 하셨습니다.</div>
		</c:when>
		<c:otherwise>
			<div style="color:green;">시스템으로 로그인 하셨습니다.</div>
		</c:otherwise>
	</c:choose>
	
	<div style="float:right; margin-right:20px;">
		<input type="button" id="btn_logout" value="로그아웃" onclick="javascript:fn_logout();"/>
	</div>
	
	<div id="login">
		<fieldset>
			<legend style="font-weight:bold;">공수정보 관리</legend>
		</fieldset>
	</div>
	
	<%-- <div style="float:right;margin-right:20px;">
		<c:if test="${authId == 'A0002'}">
			<input type="button" id="btn_insert" value="등록" onclick="javascript:pushLayer();"/>
		</c:if>
	</div> --%>
	
	<div style="float:right; margin-top:40px;">
		<ul>
			<li><b>전체 : <span>${totalCount}</span>건</b></li>
			<li>[${pageIndex + 1}페이지/${totalPage}페이지]</li>
		</ul>
	</div>
	
	<div class="table_row">
		<table class="bbslist">
			<caption></caption>
			<colgroup>
				<col width="*" />
				<col width="4%" />
				<col width="5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<col width="6.5%" />
				<%-- <col width="4%" /> --%>
			</colgroup>
			<thead class="">
				<tr>
					<th scope="col">프로젝트명</th>
					<th scope="col">기준년도</th>
					<th scope="col">공수합계</th>
					<th scope="col">1월</th>
					<th scope="col">2월</th>
					<th scope="col">3월</th>
					<th scope="col">4월</th>
					<th scope="col">5월</th>
					<th scope="col">6월</th>
					<th scope="col">7월</th>
					<th scope="col">8월</th>
					<th scope="col">9월</th>
					<th scope="col">10월</th>
					<th scope="col">11월</th>
					<th scope="col">12월</th>
					<!-- <th scope="col">비고</th> -->
				</tr>
			</thead>
			<tbody id="data" class="">
				<c:if test="${fn:length(predictMmInfoList) != 0 }">
					<c:forEach var="list" items="${predictMmInfoList}" varStatus="status">
						<tr>
							<td onclick="fn_projectDetail('${list.pmsSeqId}')" style="cursor:pointer;">${list.projectName}</td>
							<td>${list.mmYear}</td>
							<td>${list.mmTotal}</td>
							<td>${list.mmOne}</td>
							<td>${list.mmTwo}</td>
							<td>${list.mmThree}</td>
							<td>${list.mmFour}</td>
							<td>${list.mmFive}</td>
							<td>${list.mmSix}</td>
							<td>${list.mmSeven}</td>
							<td>${list.mmEight}</td>
							<td>${list.mmNine}</td>
							<td>${list.mmTen}</td>
							<td>${list.mmEleven}</td>
							<td>${list.mmTwelve}</td>
							<%-- <c:if test="${authId == 'A0002'}">
								<td>
									<input type="button" value="수정" onclick="javascript:fn_saveMm('1', '<c:out value="${list.pmsSeqId}"/>', '<c:out value="${list.managerId}"/>', '<c:out value="${list.pmsUserRoleCode}"/>');" />
									<input type="button" value="삭제" onclick="javascript:fn_saveMm('2', '<c:out value="${list.pmsSeqId}"/>', '<c:out value="${list.managerId}"/>', '<c:out value="${list.pmsUserRoleCode}"/>');" />
								</td>
							</c:if>
							<c:if test="${authId != 'A0002'}">
								<td></td>
							</c:if> --%>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(predictMmInfoList) == 0}">
					<tr>
						<td colspan="17">조회 된 내역이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<div style="position:absolute;left:50%;">${pagenation}</div>
	</div>
	
	<!-- 레이어팝업  등록 start -->
	<%-- <div id="all_body1"></div>
	<div id="lay_pop1" class="table_row">
		<fieldset>
			<legend style="font-weight:bold;">공수정보관리 등록</legend>
		</fieldset>
		<table class="bbslist" style="width:750px; margin:auto; text-align:center;">
			<caption></caption>
			<tr>
				<th colspan="4" style="text-align:left; font-weight:bold; font-size:15px; padding-left:15px;">기본정보(*필수입력)</th>
			</tr>
			<tr>
				<th>프로젝트명</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="projectName1" name="projectName" class="txtmmtype" value="" maxlength="8" />
				</td>
				<th>기준년도</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmYear1" name="mmYear" class="txtmmtype" value="0" maxlength="4" onkeydown="return fn_onlyNumber(event);"/>
				</td>
			</tr>
			<tr>
				<th>담당자</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="managerId1" name="managerId" class="txtmmtype" value="" maxlength="4" />
				</td>
				<th>담당자역할</th>
				<td style="text-align:left; padding-left:5px;">
					<select id="pmsUserRoleCode1" name="pmsUserRoleCode" title="담당자역할 선택" style="width:173px;">
						<option value="">선택</option>
						<c:forEach items="${commonUserRoleInfoList}" var="list" varStatus="status">
							<option value="${list.commonCode}" <c:if test="${list.commonCode == 'PR006'}">selected</c:if>>${list.commonName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>담당자 역할상세</th>
				<td colspan="3" style="text-align:left; padding-left:5px;">
					<input type="text" id="pmsUserRole1" name="pmsUserRole" class="txtmmroletype" value="" maxlength="30" />
				</td>
			</tr>
			<tr>
				<th>1월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmOne1" name="mmOne" class="txtmmtype" value="" maxlength="4" />
				</td>
				<th>2월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmTwo1" name="mmTwo" class="txtmmtype" value="" maxlength="4" />
				</td>
			</tr>
			<tr>
				<th>3월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmThree1" name="mmThree" class="txtmmtype" value="" maxlength="4" />
				</td>
				<th>4월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmFour1" name="mmFour" class="txtmmtype" value="" maxlength="4" />
				</td>
			</tr>
			<tr>
				<th>5월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmFive1" name="mmFive" class="txtmmtype" value="" maxlength="4" />
				</td>
				<th>6월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmSix1" name="mmSix" class="txtmmtype" value="" maxlength="4" />
				</td>
			</tr>
			<tr>
				<th>7월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmSeven1" name="mmSeven" class="txtmmtype" value="" maxlength="4" />
				</td>
				<th>8월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmEight1" name="mmEight" class="txtmmtype" value="" maxlength="4" />
				</td>
			</tr>
			<tr>
				<th>9월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmNine1" name="mmNine" class="txtmmtype" value="" maxlength="4" />
				</td>
				<th>10월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmTen1" name="mmTen" class="txtmmtype" value="" maxlength="4" />
				</td>
			</tr>
			<tr>
				<th>11월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmEleven1" name="mmEleven" class="txtmmtype" value="" maxlength="4" />
				</td>
				<th>12월공수*</th>
				<td style="text-align:left; padding-left:5px;">
					<input type="text" id="mmTwelve1" name="mmTwelve" class="txtmmtype" value="" maxlength="4" />
				</td>
			</tr>
		</table>
		<a href="javascript:;" onclick="fn_saveMm();"><input type="button" value="저장"/></a>
		<a href="javascript:;" onclick="layerClose('lay_pop1', 'all_body1');"><input type="button" value="취소"/></a>
	</div> --%>
	<!-- 레이어팝업 End -->
</form>
</body>
</html>