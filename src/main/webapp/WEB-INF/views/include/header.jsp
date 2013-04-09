<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="header">
  		<ul class="nav">
		<li class="active" id="nav-project"><a href="${contextPath}/project" data-stack="" data-stack-bare="" data-stack-root="">项目</a></li>
                 <li id="nav-progress">
                     <a href="/operations" >回顾</a>
                 </li>
		<li id="nav-members"><a href="${contextPath}/member" data-stack="" data-stack-bare="" data-stack-root="">团队</a></li>
		<li id="nav-me"><a href="/members?me=1" data-stack="" data-stack-root="">我自己</a></li>
		<li id="nav-notifications">
			<a class="link" href="/notifications" data-stack="" data-stack-root="">通知</a>
			<a href="javascript:;" id="notification-count" class="label" title="新的通知">0</a>
			<div class="noti-pop">
				<div class="noti-pop-list notification-list" style="display: none;"></div>
				<div class="noti-pop-empty" style="">- 没有新通知 -</div>
				<div class="noti-pop-action">
					<a id="noti-mark-read" class="mark-as-read" href="javascript:;" title="全部标记为已读">✔</a>
					<a class="noti-all-link" href="/teams/26cd2403339c4467be12ccc87f5baaee/notifications/" data-stack="" data-stack-root="">查看全部通知</a>
				</div>
			</div>
		</li>
	</ul>
	
	<ul class="account-info">
		<li><a href="${contextPath}/settings">goldratio的设置</a></li>
		<li><a href="/team">创建团队</a></li>
		<li><a href="/signOut" data-method="DELETE" rel="nofollow">退出</a></li>
	</ul>
</div>