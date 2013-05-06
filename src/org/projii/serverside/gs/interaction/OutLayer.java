package org.projii.serverside.gs.interaction;

import org.jboss.netty.channel.Channel;
import org.projii.commons.net.InteractionMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class OutLayer {

    private final ExecutorService workers;
    private Channel coordinationServerChannel;
    private Map<Integer, Channel> clientChannels;


    public OutLayer(ExecutorService workers) {
        this.workers = workers;
        this.clientChannels = new HashMap<>();
    }

    public void setCS(Channel channel) {
        coordinationServerChannel = channel;
    }

    public void addClient(Channel channel, int sessionId) {
        if (!clientChannels.containsKey(sessionId)) {
            clientChannels.put(sessionId, channel);
        }
    }

    public void send(final InteractionMessage interactionMessage) {
        workers.execute(new Runnable() {
            @Override
            public void run() {
                for (Channel channel : clientChannels.values()) {
                    channel.write(interactionMessage);
                }
            }
        });
    }

    public void send(final InteractionMessage interactionMessage, int sessionId) {
        final Channel channel = clientChannels.get(sessionId);
        if (channel != null) {
            workers.execute(new Runnable() {
                @Override
                public void run() {
                    channel.write(interactionMessage);
                }
            });
        }
    }

    public void sendToCS(final InteractionMessage interactionMessage) {
        if (coordinationServerChannel != null) {
            workers.execute(new Runnable() {
                @Override
                public void run() {
                    coordinationServerChannel.write(interactionMessage);
                }
            });
        }
    }

}