package org.projii.serverside.gs.networking;

import org.jai.BSON.BSONDocument;
import org.jai.BSON.BSONSerializer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;
import org.projii.commons.net.InteractionMessage;
import sun.misc.Cache;

public class CachedMessageEncoder extends SimpleChannelDownstreamHandler {

    private final Cache cache;

    public CachedMessageEncoder(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        InteractionMessage message = (InteractionMessage) e.getMessage();
        BSONDocument marshaledMessage = (BSONDocument) cache.get(message);
        if (marshaledMessage == null) {
            marshaledMessage = BSONSerializer.serialize(message);
            cache.put(message, marshaledMessage);
        }
        Channels.write(ctx, e.getFuture(), marshaledMessage);
    }
}
