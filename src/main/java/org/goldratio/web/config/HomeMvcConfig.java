package org.goldratio.web.config;

import javax.persistence.EntityManagerFactory;

import org.goldratio.web.controllers.security.CSRFHandlerInterceptor;
import org.goldratio.web.controllers.security.CSRFRequestDataValueProcessor;
import org.goldratio.web.controllers.security.SessionHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
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
@ComponentScan(basePackages = "org.goldratio.web.controllers.home")
public class HomeMvcConfig extends WebMvcConfigurationSupport {
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
        registry.addInterceptor(new CSRFHandlerInterceptor());
		registry.addInterceptor(new SessionHandlerInterceptor());
		OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor = new OpenEntityManagerInViewInterceptor();
		openEntityManagerInViewInterceptor.setEntityManagerFactory(entityManagerFactory);
		registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor);
    }
	
	@Bean
	public RequestDataValueProcessor requestDataValueProcessor(){
		CSRFRequestDataValueProcessor requestDataValueProcessor = new CSRFRequestDataValueProcessor();
	    return requestDataValueProcessor;
	}
}
