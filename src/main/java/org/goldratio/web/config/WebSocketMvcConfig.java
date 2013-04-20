package org.goldratio.web.config;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.goldratio.atmosphere.support.AtmosphereArgumentResolver;
import org.goldratio.web.controllers.security.CSRFHandlerInterceptor;
import org.goldratio.web.controllers.security.CSRFRequestDataValueProcessor;
import org.goldratio.web.controllers.security.SessionHandlerInterceptor;
import org.goldratio.web.resolver.PageableArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.support.RequestDataValueProcessor;
/** 
 * ClassName: ProjectMvcConfig <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 2, 2013 1:57:42 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Configuration
@ComponentScan(basePackages = "org.goldratio.web.controllers.websocket")
public class WebSocketMvcConfig extends WebMvcConfigurationSupport {
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/ent/**").addResourceLocations("/ent/").setCachePeriod(31556926);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
		//registry.addResourceHandler("/WEB-INF/**").addResourceLocations("/WEB-INF/").setCachePeriod(31556926);
	}

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionHandlerInterceptor());
		OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor = new OpenEntityManagerInViewInterceptor();
		openEntityManagerInViewInterceptor.setEntityManagerFactory(entityManagerFactory);
		registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor);
    }
	
	
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableArgumentResolver());
		argumentResolvers.add(new AtmosphereArgumentResolver());
	}
	
	@Bean
	public RequestDataValueProcessor requestDataValueProcessor(){
		CSRFRequestDataValueProcessor requestDataValueProcessor = new CSRFRequestDataValueProcessor();
	    return requestDataValueProcessor;
	}
	
	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
	}
}
