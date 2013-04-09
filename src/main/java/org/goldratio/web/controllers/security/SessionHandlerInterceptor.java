package org.goldratio.web.controllers.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * ClassName: SessionHandlerInterceptor <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 2, 2013 4:37:44 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class SessionHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		boolean passCheck = false;
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals(ZenTaskConstants.UUID)){
					String currentUuid = cookie.getValue();
					if(currentUuid.equals(session.getAttribute(ZenTaskConstants.UUID))) {
						passCheck = true;
					}
				}
			}
		}
		if(!passCheck) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
			
		return true;
	}
}
