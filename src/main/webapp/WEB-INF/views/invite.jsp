<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<jsp:include page="include/common.jsp"></jsp:include>
<script src="${contextPath}/resources/scripts/lib/lily.tooltip.js" type="text/javascript"></script>
<script src="${contextPath}/resources/scripts/invite.js" type="text/javascript"></script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="include/header.jsp"></jsp:include>
		<div class="container workspace" id="main-workspace">
			<div class="page sheet sheet-active sheet-root" id="page-invite">
				<div class="page-inner">
					<div class="page-left">
						<h3>邀请成员</h3>
						<form:form class="form form-invite" action="/invite/" method="post" data-remote="true" novalidate="">
						<div class="form-item">
							<label class="form-label" id="label-email">输入他们的邮箱和权限</label>
							<span class="tips-helper" data-toggle="tooltip" data-title="管理员：可以管理项目、邀请成员；<br>成员：一般成员，可以看到完整的成员列表；<br>访客：只可以看到和他在同一个项目里的成员。">?
							</span>
							<div class="form-field" id="invite-container">
								
								<div class="invite-item">
									<input type="email" class="invite-email" placeholder="例如：test@localhost.com" name="emails">
									<select class="invite-role" id="choose-role" name="roles">
										<option value="1">管理员</option>
										<option value="0" selected>成员</option>
										<option value="3">访客</option>
									</select>
									<a href="javascript:;" class="del-invite btn btn-x" data-toggle="button-delete">删除</a>
								</div>
							</div>
							<p class="add-invite-wrap"><a href="javascript:;" id="add-invite-item">+ 再加一个</a></p>
						</div>
						
		
						<div class="form-item proj-in">
							<label class="form-label">选择他们能够参与的项目<span class="select-all">[ <a href="#" class="select-all-proj">全选</a> | <a class="select-none-proj" href="#">全不选</a> ]</span></label>
							<div class="form-field">
								<c:forEach items="${team.projects}" var="project">
									<label title="${project.title}"><input type="checkbox" name="projects" value="${project.id }" checked>${project.title}</label>
								</c:forEach>
							</div>
						</div>
		
						<div class="invite-message-wrap">
							<a href="javascript:;" class="link-invite-message btn btn-x" data-toggle="button-display" data-content="#welcome_words">添加附言 ↓</a>
							<div class="form-item hide" id="welcome_words">
								<label class="form-label" for="txt-welcome" >我还想对他们说</label>
								<div class="form-field" >
									<textarea name="welcome_words" id="txt-welcome"></textarea>
								</div>
							</div>
						</div>
		
						<div class="form-buttons">
							<button type="submit" class="btn btn-primary" id="btn-invite" data-disable-with="正在邀请..." data-success-text="邀请成功" data-goto="/teams/26cd2403339c4467be12ccc87f5baaee/members">发送邀请</button>
							<a class="btn btn-x" href="/teams/26cd2403339c4467be12ccc87f5baaee/members" data-stack="" data-stack-bare="" data-stack-root="">取消，并返回团队页面</a>
						</div>
						</form:form>
					</div>
					<div class="page-right">
						<div class="preview-top">
							<h4>邀请邮件预览</h4>
							<p>
								发送邀请后，被邀请的成员会收到一封邀请邮件，这封邮件会引导他们加入团队。
							</p>
						</div>
						<div class="email-preview">
							<div class="email-top">
								<h5>欢迎加入 <span>${team.title }</span> 的团队。</h5>
								<p>简单，好用的团队协作工具。</p>
							</div>
							<div class="email-mid">
								<p>goldratio 邀请你加入 <span>功能测试</span> 团队。<span class="with-extra-msg">goldratio 还说：</span></p>
								<div class="extra-message with-extra-msg"></div>
								<div class="link">
									<p>点击下面的链接，加入团队：</p>
									<span>/join/xxxxxxxxxxxxxxxx/</span>
								</div>
							</div>
							<div class="email-bottom">
								如有疑问，请直接联系<span>${user.name}</span>：<span>${user.email}</span>
							</div>		
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="include/footer.jsp"></jsp:include>
	</div>
</body>
</html>