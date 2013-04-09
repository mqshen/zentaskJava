package org.goldratio.web.controllers.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * ClassName: CSRFHandlerInterceptor <br/>
 * Function: <br/>
 * Reason: <br/>
 * date: Apr 2, 2013 10:47:53 AM <br/>
 * 
 * @author GoldRatio
 * @version 1.0
 */

public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (!request.getMethod().equalsIgnoreCase("POST")) {
			// Not a POST - allow the request
			return true;
		} 
		else {
			// This is a POST request - need to check the CSRF token
			String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
			String requestToken = CSRFTokenManager.getTokenFromRequest(request);
			if (sessionToken.equals(requestToken)) {
				return true;
			} 
			else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
				return false;
			}
		}
	}

}