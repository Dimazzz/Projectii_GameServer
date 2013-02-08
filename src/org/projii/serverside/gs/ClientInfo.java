package org.projii.serverside.gs;

import org.projii.commons.spaceship.Spaceship;

public class ClientInfo {

    public final int sessionId;
    public final Spaceship spaceship;

    public ClientInfo(int sessionId, Spaceship spaceship) {
        this.sessionId = sessionId;
        this.spaceship = spaceship;
    }
}