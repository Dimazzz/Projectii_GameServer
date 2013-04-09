package org.projii.serverside.gs.interaction.clients.requests;

import org.jai.BSON.BSONSerializable;
import org.projii.commons.net.GameServerRequests;
import org.projii.commons.net.InteractionMessage;

public class JoinRequest implements InteractionMessage {
    @BSONSerializable
    private final int type = GameServerRequests.JOIN;
    @BSONSerializable
    private final int sessionId;

    public JoinRequest(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int getType() {
        return type;
    }

    public int getSessionId() {
        return sessionId;
    }
}
