<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
	<head>
    <jsp:include page="include/common.jsp"></jsp:include>
    </head>
    <body>
    	<div class="wrapper">
    		<jsp:include page="include/header.jsp"></jsp:include>
			
			<div class="container workspace" id="main-workspace">
				<div class="page sheet-root" id="page-team-show" data-page-name="所有项目" >
					<div class="projects ui-sortable">
					<c:forEach items="${projects}" var="project">
					<div class="project" data-access-id="70023">
            			<a title="@project.title" href="${contextPath}/project/${project.id}" class="folder c2 i2" data-stack="" data-stack-root="" data-restore-position="">
                			<i class="badge-edit" title="修改项目图标和颜色"></i>
			            </a>
        			    <h6 class="name">
                			<a title="" data-stack="" data-stack-root="" data-restore-position="" href="/project/${project.id}">${project.title}</a>
			            </h6>
        			    <p class="desc" title="${project.content}">${project.content}</p>
			        </div>
					</c:forEach>
					<div class="project new">
						<a title="创建新项目" class="folder" href="/project/new" data-stack="" data-stack-root="">
							<span class="badge"></span>
						</a>
					</div>
				</div>
			</div>
			</div>
    		<jsp:include page="include/footer.jsp"></jsp:include>
    	</div>
    </body>
</html>