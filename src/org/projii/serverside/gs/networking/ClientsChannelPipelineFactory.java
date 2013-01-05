package org.projii.serverside.gs.networking;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class ClientsChannelPipelineFactory implements ChannelPipelineFactory {

    @Override
    public ChannelPipeline getPipeline() throws Exception {

        ChannelPipeline channelPipeline = Channels.pipeline();
        channelPipeline.addLast("Protocol decoder", new ProtocolDecoder());
        channelPipeline.addLast("Protocol encoder", new ProtocolEncoder());
        return channelPipeline;

    }
}
