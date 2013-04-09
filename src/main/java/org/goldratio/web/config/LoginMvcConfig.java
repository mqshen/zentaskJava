package org.goldratio.web.config;

import org.goldratio.web.controllers.security.CSRFHandlerInterceptor;
import org.goldratio.web.controllers.security.CSRFRequestDataValueProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CSRFHandlerInterceptor());
    }
	
	@Bean
	public RequestDataValueProcessor requestDataValueProcessor(){
		CSRFRequestDataValueProcessor requestDataValueProcessor = new CSRFRequestDataValueProcessor();
	    return requestDataValueProcessor;
	}
}
