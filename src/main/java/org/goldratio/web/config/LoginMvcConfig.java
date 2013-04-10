package org.goldratio.web.config;

import javax.persistence.EntityManagerFactory;

import org.goldratio.web.controllers.security.CSRFHandlerInterceptor;
import org.goldratio.web.controllers.security.CSRFRequestDataValueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

/** 
 * ClassName: HomeMvcConfig <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 1, 2013 2:35:33 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Configuration
@ComponentScan(basePackageClasses = org.goldratio.web.controllers.LoginController.class)
public class LoginMvcConfig extends WebMvcConfigurationSupport {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CSRFHandlerInterceptor());
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
