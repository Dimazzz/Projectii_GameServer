package org.projii.serverside.gs.networking;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Networking {
    private final int clientsPort;
    private final int coordinationServerPort;
    private final String coordinationServerAddress;
    private final ChannelPipelineFactory clientsPipelineFactory;
    private final ChannelPipelineFactory coordinationServerPipelineFactory;

    public Networking(
            int clientsPort,
            int coordinationServerPort,
            String coordinationServerAddress,
            ChannelPipelineFactory clientsPipelineFactory,
            ChannelPipelineFactory coordinationServerPipelineFactory) {

        this.clientsPort = clientsPort;
        this.coordinationServerPort = coordinationServerPort;
        this.coordinationServerAddress = coordinationServerAddress;
        this.clientsPipelineFactory = clientsPipelineFactory;
        this.coordinationServerPipelineFactory = coordinationServerPipelineFactory;
    }

    public void run() {
        ExecutorService bossThreadPool = Executors.newFixedThreadPool(2);
        ExecutorService workerThreadPool = Executors.newFixedThreadPool(2);
        NioServerSocketChannelFactory socketChannelFactory = new NioServerSocketChannelFactory(bossThreadPool, workerThreadPool);

        ClientBootstrap coordinationServerBootstrap = new ClientBootstrap(socketChannelFactory);
        coordinationServerBootstrap.setPipelineFactory(coordinationServerPipelineFactory);
        coordinationServerBootstrap.connect(new InetSocketAddress(coordinationServerAddress, coordinationServerPort));

        ConnectionlessBootstrap clientsBootstrap = new ConnectionlessBootstrap(socketChannelFactory);
        clientsBootstrap.setPipelineFactory(clientsPipelineFactory);
        clientsBootstrap.bind(new InetSocketAddress(clientsPort));
    }
}