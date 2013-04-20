<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <title>ZenTask</title>
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/main.css">
    <link rel="shortcut icon" type="image/png" href="${contextPath}/resources/images/favicon.png">
    <script src="${contextPath}/resources/scripts/lib/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/scripts/lib/Json2.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/scripts/lib/lily.core.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/scripts/lib/lily.button.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/scripts/lib/lily.select.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/scripts/lib/lily.form.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/scripts/lib/lily.format.js" type="text/javascript"></script>
    <script>
		$(function(){
			$.lily = $.lily || {};
			$.extend( $.lily, {
				contextPath: '${contextPath}'	
			})
		})
    </script>
    