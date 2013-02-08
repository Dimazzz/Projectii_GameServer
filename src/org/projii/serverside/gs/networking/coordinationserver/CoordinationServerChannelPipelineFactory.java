package org.projii.serverside.gs.networking.coordinationserver;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.projii.serverside.gs.SessionsManager;
import org.projii.serverside.gs.networking.ProtocolDecoder;
import org.projii.serverside.gs.networking.ProtocolEncoder;

public class CoordinationServerChannelPipelineFactory implements ChannelPipelineFactory {

    private final SessionsManager sessionsManager;

    public CoordinationServerChannelPipelineFactory(SessionsManager sessionsManager) {
        this.sessionsManager = sessionsManager;
    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline channelPipeline = Channels.pipeline();
        channelPipeline.addLast("Protocol decoder", new ProtocolDecoder());
        channelPipeline.addLast("Logic", new CoordinationServerInteractionLogic(sessionsManager));
        channelPipeline.addLast("Protocol encoder", new ProtocolEncoder());
        return channelPipeline;
    }
}