package org.projii.serverside.gs.interaction.clients.handlers;

import org.jboss.netty.channel.Channel;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.RequestHandler;
import org.projii.serverside.gs.ExecutionLayer;
import org.projii.serverside.gs.SessionsManager;
import org.projii.serverside.gs.interaction.clients.requests.JoinRequest;
import org.projii.serverside.gs.interaction.clients.responses.JoinResponse;

public class JoinRequestHandler implements RequestHandler {

    private final SessionsManager sessionsManager;
    private final ExecutionLayer executionLayer;

    public JoinRequestHandler(SessionsManager sessionsManager, ExecutionLayer executionLayer) {
        this.sessionsManager = sessionsManager;
        this.executionLayer = executionLayer;
    }

    @Override
    public void handle(InteractionMessage interactionMessage, Channel channel) {
        JoinRequest request = (JoinRequest) interactionMessage;
        boolean result = sessionsManager.confirm(request.getSessionId());
        JoinResponse response = new JoinResponse(result);
        channel.write(response);
    }
}