package org.projii.serverside.gs.networking.coordinationserver;

import org.projii.serverside.commons.GameServerInfo;
import org.projii.serverside.gs.ClientInfo;


public interface CoordinationServerConnection {

    boolean connect(String address, int port);

    boolean authorize(int serverId, String password);

    void sendInfo(GameServerInfo state);

    void confirmClient(int clientSessionId);

    void clientLeft(int clientSessionId);

    ClientInfo getNewClientInfo();

}
