package org.goldratio.services;

import org.atmosphere.cpr.AtmosphereResource;
import org.goldratio.models.BaseModel;
import org.goldratio.models.User;

/** 
 * ClassName: UserService <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 12, 2013 11:56:18 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public interface UserService {

	 void registerWebsocket(User user, AtmosphereResource resource);
	 
	 void sendMessage(BaseModel message);
}