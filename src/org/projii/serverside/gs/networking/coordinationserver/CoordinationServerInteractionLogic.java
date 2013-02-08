package org.projii.serverside.gs.networking.coordinationserver;

import org.jai.BSON.BSONDocument;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.projii.commons.TimeLogger;
import org.projii.serverside.gs.ClientInfo;
import org.projii.serverside.gs.SessionsManager;
import org.projii.serverside.gs.networking.RequestHandler;

import static org.projii.serverside.commons.CrossServerRequests.AUTHORIZATION;
import static org.projii.serverside.commons.CrossServerResponses.AUTHORIZATION_RESULT;
import static org.projii.serverside.commons.CrossServerResponses.NEW_CLIENT;

public class CoordinationServerInteractionLogic extends SimpleChannelHandler {

    private boolean isAuthorized;
    private SessionsManager sessionsManager;

    public CoordinationServerInteractionLogic(SessionsManager sessionsManager) {
        this.sessionsManager = sessionsManager;
        isAuthorized = false;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        TimeLogger.d(this.getClass().getName(), ": ", "Message received");
        BSONDocument requestDocument = (BSONDocument) e.getMessage();
        TimeLogger.d(this.getClass().getName(), ": ", "I'v got request document");
        processRequest(requestDocument);
        TimeLogger.d(this.getClass().getName(), ": ", "I'v proceed request");
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        BSONDocument authorizationRequest = new BSONDocument();
        authorizationRequest.add("type", AUTHORIZATION);
        //TODO: Replace '1' in the line below with id of this server
        authorizationRequest.add("id", 1);

        ctx.getChannel().write(authorizationRequest);
    }

    private void processRequest(BSONDocument request) {
        int requestType = (Integer) request.get("type");

        RequestHandler requestHandler = null;
        switch (requestType) {
            case AUTHORIZATION_RESULT:
                isAuthorized = (Boolean) request.get("result");
                break;
            case NEW_CLIENT:
                ClientInfo clientInfo = (ClientInfo) request.get("info");
                sessionsManager.add(clientInfo);
                break;
        }
    }


}