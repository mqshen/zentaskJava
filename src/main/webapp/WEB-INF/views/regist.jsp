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
		<div class="page" id="page-signup">
			<div class="sign-page">
				<div class="bd">
					<form:form class="form" action="${contextPath}/join" method="post" data-remote="true" novalidate="">
		
						<div class="form-item">
							<div class="form-field">
								<input class="team-name" name="teamName" type="text" placeholder="公司或团队的名称" autocomplete="off" data-validate="required;length:1,255" data-validate-msg="请填写团队或者公司的名称">
							</div>
						</div>
						<div class="form-item">
							<div class="form-field">
								<input class="email" name="email" type="text" placeholder="你的邮箱地址" autocomplete="off" data-validate="required;email" data-validate-msg="请填写您的邮箱地址">
							</div>
						</div>
						<div class="form-item">
							<div class="form-field">
								<input class="user-name" name="name" type="text" placeholder="您的名字" autocomplete="off" data-validate="required;email" data-validate-msg="请填写您的邮箱地址">
							</div>
						</div>
						<div class="form-item">
							<div class="form-field">
								<input class="password" name="password" type="password" placeholder="设置一个密码" autocomplete="off" data-validate="required;length:6" data-validate-msg="请设置一个登录密码；至少6位">
							</div>
						</div>
		
						<div class="form-item form-btns">
							<button type="submit" id="btn-signup" class="btn btn-primary btn-large btn-submit" data-disable-with="正在注册...">注册</button>
							<p class="desc">点击注册表示您已阅读并同意<a href="/agreement/">《tower.im 服务条款》</a></p>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<jsp:include page="include/footer.jsp"></jsp:include>
	</div>
</body>
</html>