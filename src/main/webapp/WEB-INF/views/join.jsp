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
			<div class="center-box">
				<div class="hd">
					<h1 class="logo">
						<a href="/${contextPath}"></a>
					</h1>
					<span class="title">欢迎加入</span>
				</div>

				<div class="bd">
					<h3>完善个人资料</h3>
					<form:form class="form" action="${contextPath}/join/${inviteUser.hashCode}" method="post">
						<div class="form-item">
							<label class="form-label" for="txt-nickname">昵称</label>
							<div class="form-field">
								<input type="text" tabindex="1" name="nickName" id="txt-nickname" autofocus="">
							</div>
						</div>

						<div class="form-item">
							<label class="form-label" for="txt-password">登录密码</label>
							<div class="form-field">
								<input type="password" tabindex="2" name="password"
									id="txt-password" >
								<p class="desc">字母、数字或符号，最短6位，区分大小写</p>
							</div>
						</div>

						<div class="form-buttons">
							<button type="submit" tabindex="3" id="btn-join"
								class="btn btn-primary" data-disable-with="正在提交..."
								data-success-text="加入成功" data-goto="/launchpad/">提交</button>
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