<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
    <head>
        <title>Zentasks</title>
        <link rel="shortcut icon" type="image/png" href="resources/images/favicon.png">
        <link rel="stylesheet" type="text/css" media="screen" href="resources/css/public.css">
        <link rel="stylesheet" type="text/css" media="screen" href="resources/css/login.css">
    </head>
<body>
    <div id="logon">
    	<div id="localLogon">
    	
			<form:form id="form" method="post" modelAttribute="loginForm" cssClass="cleanform">
				<div class="header">
			  		<c:if test="${not empty message}">
						<div id="message" class="success">${message}</div>	
			  		</c:if>
			  		<s:bind path="*">
			  			<c:if test="${status.error}">
					  		<div id="message" class="error">Form has errors</div>
			  			</c:if>
			  		</s:bind>
				</div>
    			<div>
                    <form:input path="email" cssClass="text login-text" type="email" placeholder="邮箱" value="mqshen@126.com"/>
    			</div>
    			<div>
                    <form:input path="password" cssClass="text login-text" type="password" placeholder="密码" value="111111"/>
    			</div>
    			<input class="button" type="submit" value="登 录" tabindex="3">
    			<div class="actions">
    				<input id="remember_me" type="checkbox" name="rememberme" checked="">
    				<label class="remember_me" for="remember_me">记住我</label>
    				<span class="middot">·</span>
    				<a class="reset" href="/resetpassword">忘记密码？</a>
    			</div>
    			<div class="clearfix"></div>
    		</form:form>
    	</div>        
    </div>
</body>
</html>