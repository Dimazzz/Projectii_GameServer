package org.projii.serverside.gs.networking.client;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.RequestHandler;

import java.util.Map;

public class ClientInteractionLogic extends SimpleChannelUpstreamHandler {

    private final Map<Class<? extends InteractionMessage>, RequestHandler> handlers;

    public ClientInteractionLogic(Map<Class<? extends InteractionMessage>, RequestHandler> handlers) {
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
}
