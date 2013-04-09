package org.projii.serverside.gs.interaction.clients.handlers;

import org.jboss.netty.channel.Channel;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.RequestHandler;
import org.projii.serverside.gs.SessionsManager;
import org.projii.serverside.gs.interaction.OutLayer;
import org.projii.serverside.gs.interaction.clients.requests.JoinRequest;
import org.projii.serverside.gs.interaction.clients.responses.JoinResponse;

public class JoinRequestHandler implements RequestHandler {

    private final SessionsManager sessionsManager;
    private final OutLayer outLayer;

    public JoinRequestHandler(SessionsManager sessionsManager, OutLayer outLayer) {
        this.sessionsManager = sessionsManager;
        this.outLayer = outLayer;
    }

    @Override
    public void handle(InteractionMessage interactionMessage, Channel channel) {
        JoinRequest request = (JoinRequest) interactionMessage;
        boolean successfulAuth = sessionsManager.confirm(request.getSessionId());
        JoinResponse response = new JoinResponse(successfulAuth);

        if (successfulAuth) {
            outLayer.addClient(channel, request.getSessionId());
        }

        channel.write(response);
    }
}