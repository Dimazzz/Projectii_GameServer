package org.projii.serverside.gs.networking.coordinationserver;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.crossServerRequests.AuthorizationRequest;
import org.projii.serverside.gs.ExecutionLayer;

public class CoordinationServerInteractionLogic extends SimpleChannelHandler {

    private final ExecutionLayer executionLayer;

    public CoordinationServerInteractionLogic(ExecutionLayer executionLayer) {
        this.executionLayer = executionLayer;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        InteractionMessage message = (InteractionMessage) e.getMessage();
        executionLayer.exec(message, ctx.getChannel());
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest(0, "123456");
        ctx.getChannel().write(authorizationRequest);
    }

}