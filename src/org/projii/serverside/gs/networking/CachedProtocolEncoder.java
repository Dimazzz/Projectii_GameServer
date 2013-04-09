package org.projii.serverside.gs.networking;

import org.jai.BSON.BSONDocument;
import org.jai.BSON.BSONEncoder;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;
import sun.misc.Cache;

import java.nio.ByteBuffer;

public class CachedProtocolEncoder extends SimpleChannelDownstreamHandler {

    private final Cache cache;

    public CachedProtocolEncoder(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        BSONDocument document = (BSONDocument) e.getMessage();
        ChannelBuffer buffer = (ChannelBuffer) cache.get(document);
        if (buffer == null) {
            ByteBuffer encodedDocument = BSONEncoder.encode(document);
            buffer = ChannelBuffers.wrappedBuffer(encodedDocument);
            cache.put(document, buffer);
        }
        Channels.write(ctx, e.getFuture(), buffer);
    }
}
