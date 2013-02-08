package org.projii.serverside.gs;

import org.projii.serverside.gs.networking.Networking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameServer {

    public static void main(String[] args) {
        int clientsPort = 6677;
        int coordinationServerPort = 6667;
        String coordinationServerAddress = "localhost";
        BlockingQueue taskQueue = new LinkedBlockingQueue();
        SessionsManager sessionsManager = new SessionsManager();

        Networking networking = new Networking(clientsPort, coordinationServerAddress, coordinationServerPort, sessionsManager);
        networking.run();
    }

}
