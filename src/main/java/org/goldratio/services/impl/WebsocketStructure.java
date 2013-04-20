package org.goldratio.services.impl;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.atmosphere.cpr.AtmosphereResource;
import org.goldratio.models.BaseModel;


/**
 * Sends updated tweets to the websocket.
 * @author Luciano.Leggieri
 */
public final class WebsocketStructure {

    /**
     * Object to JSON mapper.
     */

    /**
     * Repository to get and send the latest tweets.
     */

    /**
     * Logged user awaiting for broadcaster responses.
     */

    /**
     * Websocket resource.
     */
    private final AtmosphereResource resource;

    /**
     * Timestamp of the last update sent.
     */
    private long lastUpdate;

    /**
     * Class constructor.
     * @param aTweetRepository tweet repository.
     * @param anUser owner of the broadcasters.
     * @param aResource websocket resource.
     */
    public WebsocketStructure(final AtmosphereResource aResource) {
        this.resource = aResource;
        this.refresh();
    }

    /**
     * Update broadcasters.
     */
    public void sendMessage(BaseModel Message) {
        resource.getBroadcaster().broadcast(Message);
    }

    /**
     * Update timestamp.
     */
    protected void refresh() {
        this.lastUpdate = System.currentTimeMillis();
    }

    /**
     * @author Luciano.Leggieri
     */
    private class Message implements Callable<String> {

        @Override
        public String call() throws IOException {
            refresh();
            return "sdfsdf";
        }

    }

}
