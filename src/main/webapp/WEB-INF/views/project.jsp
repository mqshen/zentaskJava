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
	<script src="${contextPath}/resources/scripts/lib/lily.slider.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.appendForm.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.editor.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.calendar.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.todo.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/i18n/lily.calendar-zh-CN.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/lib/lily.popover.js" type="text/javascript"></script>
	<script src="${contextPath}/resources/scripts/project.js" type="text/javascript"></script>
    </head>
    <body>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			<div class="container workspace" id="main-workspace">
	<div class="page sheet sheet-active sheet-root" id="page-project" data-page-name="test" >
		<div class="page-inner">
			<div class="project-header">
				<div class="project-title">test</div>
				<div class="project-desc">test</div>
			</div>
			
			<div class="link-admin">
				<a href="${contextPath}/project/${project.id}/members" class="link-admin-members" title="成员管理" data-stack="" data-nocache="">成员管理</a>
				<a href="${contextPath}/project/${project.id}/settings/" class="link-admin-settings" title="项目设置" data-stack="" data-nocache="">项目设置</a>
			</div>
			
			<div class="section-messages" data-droppable="">
				<h3 class="topics-head">
					<a class="title" href="${contextPath}/project/${project.id}/topic/" data-stack="">讨论</a>
					<a href="javascript:;" class="btn btn-mini btn-new-discussion" data-toggle="button-display" data-content="#new-discussion-form" data-hidden="#no-discussion-div">发起新的讨论</a>
				</h3>
				
				<form class="form form-editor form-new-discussion" id="new-discussion-form" action="${contextPath}/project/${project.id}/message" method="post" data-remote="true" data-type="json">
					<div class="form-item">
						<div class="form-field">
							<input tabindex="1" type="text" name="title" id="txt-title" placeholder="话题" data-validate="length:0,255" data-validate-msg="话题最长255个字符" data-autosave="new-message-title">
						</div>
					</div>
					<div class="form-item">
						<div class="form-field">
							<textarea tabindex="1" name="content" id="txt-content" placeholder="说点什么..." data-blur-validate="false" data-validate="custom" data-autosave="new-message-content"></textarea>
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
									<label><input tabindex="1" type="checkbox" name="notice" value="bba9abd2ac66441986769e3ebe8795fa">tower.im</label>
								</li>
							</ul>
						</div>
					</div>
					<div class="form-buttons">
						<button tabindex="1" class="btn btn-primary" id="btn-post" data-toggle="submit" data-disable-with="正在提交...">发起讨论</button>
						<a tabindex="2" href="javascript:;" class="btn btn-x" id="link-cancel-post" data-toggle="button-display" data-content="#new-discussion-form" data-hidden="#no-discussion-div">取消</a>
					</div>
				</form>

                <div class="messages" id="messages">
                <c:forEach items="${project.messages}" var="message">
                	<div class="message" style="">
                        <a href="/member/${message.author.id }" >
                            <img class="avatar" src="${contextPath }/avatar/${message.author.avatarUrl}">
                        </a>
                        <div class="name">
                            <a href="/member/${message.author.id}">${message.author.name}</a>
                        </div>
	                    <a href="${contextPath}/project/${project.id}/message/${message.id}" class="message-link">
                            <span class="message-title">${message.title }</span>
                		    <span class="message-content">${message.content}</span>
                    	</a>
	                    <span class="time" title="2013-03-22">${message.createTime}</span>
	                    <a data-stack="" href="${contextPath}/project/${project.id}/message/${message.id}" class="comments-count">
							${message.commentNum}	
						</a>
                    </div>
                </c:forEach>
                </div>

				<c:if test="${empty project.messages}">
				    <div class="init init-discussion" id="no-discussion-div">
				    	<div class="title" >还没有人发起讨论</div>
    				</div>
				</c:if>
			</div>
			<h3>
				<a class="title" href="${contextPath}/project/${project.id}/todos/" data-stack="">任务</a>
				<a href="javascript:;" class="btn btn-mini btn-new-todolist" data-toggle="button-display" data-content="#new-todoList-div" data-hidden="#no-todoList-div">添加任务列表</a>
			</h3>
			<div class="todos-all">
				<div class="filters-wrap hide">
					<div class="filters">
						<h5>任务筛选</h5>
						<select id="filter-assignee">
							<option value="0">所有成员</option>
							<option disabled="">-----</option><option value="a795e4b7ca5e4766972e9609696217a3">goldratio (我自己)</option>
								
								<option value="bba9abd2ac66441986769e3ebe8795fa">tower.im</option>
							<option disabled="">-----</option>
							<option value="-1">未分派</option>
						</select><br>
						<select id="filter-due">
							<option value="-1">所有时间</option>
							<option disabled="">-----</option>
							<option value="0">今天</option>
							<option value="1">明天</option>
							<option value="2">本周</option>
							<option value="3">下周</option>
							<option value="5">以后</option>
							<option disabled="">-----</option>
							<option value="4">已延误</option>
						</select><br>
						<span class="filter-desc">
							<strong>←</strong>筛选结果已用<em> 荧光笔 </em>标记
						</span>
					</div>
				</div>
				<div class="todolists-wrap">
				    <div class="todolist-form new hide" id="new-todoList-div">
				        <form class="form" method="post" action="${contextPath}/project/${project.id}/todoList" data-remote="true" id="new-todoList-form">
				            <input type="text" class="todolist-name" name="title" placeholder="给任务列表起个名字" data-validate="custom" data-blur-validate="false" data-validate-msg="">
				            <p>
				                <button data-toggle="submit" class="btn btn-create-todolist btn-primary" data-disable-with="正在保存...">保存，开始添加任务</button>
				                <button type="button" class="btn btn-x btn-cancel-todolist" data-toggle="button-display" data-content="#new-todoList-div" data-hidden="#no-todo-div">取消</button>
				            </p>
				        </form>
				    </div>
    				<div class="todolists ui-sortable" id="todolists-container">
        				<div class="todolists-wrap">
					  
						    <div class="todolist-form new hide">
						      <form class="form" method="post" action="${contextPath}/projects/f1a6a280a67343fa827f5722e4c71f76/lists/" data-remote="true">
						        <input type="text" class="todolist-name" name="todolist_name" placeholder="给任务列表起个名字" data-validate="custom" data-blur-validate="false" data-validate-msg="">
						        <p>
						          <button type="submit" class="btn btn-create-todolist btn-primary" data-disable-with="正在保存...">保存，开始添加任务</button>
						          <button type="button" class="btn btn-x btn-cancel-todolist">取消</button>
						        </p>
						      </form>
						    </div>
						<div class="todolists ui-sortable">
                            <c:forEach items="${project.todoLists}" var="todoList">
                            <div class="todolist" data-sort="0">
                            	<div class="title">
                            		<div class="todolist-actions actions">
                            			<div class="inr">
                            				<a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/stick" class="stick" rel="nofollow" title="改为所有任务完成后不自动归档列表">固定</a>
                            				<a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/edit" class="edit" title="编辑">编辑</a>
                            				<a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/destroy" class="del" title="删除任务列表">删除</a>
                            			</div>
                            		</div>
                            		<h4>
                                        <a href="${contextPath}/project/${ project.id}/todoList/${todoList.id}" data-stack="">${todoList.title}</a>
                                    </h4>
                            	</div>
                            	<ul class="todos todos-uncompleted ui-sortable" id="todos-uncompleted-container-${todoList.id}">
                            		<c:forEach items="${todoList.todoItems}" var="todoItem">
                            		<c:if test="${not todoItem.done }">
                                 		<c:choose>
											<c:when test="${todoItem.running}">
		                                    <li class="todo running">
											</c:when>
											<c:otherwise>
		                                    <li class="todo">
											</c:otherwise>
										</c:choose>
                            	        <div class="todo-actions actions">
                                            <div class="inr">
                            				    <a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/todoItem/${todoItem.id}/running" data-toggle="remote" class="run" title="标记成正在进行中">执行</a>
                                				<a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/todoItem/${todoItem.id}/pause" data-toggle="remote" class="pause" title="暂停">暂停</a>
                            				    <a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/todoItem/${todoItem.id}/edit"  data-toggle="remote" class="edit" title="编辑">编辑</a>
                                    			<a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/todoItem/${todoItem.id}/destroy" data-toggle="remote" class="del" title="删除">删除</a>
                            		        </div>
                                    	</div>
                                      	<div class="todo-wrap">
                                      		<input type="checkbox" name="todo-done" data-toggle="remote" href="/@teamId/project/@project.id/list/@todoList.id/item/@todoItem.id/done">
		                            		<c:if test="${todoItem.running}">
	                                      		<span class="runner on" title="${todoItem.worker.name} 正在做这条任务">
	                                      			<img alt="${todoItem.worker.name }" class="avatar" src="${contextPath}/avatar/${todoItem.worker.avatarUrl}">
	                                      		</span>
                                      		</c:if>
                                       		<span class="todo-content">
                                       			<span>${todoItem.title}</span>
                                                <a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/item/${todoItem.id}/" >
                                                    ${ todoItem.title}
                                                </a>
                                      		</span>
                                 			<c:choose>
												<c:when test="${not empty todoItem.worker}">
                                 			   		<a href="javascript:;" class="label todo-assign-due " data-toggle="popover" data-conent="#tpl-todo-popover">${todoItem.worker.name}</a>
											   	</c:when>
											   	<c:otherwise>
                                      				<a href="javascript:;" class="label todo-assign-due no-assign" data-toggle="popover" data-conent="#tpl-todo-popover">未指派</a>
											   	</c:otherwise>
											</c:choose>
                                 	    </div>
                                    </li>
                                    </c:if>
									</c:forEach> 
                               	</ul>
                               	<ul class="todo-new-wrap" >
								<li class="todo-form" id="new-todo-form-container-${todoList.id}">
                                         <button type="button" class="btn btn-mini btn-new-todo" data-toggle="append" data-content="#new-todo-form-container" data-url="${todoList.id}/todoItem">添加新任务</button>
                                     </li>
                                 </ul>
                                 <ul class="todos todos-completed">
                                 	<c:forEach items="${todoList.todoItems}" var="todoItem">
                            		<c:if test="${todoItem.done }">
                                    <li class="todo completed">
                            	        <div class="todo-actions actions">
                                            <div class="inr">
                            				    <a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/todoItem/${todoItem.id}/running" data-toggle="remote" class="run" title="标记成正在进行中">执行</a>
                                				<a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/todoItem/${todoItem.id}/pause" class="pause" title="暂停">暂停</a>
                            				    <a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/todoItem/${todoItem.id}/edit" class="edit" title="编辑">编辑</a>
                                    			<a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/todoItem/${todoItem.id}/destroy" class="del" title="删除">删除</a>
                            		        </div>
                                    	</div>
                                      	<div class="todo-wrap">
                                      		<input type="checkbox" name="todo-done" data-toggle="remote" href="/@teamId/project/@project.id/list/@todoList.id/item/@todoItem.id/undone" checked>
                                       		<span class="todo-content">
                                       			<span>${todoItem.title}</span>
                                                <a href="${contextPath}/project/${project.id}/todoList/${todoList.id}/item/${todoItem.id}/" >
                                                    ${ todoItem.title}
                                                </a>
                                      		</span>
                                 			<c:choose>
												<c:when test="${not empty todoItem.worker}">
                                 			   		<a href="javascript:;" class="label todo-assign-due " data-toggle="popover" data-conent="#tpl-todo-popover">${todoItem.worker.name}</a>
											   	</c:when>
											   	<c:otherwise>
                                      				<a href="javascript:;" class="label todo-assign-due no-assign" data-toggle="popover" data-conent="#tpl-todo-popover">未指派</a>
											   	</c:otherwise>
											</c:choose>
                                 	    </div>
                                     </li>
                                     </c:if>
                                     </c:forEach>
                                 </ul>
                             </div>
                           </c:forEach> 
				        </div>
                    </div>
                </div>
				<c:if test="${empty project.todoLists}">
            	<div class="init init-todo-empty" id="no-todoList-div">
            	    <div class="title" >没有需要处理的任务</div>
            	</div>
            	</c:if>
</div>

<div class="todolists-completed-wrap">
</div>


			</div>

			<div class="section-files" data-droppable="">
				<h3>
					<a class="title" href="/project/@project.id/attachments/" data-stack="">文件</a>
					<a href="javascript:;" class="btn btn-mini btn-upload-file" data-url="/projects/@user.activeTeamHash/uploads/" style="position: relative; overflow: hidden; direction: ltr;">上传新文件<input multiple="multiple" type="file" title="添加附件" name="upload-file" tabindex="-1" style="position: absolute; right: 0px; top: 0px; font-family: Arial; font-size: 118px; margin: 0px; padding: 0px; cursor: pointer; opacity: 0;"></a>
				</h3>
				
						<div class="init init-file">
							<div class="title">还没有人上传过文件</div>
						</div>
						<ul class="file-list"></ul>
					
					<div class="file-links folders">
						<a href="/projects/@user.activeTeamHash/attachments/" class="link-more-files hide" data-stack="">全部0个文件</a>
					
					
					</div>
			</div>
		</div>

        <div class="trash">
          <a href="/projects/@user.activeTeamHash/trash/" data-stack="true" title="回收站">
            <img src="${contextPath}/resources/images/trash-mask.png">
          </a>
        </div>
		
	<script type="text/html" id="tpl-todo-form">
    <li class="todo-form">
        <input type="checkbox" disabled />
        <form class="form" method="post" data-remote="true">
		    <textarea tabindex="1" name="todo_content" class="todo-content" placeholder="简单描述任务内容" data-validate="custom" data-validate-msg=""></textarea>
		    <div class="todo-conditions">
			    <div class="condition">
				    <span>截止: </span>
    				<a tabindex="3" href="javascript:;" class="link-todo-due"><span class="due">没有截止时间</span></a>
    				<input type="hidden" name="due_at" class="todo-due-date" />
    			</div>
    			<div class="condition">
    				<span>指派给: </span>
    				<select tabindex="2" name="assignee_guid" class="todo-assignee" disabled>
    					<option disabled class="loading">正在加载...</option>
    					<option disabled>-----</option>
    					<option value="-1">暂不指定</option>
    				</select>
    			</div>
    		</div>
    		<div class="create-buttons">
    			<button tabindex="1" type="submit" class="btn btn-primary btn-create-todo" data-disable-with="正在保存...">保存，继续添加</button>
    			<button tabindex="3" type="button" class="btn btn-x btn-cancel-todo">取消</button>
    		</div>
    		<div class="edit-buttons">
    			<button tabindex="1" type="submit" class="btn btn-primary btn-update-todo" data-disable-with="正在保存...">保存</button>
    			<button tabindex="3" type="button" class="btn btn-x btn-cancel-update-todo">取消</button>
    		</div>
    	</form>
    </li>
	</script>
		<script type="text/html" id="tpl-label-popover">
			<div class="label-popover">
	<h3>选择标签</h3>
	<div class="select-label">
		<div class="label-list" data-project-guid="@user.activeTeamHash">
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
		<script type="text/html" id="tpl-upload-file">
			<div class="file uploading" id="file-upload-{{ id }}" fileid="{{ id }}">
	<div class="file-thumb">
		<a href="javascript:;">
			<img alt="{{ name }}" src="/assets/blank-3dbe121a376a181f0fe840fb1daeeb51.gif" />
		</a>
	</div>
	<div class="file-info">
		<div class="title">
			<div class="name">{{ name }}</div>
			<span class="size"></span>
			<div class="progress">
				<div class='progress-bar'><div><span></span></div></div>
				<span class='percent'>0%</span>
			</div>
			<a href='javascript:;' class='link-cancel' title='Cancel'>取消</a>
		</div>
	</div>
</div>

		</script>
		
	</div>	
<div class="popover direction-right-top" style="display: none" id="tpl-todo-popover">
    <div class="popover-content">
        <div class="assign-due-popover">
        	<div class="select-assignee">
        		<h3>将任务指派给</h3>
        		<select tabindex="62" name="assignee_guid" class="todo-assignee" >
                    @project.users.map{ user => 
                        <option value="@user.id">@user.name</option>
                    }
        	  </select>
        	</div>
        	<div class="select-due-date">
        		<h3>任务截止时间</h3>
        		<div class="shortcuts">
        			<a href="javascript:;" class="today">[今天]</a>
        			<a href="javascript:;" class="tomorrow">[明天]</a>
        			<a href="javascript:;" class="this-week">[本周]</a>
        			<a href="javascript:;" class="next-week">[下周]</a>
        		</div>
        		<div class="datepicker" id="todo-popover-datapicker">
        			<input type="text" class="todo-due-date" />
        		</div>
        		<div class="no-due-date">
        			<a href="javascript:;">没有截止时间</a>
        		</div>
        	</div>
        </div>
    </div>
    <div class="popover-arrow"></div>
</div>

<div class="popover direction-right-top" style="display: none" id="tpl-todo-popover-datepick">
    <div class="popover-content">
        <div class="assign-due-popover">
        	<div class="select-due-date">
        		<h3>任务截止时间</h3>
        		<div class="shortcuts">
        			<a href="javascript:;" class="today">[今天]</a>
        			<a href="javascript:;" class="tomorrow">[明天]</a>
        			<a href="javascript:;" class="this-week">[本周]</a>
        			<a href="javascript:;" class="next-week">[下周]</a>
        		</div>
        		<div class="datepicker" >
        			<input type="text" class="todo-due-date" />
        		</div>
        		<div class="no-due-date">
        			<a href="javascript:;">没有截止时间</a>
        		</div>
        	</div>
        </div>
    </div>
    <div class="popover-arrow"></div>
</div>

<li class="todo-form new" style="display:none" id="new-todo-form-container">
   <form class="form" method="post" data-remote="true" action="${contextPath}/project/${project.id}/todoList/">
   	<textarea tabindex="1" name="title" class="todo-content" placeholder="简单描述任务内容" style="overflow: hidden; word-wrap: break-word; resize: none; "></textarea>
   	<div class="todo-conditions">
   		<div class="condition">
   			<span>截止: </span>
   			<a tabindex="3" href="javascript:;" class="link-todo-due"><span class="due" data-toggle="popover" data-conent="#tpl-todo-popover-datepick">没有截止时间</span></a>
   			<input type="hidden" name="deadLine" class="todo-due-date">
   		</div>
   		<div class="condition">
   			<span>指派给: </span>
               <select tabindex="2" name="workerId" class="todo-assignee">
	                <c:forEach items="${project.members}" var="member">
   				    	<option value="${member.id}">${member.name}</option>
   				    </c:forEach>
   			</select>
   		</div>
   	</div>
   	<div class="create-buttons">
   		<button tabindex="1" data-toggle="submit" class="btn btn-primary btn-create-todo" data-disable-with="正在保存...">保存，继续添加</button>
   		<button tabindex="3" type="button" data-toggle="button-display" data-content="" data-hidden="#new-todo-form-container" class="btn btn-x btn-cancel-todo">取消</button>
   	</div>
   </form>
</li> 
</div>
			<form:form id="csrfForm" method="post" >
			</form:form>
    		<jsp:include page="include/footer.jsp"></jsp:include>
    	</div>
    </body>
</html>