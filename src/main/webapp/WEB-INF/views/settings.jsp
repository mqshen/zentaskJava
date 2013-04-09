<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
    <jsp:include page="include/common.jsp"></jsp:include>
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/calendar.css">
	<script src="${contextPath}/resources/scripts/lib/lily.fileuploader.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/settings.js" type="text/javascript"></script>
    </head>
    <body>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			<div class="container workspace" id="main-workspace">
				<div class="page sheet sheet-active" id="page-member-settings" data-page-name="goldratio的设置" data-url="/members/a795e4b7ca5e4766972e9609696217a3/settings/">
		<div class="page-inner">
			<h3>个人的设置</h3>
			<form:form class="form" action="${contextPath}/settings" method="post" data-remote="true">
				<div class="form-item upload-avatar" data-droppable="">
					<div class="avatar-wrapper">
						<img class="avatar" src="${contextPath}/avatar/${user.avatarUrl}" id="user-icon">
						<div class="loading"></div>
					</div>
					<div class="link-upload" data-url="/members/avatars/" style="position: relative; overflow: hidden; direction: ltr;">
					  <a id="btn-upload" href="javascript:;">选择新头像</a>
					<input type="file" title="添加附件" name="file" tabindex="-1" data-url="${contextPath}/settings/avatar" style="position: absolute; right: 0px; top: 0px; font-family: Arial; font-size: 118px; margin: 0px; padding: 0px; cursor: pointer; opacity: 0;"></div>
					<p class="desc">你可以选择png/jpg图片(100*100)作为头像</p>
				</div>
				<div class="form-item">
					<label class="form-label" for="txt-email">邮箱</label>
					<div class="form-field">
						<input type="text" name="email" id="txt-email" value="${user.email}" data-validate="email" data-validate-msg="请输入一个有效的邮箱地址">
					</div>
				</div>
				<div class="form-item">
					<label class="form-label" for="txt-nickname">昵称</label>
					<div class="form-field">
						<input type="text" name="nickname" id="txt-nickname" value="${user.name }" data-validate="required;length:1,255" data-validate-msg="人在江湖飘，总得有名号;昵称最长255个字符">
					</div><a></a>
				</div>
				<div class="form-item">
					<label class="form-label" for="txt-old-password">现有密码</label>
					<div class="form-field">
						<input type="password" id="txt-old-password" data-validate="length:6;custom" data-validate-msg="登录密码需要至少有6位;" data-blur-validate="false">
					  <p class="desc">不修改密码则不需要填写此项</p>
					</div>
				</div>
				<div class="form-item">
					<label class="form-label" for="txt-password">新密码</label>
				<div class="form-field">
						<input type="password" id="txt-password" data-validate="length:6;custom" data-validate-msg="登录密码需要至少有6位;" data-blur-validate="false">
						  <p class="desc">修改密码请先输入现有密码，强烈建议密码同时包含字母、数字和标点符号。</p>
					</div>
				</div>
				<div class="form-buttons">
					<button class="btn" id="btn-save" data-disable-with="正在保存..." data-success-text="保存成功">保存</button>
					<a href="javascript:history.back()" class="btn btn-x">返回</a>
				</div>
			</form:form>
		</div>
	</div> 
			</div>
			<form:form id="csrfForm" method="post" >
			</form:form>
    		<jsp:include page="include/footer.jsp"></jsp:include>
    	</div>
    </body>
</html>