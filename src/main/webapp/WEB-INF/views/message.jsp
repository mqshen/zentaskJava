<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
    <jsp:include page="include/common.jsp"></jsp:include>
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/calendar.css">
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/editor.css">
    <link rel="stylesheet" media="screen" href="${contextPath}/resources/css/window.css">
	<script src="${contextPath}/resources/scripts/lib/lily.fileuploader.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.window.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.editor.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.editorTrigger.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/message.js" type="text/javascript"></script>
    </head>
    <body>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			<div class="container workspace" id="main-workspace">
				<div class="page sheet sheet-root" id="page-project">
        <div class="sheet-header">
            <a class="link-parent-sheet" href="${contextPath}/project/${message.projectId}" >${message.project.title}</a>
        </div>
        <div class="page sheet sheet-active sheet-1" id="page-message" >
			<div class="page-inner">
				<div class="topic">
					<div class="message" data-post='{"messageId": "${message.id }"}'>
            	        <div class="message-subject">
                            <h3 title="${message.title}" name="title" class="editable" id="txt-title">${message.title}</h3>
	                    </div>
	                    <a class="avatar-wrap" data-stack="" data-stack-root="">
                            <img class="avatar" src="${contextPath}/avatar/${message.author.avatarUrl}" width="50" height="50">
	                    </a>
	                    <div class="message-main">
                            <div class="info">
                                <a class="author" href="/members/${message.author.id}" >${message.author.name}</a>
                                <span class="create-time" title="2013-03-22 14:46">${message.createTime}</span>
                        	</div>
		                	<div class="message-content editor-style editable" id="txt-content" name="content" data-type="textarea">${message.content}</div>
		                	<div class="attachments-preview editable" data-type="file">
			                	<div class="file-list" >
			                		<div class="file-images">
					                <c:forEach items="${message.getImageFiles()}" var="imageFile">
				                	<div class="file selected" data-toggle="select" data-content="${imageFile.id}" name="files">
					                	<div class="file-thumb">
					                		<a href="javascript:;" title="点击预览">
												<img class="image" alt="" src="${contextPath}/attachment/${imageFile.url}?thumb=1">
											</a>
					                	</div>
					                	<a class="remove" data-toggle="remove" href="javascript:;">&nbsp;</a>
				                	</div>
				                	</c:forEach>
			                					<div style="clear:both"></div>
				                	</div>
				                	<div class="file-others">
					                <c:forEach items="${message.getOtherFiles()}" var="imageFile">
					                	<div class="file selected" data-toggle="select" data-content="${imageFile.id}" name="files">
					                		<div class="file-thumb">
					                			<a href="${contextPath}/attachment/${imageFile.url}">
					                				<img alt="README" src="${contextPath}/resources/images/file_extension_others.png">
					                			</a>
					                			<a class="remove" data-toggle="remove" href="javascript:;">&nbsp;</a>
					                		</div>
					                		<div class="file-info">
												<div class="title">
													<a class="name" href="${contextPath}/attachment/${imageFile.url}" data-stack="" title="">${imageFile.name}</a>
													<span class="name"><a href="${contextPath}/attachment/${imageFile.url}" target="_blank" title="">${imageFile.name}</a></span>
												</div>
											</div>
					                	</div>
				                	</c:forEach>
			                					<div style="clear:both"></div>
				                	</div>
			                	</div>
		                	</div>
	                </div>
                </div>
			</div>
			<div class="comments" id="comment-container">
				<c:forEach items="${message.comments}" var="comment">
                <div class="comment" id="" style="">
                    <a class="avatar-wrap" href="${contextPath}/member/${comment.author.id}/" data-stack="" data-stack-root="">
                        <img class="avatar" src="${contextPath}/avatar/${comment.author.avatarUrl}" width="50" height="50">
                    </a>
                    <div class="comment-actions actions">
                        <a href="" class="edit" style="display: inline;" data-toggle="editorTrigger">编辑</a>
                        <a href="${contextPath}/comment/@comment.id/destroy" class="del" data-remote="true" data-method="post" data-confirm="确定要删除这条回复吗？" data-visible-to="creator,admin" style="display: inline;">删除</a>
                    </div>
                    <div class="comment-main">
                        <div class="info">
                            <a class="author" href="/member/${comment.author.id}/" >${comment.author.name}</a>
                            <span class="create-time" >刚刚</span>
                        </div>
                        <div class="comment-content editor-style">${comment.content}</div>
                    </div>
                </div>
                </c:forEach>
			</div>

                <div class="comment comment-form new">
                    <form id="new-comment-form" class="form form-editor form-new-comment" method="post" action="${contextPath}/project/${message.projectId}/message/${message.id}/comment">
		                <a class="avatar-wrap" href="/member/">
                            <img class="avatar" width="50" height="50" src="${contextPath}/avatar/${user.avatarUrl}">
                		</a>
		                <div class="comment-main">
                            <div>
                                <textarea id="txt-new-comment" tabindex="1" autofocus="" class="content" name="content"></textarea>
                            </div>

                    		<div class="form-buttons">
                    			<button tabindex="1" data-toggle="submit" class="btn btn-primary btn-create-comment" data-disable-with="正在发送...">发表评论</button>
                    			<button tabindex="2" type="button" data-content="#message-comment-div" data-hidden="#message-comment-trigger" class="btn btn-x btn-cancel-create-comment">取消</button>
                    		</div>
                    	</div>
                    </form>
                </div>
				<div class="detail-actions" data-author-guid="a795e4b7ca5e4766972e9609696217a3">
					<div class="item" data-visible-to="creator" style="display: block;">
						<a href="${contextPath}/project/${message.projectId}/message/${message.id}" data-content='{"messageId": ${message.id}, "projectId": ${message.projectId}}' data-target=".message" class="detail-action detail-action-edit" data-toggle="editorTrigger" >编辑</a>
					</div>
					<div class="item" data-visible-to="creator,admin" style="display: block;">
						<a href="${contextPath}/project/${message.projectId}/message/${message.id}/destroy" class="detail-action detail-action-del" data-confirm="确定要删除这条讨论吗？" data-method="post" data-remote="true" rel="nofollow">删除</a>
					</div>
					<div class="item" data-visible-to="admin" style="display: block;">
						<a href="javascript:;" class="detail-action detail-action-move">移动...</a>
						<div class="confirm">
							<form class="form form-move" action="/projects/f1a6a280a67343fa827f5722e4c71f76/messages/0c1a4d9073e34d8ba35585aee549c497/move" method="post" data-remote="">
								<p class="title">移动这条讨论到项目</p>
								<p>
								<select name="tpuid" data-project="f1a6a280a67343fa827f5722e4c71f76" class="choose-projects loading" disabled="">
										<option disabled="">正在加载项目...</option>
									</select>
								</p>
								<p>
									<button type="submit" class="btn btn-mini" disabled="" data-disable-with="正在移动...">移动</button>
									<button type="button" class="btn btn-x cancel">取消</button>
								</p>
							</form>
						</div>
					</div>
				</div>

			</div>
				
			<script type="text/html" id="tpl-label-popover">
				<div class="label-popover">
	<h3>选择标签</h3>
	<div class="select-label">
		<div class="label-list" data-project-guid="f1a6a280a67343fa827f5722e4c71f76">
			<div class="empty">正在加载...</div>
		</div>
		<div class="new-label">
			<input type="text" placeholder="新的标签" class="txt-new-label" disabled />
		</div>
	</div>
	<div class="remove-label">
		<a href="javascript:;" class="link-remove-label">取消标记</a>
	</div>
</div>

			</script>
			
		</div></div>
    		</div>
			<form:form id="csrfForm" method="post" >
			</form:form>
    		<jsp:include page="include/footer.jsp"></jsp:include>
    	</div>
    </body>
</html>