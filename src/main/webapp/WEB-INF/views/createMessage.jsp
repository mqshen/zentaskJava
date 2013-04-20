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
<script src="${contextPath}/resources/scripts/createMessage.js" type="text/javascript"></script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="include/header.jsp"></jsp:include>
		<div class="container workspace" id="main-workspace">
			<div class="page sheet sheet-active sheet-root" id="page-message"
				data-page-name="创建新项目">
				<div class="page-inner">
					<h3>创建新项目</h3>
					<div class="edit-container" >
					<form class="form-edit-message" method="post" action="${contextPath}/project/${projectId}/message">
						<div class="editor-container">
							<div class="form-item">
								<div class="form-field">
									<input type="text" name="title" id="txt-title">
								</div>
							</div>
							<div class="form-item">
								<div class="form-field">
									<textarea class="content" name="content" id="txt-content" ></textarea>
								</div>
							</div>
							<div class="form-item">
								<div class="form-field">
									<div class="attachments-preview editable" data-type="file">
										<div class="file_input_button">
											<span class="prompt_graphic"></span>
											<span class="file_input_container">
												<input type="file" name="file" style="opacity:0;height:20;cursor:pointer;font-size:0;position:absolute;">
												<a href="javascript:;" class="">上传</a>
											</span>
										</div>
			                			<div class="file-list" date-post="true" date-type="data" name="files" id="file-container">
			                				<div class="file-images">
			                					<div style="clear:both"></div>
											</div>
			                				<div class="file-others">
			                					<div style="clear:both"></div>
											</div>
			                			</div>
		                			</div>
		                		</div>
		                	</div>
		                </div>
		               	<div class="form-buttons">
		               		<a tabindex="1" class="btn btn-primary" id="btn-post" data-toggle="submit" data-disable-with="正在保存...">保存</a>
		               		<a tabindex="2" href="javascript:;" class="btn btn-x" id="link-cancel-post">取消</a>
		               	</div>
		            </form>
		            </div>
				</div>
			</div>
		</div>
		<form:form id="csrfForm" method="post">
		</form:form>
		<jsp:include page="include/footer.jsp"></jsp:include>
	</div>
</body>
</html>