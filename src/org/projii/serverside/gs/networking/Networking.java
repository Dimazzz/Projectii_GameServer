package org.projii.serverside.gs.networking;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.projii.serverside.gs.SessionsManager;
import org.projii.serverside.gs.networking.client.ClientsChannelPipelineFactory;
import org.projii.serverside.gs.networking.coordinationserver.CoordinationServerChannelPipelineFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Networking {
    private final int clientsPort;
    private final int coordinationServerPort;
    private final String coordinationServerAddress;
    private final SessionsManager sessionsManager;

    public Networking(int clientsPort, String coordinationServerAddress, int coordinationServerPort, SessionsManager sessionsManager) {
        this.clientsPort = clientsPort;
        this.coordinationServerAddress = coordinationServerAddress;
        this.coordinationServerPort = coordinationServerPort;
        this.sessionsManager = sessionsManager;
    }

    public void run() {
        ExecutorService bossThreadPool = Executors.newFixedThreadPool(2);
        ExecutorService workerThreadPool = Executors.newFixedThreadPool(2);

        NioServerSocketChannelFactory socketChannelFactory = new NioServerSocketChannelFactory(bossThreadPool, workerThreadPool);

        ClientBootstrap coordinationServerBootstrap = new ClientBootstrap(socketChannelFactory);
        coordinationServerBootstrap.setPipelineFactory(new CoordinationServerChannelPipelineFactory(sessionsManager));
        coordinationServerBootstrap.connect(new InetSocketAddress(coordinationServerAddress, coordinationServerPort));

        ConnectionlessBootstrap clientsBootstrap = new ConnectionlessBootstrap(socketChannelFactory);
        clientsBootstrap.setPipelineFactory(new ClientsChannelPipelineFactory());
        clientsBootstrap.bind(new InetSocketAddress(clientsPort));
    }
}