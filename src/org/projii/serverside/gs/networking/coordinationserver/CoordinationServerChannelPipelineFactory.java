package org.projii.serverside.gs.networking.coordinationserver;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.*;

import java.util.Map;
import java.util.concurrent.Executor;

public class CoordinationServerChannelPipelineFactory implements ChannelPipelineFactory {

    private final Map<Integer, Class> correspondenceTable;
    private final Executor workers;
    private final Map<Class<? extends InteractionMessage>, RequestHandler> handlers;

    public CoordinationServerChannelPipelineFactory(
            Map<Integer, Class> correspondenceTable,
            Executor workers, Map<Class<? extends InteractionMessage>,
            RequestHandler> handlers) {
        this.correspondenceTable = correspondenceTable;
        this.workers = workers;
        this.handlers = handlers;
    }


    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline channelPipeline = Channels.pipeline();
        channelPipeline.addLast("Protocol decoder", new ProtocolDecoder());
        channelPipeline.addLast("Protocol encoder", new ProtocolEncoder());
        channelPipeline.addLast("Message decoder", new MessageDecoder(correspondenceTable));
        channelPipeline.addLast("Message encoder", new MessageEncoder());
        channelPipeline.addLast("Execution handler", new ExecutionHandler(workers, false, true));
        channelPipeline.addLast("Request handling service", new CoordinationServerInteractionLogic(handlers));
        return channelPipeline;
    }
}