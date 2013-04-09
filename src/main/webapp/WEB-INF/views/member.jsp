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
		<jsp:include page="include/header.jsp"></jsp:include>
		<div class="container workspace" id="main-workspace">
			<div class="page sheet-root" id="page-members">
				<h2>相比到达的地方，同行的人更重要</h2>
				<div class="group group-default ui-droppable" data-guid="0">
					<ul class="members">
						<li class="member member-invite">
							<a href="${contextPath}/invite" class="member-link new" data-stack="" data-stack-root=""> 
								<img class="avatar" src="${contextPath }/resources/images/new-member.png" alt="邀请新成员">
								<span class="name">邀请新成员</span>
							</a>
						</li>
						<c:forEach items="${team.members}" var="member">
						<li class="member ui-draggable">
							<a href="${contextPath}/member/${member.id}" title="${member.name}" class="member-link" >
								<img class="avatar" src="${contextPath}/avatar/${member.avatarUrl}" alt="${member.name}"> 
								<span class="name">${member.name}</span>
							</a>
						</li>
						</c:forEach>
						<c:forEach items="${team.invitedMembers}" var="member">
						<li class="member ui-draggable">
							<a href="${contextPath}/invitations/${member.hashCode}" title="${member.email}" class="member-link" >
								<img class="avatar" src="${contextPath}/resources/images/default.png" alt="${member.email}"> 
								<span class="name">${member.email}</span>
								<span class="role">(已邀请)</span>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
				<%--
				<div class="group group-new">
					<div class="group-hd">
						<a class="group-new-action" href="javascript:;" title="点击这里创建小组">+
							新建小组</a>
						<div class="group-form hide">
							<form class="form form-create-group"
								action="/teams/26cd2403339c4467be12ccc87f5baaee/subgroups"
								method="post" data-remote="">
								<input name="subgroup_name" class="group-name" type="text"
									placeholder="例如：技术部、客服小组" data-validate="custom"
									data-blur-validate="false" data-validate-msg="">
								<button class="btn btn-primary group-edit-save" type="submit"
									data-disabled-with="正在保存...">保存</button>
								<button type="button" class="btn btn-x cancel">取消</button>
							</form>
						</div>

					</div>
				</div>
				 --%>
			</div>
		</div>
		<form:form id="csrfForm" method="post">
		</form:form>
		<jsp:include page="include/footer.jsp"></jsp:include>
	</div>
</body>
</html>