package org.projii.serverside.gs.networking;

import org.projii.serverside.gs.ClientInfo;
import org.projii.serverside.gs.GameServerInfo;

public interface CoordinationServerConnection {

    boolean connect(String address, int port);

    boolean authorize(int serverId, String password);

    void sendInfo(GameServerInfo state);

    void confirmClient(int clientSessionId);

    void clientLeft(int clientSessionId);

    ClientInfo getNewClientInfo();

}
