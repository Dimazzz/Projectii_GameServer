package org.projii.serverside.gs.networking.client;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.*;

import sun.misc.Cache;

import java.util.Map;
import java.util.concurrent.Executor;

public class ClientsChannelPipelineFactory implements ChannelPipelineFactory {

    private final Map<Integer, Class> correspondenceTable;
    private final Cache messageEncoderCache;
    private final Cache protocolEncoderCache;
    private final Executor workers;
    private final Map<Class<? extends InteractionMessage>, RequestHandler> handlers;

    public ClientsChannelPipelineFactory(Map<Integer, Class> correspondenceTable, Executor workers, Map<Class<? extends InteractionMessage>, RequestHandler> handlers) {
        this.correspondenceTable = correspondenceTable;
        this.workers = workers;
        this.handlers = handlers;
        this.protocolEncoderCache = new Cache();
        this.messageEncoderCache = new Cache();
    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline channelPipeline = Channels.pipeline();
        channelPipeline.addLast("Protocol decoder", new ProtocolDecoder());
        channelPipeline.addLast("Cacher", new MessageCacher(protocolEncoderCache));
        channelPipeline.addLast("Protocol encoder", new ProtocolEncoder());
        channelPipeline.addLast("Message decoder", new MessageDecoder(correspondenceTable));
        channelPipeline.addLast("Message encoder", new MessageEncoder());
        channelPipeline.addLast("Execution handler", new ExecutionHandler(workers, false, true));
        channelPipeline.addLast("Cache decider", new MessageCacheDecider(protocolEncoderCache));
        channelPipeline.addLast("Interaction logic", new ClientInteractionLogic(handlers));
        return channelPipeline;
    }
}
