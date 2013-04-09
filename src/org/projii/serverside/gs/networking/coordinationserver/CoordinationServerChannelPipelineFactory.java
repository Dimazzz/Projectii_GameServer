package org.projii.serverside.gs.networking.coordinationserver;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.projii.serverside.commons.MessageDecoder;
import org.projii.serverside.commons.MessageEncoder;
import org.projii.serverside.commons.ProtocolDecoder;
import org.projii.serverside.commons.ProtocolEncoder;
import org.projii.serverside.gs.ExecutionLayer;

import java.util.Map;

public class CoordinationServerChannelPipelineFactory implements ChannelPipelineFactory {

    private final ExecutionLayer executionLayer;
    private final Map<Integer, Class> correspondenceTable;

    public CoordinationServerChannelPipelineFactory(ExecutionLayer executionLayer, Map<Integer, Class> correspondenceTable) {
        this.executionLayer = executionLayer;
        this.correspondenceTable = correspondenceTable;
    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline channelPipeline = Channels.pipeline();
        channelPipeline.addLast("Protocol decoder", new ProtocolDecoder());
        channelPipeline.addLast("Protocol encoder", new ProtocolEncoder());
        channelPipeline.addLast("Message decoder", new MessageDecoder(correspondenceTable));
        channelPipeline.addLast("Message encoder", new MessageEncoder());
        channelPipeline.addLast("Request handling service", new CoordinationServerInteractionLogic(executionLayer));
        return channelPipeline;
    }
}