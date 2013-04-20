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
		<a class="title" href="javascript:;" id="" data-stack="">${project.title}</a>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			<div class="container workspace" id="main-workspace">
				<div class="page sheet sheet-root" data-id="page-project">
					<div class="sheet-header">
						<a class="link-parent-sheet" data-stack="" data-stack-replace="" href="/projects/6cceb7d4446f4d4b9cc3a15f0c571171/" data-restore-position="true">
							${project.title}	
						</a>
					</div>
					<div class="page sheet sheet-active sheet-1" data-page-name="项目设置" id="page-project-settings">
						<div class="page-inner">
							<h3>项目设置</h3>
							<form:form class="form" id="form-info" action="${contextPath}/project/${project.id}/settings" method="post" data-remote="true">
								<div class="form-item">
									<div class="form-field">
										<input type="text" name="title" id="project-name" placeholder="项目名称" value="${project.title}">
									</div>
								</div>
								<div class="form-item">
									<div class="form-field">
										<textarea name="content" id="project-desc" data-validate-msg="项目描述最长1000个字符" >${project.content}</textarea>
									</div>
								</div>
								<div class="form-item">
									<button data-toggle="submit" id="btn-save-settings" class="btn btn-primary" data-disable-with="正在保存..." data-success-text="保存成功">保存设置</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
    		<jsp:include page="include/footer.jsp"></jsp:include>
    	</div>
    </body>
</html>