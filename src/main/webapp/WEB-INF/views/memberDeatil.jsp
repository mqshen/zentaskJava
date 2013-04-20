<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://goldratio.com/jsp/zentask" prefix="zentask" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
    <jsp:include page="include/common.jsp"></jsp:include>
    </head>
    <body>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			<div class="container workspace">
				<div class="page sheet sheet-active sheet-root" id="page-member">
					<div class="member-info">
						<img class="avatar" alt="goldratio" src="${contextPath}/avatar/${user.avatarUrl}">
						<div class="info">
							<h2>${user.name}</h2>
							<a class="email" href="mailto:${user.email}">${user.email}</a>
						</div>
					</div>
					<div class="member-data todos">
						<div class="filter-by-project">
							<select id="filter-project">
								<option value="-1">所有项目</option>
								<c:forEach items="${projects}" var="project">
									<option value="${project.id}">${project.name}</option>
								</c:forEach>
							</select>
						</div>
						<h3>我的任务</h3>
	
						<div id="boxes" class="boxes" style="">
							<div id="box-today" class="box box-today ui-droppable">
								<h5 class="" style="">今日任务</h5>
								<div class="todolist">
									<ul class="todos todos-uncompleted">
									
				  						<zentask:todoItem todoItems="${todoItems}" contextPath="${contextPath}" user="${user}" today="true"></zentask:todoItem>
									</ul>
									<div class="init init-todo-today-empty init-box-empty hide" style="display: none;">
										<div class="arrow"></div>
										<div class="title">从下面拖动任务上来开始今天的工作</div>
									</div>
									<div class="init init-todo-today-empty filtered hide" id="init-filter-empty" style="display: none;">
										<div class="title">这个项目今天没有任务</div>
									</div>
								</div>
								<div class="droppable-mask">
									<p class="droppable-desc">今日任务请拖动到这里</p>
								</div>
							</div>

							<div id="box-other" class="box box-other ui-droppable">
								<div class="todolist">
									<ul class="todos todos-uncompleted">
										<zentask:todoItem todoItems="${todoItems}" contextPath="${contextPath}" user="${user}" today="false"></zentask:todoItem>
									</ul>
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