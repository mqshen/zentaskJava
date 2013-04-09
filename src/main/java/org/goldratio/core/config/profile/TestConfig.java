package org.goldratio.core.config.profile;

import org.goldratio.core.config.support.WebEnvironmentConfigSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** 
 * ClassName: TestConfig <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 1, 2013 2:54:00 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Configuration
@Profile("test")
public class TestConfig extends WebEnvironmentConfigSupport{
	
}