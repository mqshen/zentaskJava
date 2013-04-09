<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
    <jsp:include page="include/common.jsp"></jsp:include>
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/calendar.css">
	<script src="${contextPath}/resources/scripts/message.js" type="text/javascript"></script>
    </head>
    <body>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			<div class="container workspace" id="main-workspace">
				<div class="page sheet sheet-root" >
					<div class="sheet-header">
						<a class="link-parent-sheet"  href="${contextPath}/project/${project.id}/" ">
							${project.title}
						</a>
					</div>
					<div class="page sheet sheet-active sheet-1">
						<div class="page-inner">
							<h3>所有的讨论</h3>
								<div class="editor-wrapper">
									<div class="editor-placeholder fake-textarea" data-droppable="">点击发起讨论</div>
									<form class="form form-editor form-new-discussion" action="/projects//messages/" method="post" data-remote="true" data-type="json">
								        <div class="form-item">
											<div class="form-field">
												<input tabindex="1" type="text" name="subject" data-validate="length:0,255" data-validate-msg="话题最长255个字符" data-autosave="new-message-title" id="txt-title" placeholder="话题">
											</div>
								        </div>
								        <div class="form-item">
											<div class="form-field">
												<textarea tabindex="1" name="content" id="txt-content" placeholder="说点什么..." data-autosave="new-message-content" data-validate="custom"></textarea>
											</div>
								        </div>
										<div class="form-item notify">
											<div class="notify-title">
												发送通知给：
												<span class="select-all">
													[ <a href="javascript:;" class="link-select-all">全选</a> | <a href="javascript:;" class="link-select-none">全不选</a> ]
												</span>
											</div>
											<div class="form-field">
												<ul class="member-list">
													<li>
														<label><input tabindex="1" type="checkbox" value="bba9abd2ac66441986769e3ebe8795fa">tower.im</label>
													</li>
												</ul>
											</div>
										</div>
										<div class="form-buttons">
											<button tabindex="1" class="btn btn-primary" id="btn-post" type="submit" data-disable-with="正在提交...">发起讨论</button>
											<a tabindex="2" href="javascript:;" class="btn btn-x" id="link-cancel-post">取消</a>
										</div>
									</form>
								</div>
								<div class="messages">
					                <c:forEach items="${messages}" var="message">
									<div class="message">
										<a href="${contextPath }/members/${message.author.id}" >
											<img class="avatar" src="${contextPath }/avatar/${message.author.avatarUrl}">
										</a>
										<div class="name">
											<a data-stack="" data-stack-root="" href="${contextPath }/members/${message.author.id}">${message.author.name }</a>
										</div>
										<a data-stack="" href="${contextPath}/project/${project.id}/message/${message.id}" class="message-link">
											<span class="message-title">
												${message.title}
											</span>
											<span class="message-content">
												${message.content}
											</span>
										</a>
										<span class="time" title="2013-03-23">${message.createTime}</span>
											<a data-stack="" href="${contextPath}/project/${project.id}/message/${message.id}" class="comments-count">
												${message.commentNum}	
											</a>
									</div>
									</c:forEach>
								</div>
								<a href="javascript:;" id="btn-load-more" class="over">没有更多内容了</a>
							</div>
						</div>
					</div>
    			</div>
    		<jsp:include page="include/footer.jsp"></jsp:include>
    	</div>
    </body>
</html>