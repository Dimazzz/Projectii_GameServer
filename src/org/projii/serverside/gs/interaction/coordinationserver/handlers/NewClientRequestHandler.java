package org.projii.serverside.gs.interaction.coordinationserver.handlers;

import org.jboss.netty.channel.Channel;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.RequestHandler;
import org.projii.serverside.commons.crossServerResponses.NewClientRequest;
import org.projii.serverside.gs.ClientInfo;
import org.projii.serverside.gs.SessionsManager;

public class NewClientRequestHandler implements RequestHandler {
    private final SessionsManager sessionsManager;

    public NewClientRequestHandler(SessionsManager sessionsManager) {
        this.sessionsManager = sessionsManager;
    }

    @Override
    public void handle(InteractionMessage request, Channel channel) {
        NewClientRequest r = (NewClientRequest) request;
        ClientInfo clientInfo = new ClientInfo(r.getSessionId(), r.getSpaceship());
        sessionsManager.add(clientInfo);
    }
}
