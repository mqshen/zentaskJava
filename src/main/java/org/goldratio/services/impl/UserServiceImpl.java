package org.goldratio.services.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListener;
import org.goldratio.models.BaseModel;
import org.goldratio.models.User;
import org.goldratio.services.UserService;
import org.springframework.stereotype.Service;

/** 
 * ClassName: UserServiceImpl <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 13, 2013 12:08:22 AM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private final ConcurrentMap<Long, ConcurrentMap<Long, WebsocketStructure>> websockets;

    public UserServiceImpl() {
    	websockets = new ConcurrentHashMap<Long,  ConcurrentMap<Long, WebsocketStructure>>();
    }
	
    
    private synchronized void keepResource(User user, WebsocketStructure wss) {
    	Long teamId = user.getCurrentTeamId();
    	ConcurrentMap<Long, WebsocketStructure> websocketStructureTeam = websockets.get(teamId);
    	if(websocketStructureTeam == null)
    		websocketStructureTeam = new ConcurrentHashMap<Long,  WebsocketStructure>();
    	websocketStructureTeam.put(user.getId(), wss);
    	websockets.put(teamId, websocketStructureTeam);
    }
    
    private synchronized void removeResource(User user, AtmosphereResource resource) {
    	Map<Long, WebsocketStructure> websocketStructureTeam = websockets.get(user.getTeamId());
    	websocketStructureTeam.remove(user.getId());
    	
    	
    	if(websocketStructureTeam.size() == 0) {
    		websockets.remove(user.getTeamId());
    	}
    }
    
	@Override
	public void registerWebsocket(final User user,final AtmosphereResource resource) {
        WebsocketStructure wss = new WebsocketStructure( resource);
        resource.addEventListener(new AtmosphereResourceEventListener() {

            @Override
            public void onThrowable(final AtmosphereResourceEvent event) {
            	removeResource(user, resource);
            }

            @Override
            public void onSuspend(final AtmosphereResourceEvent event) {
            	// TODO 
            	//System.out.print("suspend");
            }

            @Override
            public void onResume(final AtmosphereResourceEvent event) {
            	// TODO System.out.print("suspend");
            	//System.out.print("suspend");
            }

            @Override
            public void onDisconnect(final AtmosphereResourceEvent event) {
            	removeResource(user, resource);
            }

            @Override
            public void onBroadcast(final AtmosphereResourceEvent event) {
            	// TODO 
            	//System.out.print("suspend");
            }

			@Override
			public void onPreSuspend(AtmosphereResourceEvent event) {
				// TODO
            	//System.out.print("suspend");
			}
        });
        this.keepResource(user, wss);
	}

	@Override
	public void sendMessage(BaseModel message) {
		Long teamId = message.getTeamId();
    	ConcurrentMap<Long, WebsocketStructure> websocketStructureTeam = websockets.get(teamId);
    	if(websocketStructureTeam == null)
    		return;
    	for(Long userId: websocketStructureTeam.keySet()) {
    		WebsocketStructure websocket = websocketStructureTeam.get(userId);
    		websocket.sendMessage(message);
    	}
	}

}
