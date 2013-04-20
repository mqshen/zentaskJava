<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://goldratio.com/jsp/zentask" prefix="zentask" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:useBean id="now" class="java.util.Date"/>
<html>
	<head>
    <jsp:include page="include/common.jsp"></jsp:include>
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/calendar.css">
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/editor.css">
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/window.css">
	<script type="text/javascript">

	</script>
    </head>
    <body>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			<div class="container workspace" id="main-workspace">
				<div class="page sheet sheet-root" data-id="page-project">
					<div class="sheet-header">
						<a class="link-parent-sheet" data-stack="" data-stack-replace="" href="${contextPath}/project/${project.id}" >
							${project.title}
						</a>
					</div>
					<div class="page sheet sheet-active sheet-1" data-page-name="成员管理" id="page-project-members" data-since="2013-04-15 09:27:28 +0800">
						<div class="page-inner">
							<h3>成员管理</h3>
							<form:form class="form" id="form-members" action="${contextPath}/project/${project.id}/members" method="post" data-remote="true" >
								<div class="setting-section">
									<p class="desc">只有项目成员才能访问该项目的信息。</p>
									<div class="group group-default">
										<ul class="members">
							                <c:forEach items="${team.members}" var="member">
							                <c:if test="${member.id != sessionScope.user.id }">
											<li class="select member <c:if test="${project.members.contains(member)}">selected</c:if>" data-toggle="select" data-content="${member.id}" 
												data-orgin-statues="<c:if test="${project.members.contains(member)}">selected</c:if>" name="members">
												<img src="${contextPath}/avatar/${member.avatarUrl}" class="avatar" alt="test">
												<span class="name">${member.name}</span>
											</li>
											</c:if>
											</c:forEach>
										</ul>
									</div>
									<div class="form-buttons">
										<button data-toggle="submit" class="btn btn-primary" id="btn-create-project" data-disable-with="正在保存..." data-success-text="保存成功">保存项目成员</button>
										<a href="/" class="btn btn-x" data-stack="" data-stack-bare="" data-stack-root="">取消</a>
									</div>
								</div>
							</form:form>
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