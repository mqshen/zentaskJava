<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="include/common.jsp"></jsp:include>
</head>
<body>
	<div class="wrapper">
		<div class="container workspace" id="main-workspace">
			<div class="page" id="page-launchpad">
				<div class="topbar">
					<h1 class="logo">
						<a href="${contextPath}" title="返回">ZenTask</a>
					</h1>
					<div class="account-info">
						<span class="welcome">hi, goldratio</span> <a
							href="/users/sign_out" data-method="DELETE" rel="nofollow">退出</a>
					</div>
				</div>
				<ul class="teams">
	                <c:forEach items="${user.teams}" var="team">
						<li><a href="${contextPath}/teamSelect/${team.id}"><span>功能测试</span><b class="fly">✈</b></a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<jsp:include page="include/footer.jsp"></jsp:include>
	</div>
</body>
</html>