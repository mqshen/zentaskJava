package org.goldratio.web;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.atmosphere.cpr.MeteorServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/** 
 * ClassName: WebInitializer <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Mar 27, 2013 4:00:36 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class WebInitializer implements WebApplicationInitializer {
	
	private static final long WEBSOCKET_INACTIVITY_TIMEOUT_IN_MILLIS =
            TimeUnit.MINUTES.toMillis(30);
	
	private String profile = "develop";
//	private String profile = "junit";
//	private String profile = "test";
//	private String profile = "uat";
	
//	private String profile = "production";
	
	public void onStartup(ServletContext ctx) throws ServletException {
		
		AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
		rootCtx.getEnvironment().setActiveProfiles(profile);
		rootCtx.scan("org.goldratio.core.config");
		
		ctx.addListener(new ContextLoaderListener(rootCtx));
		
		DispatcherServlet homeServlet = new DispatcherServlet();
		homeServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
		homeServlet.setContextConfigLocation("org.goldratio.web.config.HomeMvcConfig");
		ServletRegistration.Dynamic home = ctx.addServlet("main", homeServlet);
		home.setLoadOnStartup(1);
		home.addMapping("/");
		
		

		DispatcherServlet webSocketServlet = new DispatcherServlet();
		webSocketServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
		webSocketServlet.setContextConfigLocation("org.goldratio.web.config.WebSocketMvcConfig");
		
        MeteorServlet meteorServlet = new MeteorServlet(webSocketServlet, "/");
        ServletRegistration.Dynamic dispatcher = ctx.addServlet("comet", meteorServlet);
        dispatcher.setInitParameter("org.atmosphere.cpr.broadcasterClass", "org.atmosphere.cpr.DefaultBroadcaster");
        dispatcher.setInitParameter("org.atmosphere.cpr.CometSupport.maxInactiveActivity", Long.toString(WEBSOCKET_INACTIVITY_TIMEOUT_IN_MILLIS));
        dispatcher.setInitParameter("org.atmosphere.useStream", "true");
        dispatcher.setInitParameter("org.atmosphere.useWebSocket", "true");
        dispatcher.setInitParameter("org.atmosphere.useNative", "true");
        dispatcher.setInitParameter("org.atmosphere.cpr.broadcaster.shareableThreadPool", "true");
        dispatcher.setInitParameter("org.atmosphere.cpr.broadcasterLifeCyclePolicy", "IDLE");
        dispatcher.setInitParameter("org.atmosphere.cpr.sessionSupport", "true");
        dispatcher.setInitParameter("org.atmosphere.cpr.AtmosphereInterceptor", "org.atmosphere.interceptor.JSONPAtmosphereInterceptor,org.atmosphere.interceptor.SSEAtmosphereInterceptor");
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/websockets");
		
		DispatcherServlet loginServlet = new DispatcherServlet();
		loginServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
		loginServlet.setContextConfigLocation("org.goldratio.web.config.LoginMvcConfig");
		ServletRegistration.Dynamic login = ctx.addServlet("login", loginServlet);
		login.setLoadOnStartup(2);
		login.addMapping("/login");
		
		
		DispatcherServlet joinServlet = new DispatcherServlet();
		joinServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
		joinServlet.setContextConfigLocation("org.goldratio.web.config.JoinMvcConfig");
		ServletRegistration.Dynamic join = ctx.addServlet("join", joinServlet);
		join.setLoadOnStartup(3);
		join.addMapping("/join/*");
		
		//Servlet dispatcherServlet = new DispatcherServlet(rootCtx);
		
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}