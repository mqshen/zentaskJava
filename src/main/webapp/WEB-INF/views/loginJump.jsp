<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<script>
function autoJump() {
	document.autoJumpForm.submit();
}
</script>
</head>
<body onload="autoJump();">
	<form name="autoJumpForm" method="get" action="${contextPath}/project">
	</form>
</body>
</html>