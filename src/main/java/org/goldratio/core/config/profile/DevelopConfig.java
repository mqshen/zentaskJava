package org.goldratio.core.config.profile;

import org.goldratio.core.config.support.WebEnvironmentConfigSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** 
 * ClassName: DevelopConfig <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Mar 27, 2013 3:36:31 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Configuration
@Profile("develop")
public class DevelopConfig extends WebEnvironmentConfigSupport{
	
}
