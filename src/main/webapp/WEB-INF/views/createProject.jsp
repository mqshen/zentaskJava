<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="include/common.jsp"></jsp:include>
<link rel="stylesheet" media="screen" href="${contextPath}/resources/css/calendar.css">
<link rel="stylesheet" media="screen" href="${contextPath}/resources/css/editor.css">
<link rel="stylesheet" media="screen" href="${contextPath}/resources/css/window.css">
<script src="${contextPath}/resources/scripts/lib/lily.fileuploader.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/lib/lily.window.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/lib/lily.slider.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/lib/lily.appendForm.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/lib/lily.editor.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/lib/lily.calendar.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/lib/lily.todo.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/lib/i18n/lily.calendar-zh-CN.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/lib/lily.popover.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/createProject.js" type="text/javascript"></script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="include/header.jsp"></jsp:include>
		<div class="container workspace" id="main-workspace">
			<div class="page sheet sheet-active sheet-root" id="page-new-project"
				data-page-name="创建新项目">
				<div class="page-inner">
					<h3>创建新项目</h3>
					<form:form id="form" method="post" modelAttribute="projectForm" cssClass="form">
						<div class="header">
							<c:if test="${not empty message}">
								<div id="message" class="success">${message}</div>
							</c:if>
							<s:bind path="*">
								<c:if test="${status.error}">
									<div id="message" class="error">Form has errors</div>
								</c:if>
							</s:bind>
							<div class="form-item">
								<div class="form-field">
									<form:input path="title" type="text" id="title" placeholder="项目名称箱" />
								</div>
							</div>
							<div class="form-item">
								<div class="form-field">
									<form:textarea path="content" id="content" placeholder="简单描述项目，便于其他人理解（选填）" />
								</div>
							</div>
							<div class="project-members setting-section">
								<h4>点击头像，指定项目成员</h4>
								<p class="desc">只有项目成员才能访问该项目的信息。你可以通过项目设置，随时调整成员列表</p>
								<ul class="members">
									<c:forEach items="${team.members}" var="member">
										<c:if test="${ not (member.id == sessionScope.user.id)}">
										<li class="member btn btn-x" data-content="${member.id}"
											data-toggle="select" title="${member.name}"><img
											src="${contextPath}/avatar/${member.avatarUrl}" class="avatar"
											alt="${member.name}"> <span class="name">${member.name}</span>
											<span class="role"></span></li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
<%--
							<div class="invite-members">
								<a href="javascript:;" class="link-show-invite">或者，直接通过邮箱邀请
									↓</a>
								<div class="form-item hide">
									<label class="form-label" id="label-email">请填入要邀请的邮箱地址</label>
									<div class="form-field">
										<div class="invite-item">
											<input type="email" name="email" class="invite-email"
												placeholder="例如：farthinker@@tower.im">
										</div>
									</div>
									<p class="add-invite-wrap">
										<a href="javascript:;" id="add-invite-item">+ 再加一个</a>
									</p>
								</div>
							</div>
 --%>
							<div class="form-buttons">
								<button data-toggle="submit" class="btn btn-primary"
									id="btn-create-project" data-disable-with="正在创建..."
									data-success-text="创建成功">创建项目</button>
								<a href="/" class="btn btn-x" data-stack="" data-stack-bare=""
									data-stack-root="">取消</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<form:form id="csrfForm" method="post">
		</form:form>
		<jsp:include page="include/footer.jsp"></jsp:include>
	</div>
</body>
</html>