package org.projii.serverside.gs;

import org.projii.serverside.gs.networking.Networking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class GameServer {

    public static void main(String[] args) {
        int clientsPort = 6677;
        String coordinationServerAddress = "localhost";
        int coordinationServerPort = 6667;

        BlockingQueue taskQueue = new LinkedBlockingQueue();
        SessionsManager sessionsManager = new SessionsManager();


        Networking networking = new Networking(clientsPort, coordinationServerAddress, coordinationServerPort);
        networking.run();
    }
}
