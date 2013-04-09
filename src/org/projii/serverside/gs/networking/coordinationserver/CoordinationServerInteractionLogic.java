package org.projii.serverside.gs.networking.coordinationserver;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.RequestHandler;
import org.projii.serverside.commons.crossServerRequests.AuthorizationRequest;

import java.util.Map;

public class CoordinationServerInteractionLogic extends SimpleChannelHandler {

    private final Map<Class<? extends InteractionMessage>, RequestHandler> handlers;

    public CoordinationServerInteractionLogic(Map<Class<? extends InteractionMessage>, RequestHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Object message = e.getMessage();
        RequestHandler requestHandler = handlers.get(message);
        if (requestHandler != null) {
            requestHandler.handle((InteractionMessage) message, ctx.getChannel());
        }
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(0, "123456");
        ctx.getChannel().write(authorizationRequest);
    }

}