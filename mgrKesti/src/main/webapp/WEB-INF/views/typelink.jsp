<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-migrate-1.2.1.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/common/commonUtil.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/common/jquery.ui/jquery.ui.core.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common/jquery.ui/jquery.ui.widget.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common/jquery.ui/jquery.ui.datepicker.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/common/jquery.ui/jquery.ui.core.min.js"/>"></script>

<link type="text/css" rel="stylesheet" href="<c:url value='/resources/js/common/jquery.ui/css/jquery.ui.all.css'/>"/>

<script type="text/javascript">
	$.datepicker.setDefaults({
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		changeMonth: true,
		changeYear: true,
		showMonthAfterYear: true,
		dateFormat: 'yy-mm-dd',
		showOn: 'button',
		buttonImage: '/mgrKesti/resources/images/icon_calendar.gif',
		buttonText: '달력',
		buttonImageOnly: true
	});
</script>