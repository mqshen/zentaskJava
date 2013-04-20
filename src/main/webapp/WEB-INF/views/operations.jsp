<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://goldratio.com/jsp/zentask" prefix="zentask" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
    <jsp:include page="include/common.jsp"></jsp:include>
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/calendar.css">
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/editor.css">
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/window.css">
	<script src="${contextPath}/resources/scripts/lib/lily.fileuploader.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.window.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.editor.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.editorTrigger.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/message.js" type="text/javascript"></script>
    </head>
    <body>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			<div class="container workspace">
				<div class="page sheet-root" id="page-progress">
					<div class="page-inner">
						<h2>岁月匆匆，往事不随风</h2>
						<c:if test="${user.isAdmin()}">
							<select id="select-member">
								<option value="0">所有人</option>
								<c:forEach items="${team.users}" var="user">
									<option value="${user.id }">${user.name}</option>
								</c:forEach>
							</select>
						</c:if>	
						<div class="progress-day ">
							<c:set var="curentDate" value=""/>
							<zentask:operation operations="${operations}" contextPath="${contextPath}"></zentask:operation>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			<form:form id="csrfForm" method="post" >
			</form:form>
    		<jsp:include page="include/footer.jsp"></jsp:include>
    	</div>
    </body>
</html>