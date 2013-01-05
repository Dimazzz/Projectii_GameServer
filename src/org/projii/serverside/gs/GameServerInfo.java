package org.projii.serverside.gs;

import org.jai.BSON.BSONSerializable;

public class GameServerInfo {

    @BSONSerializable
    private int maxNumberOfPlayers;

    @BSONSerializable
    private String currentGameMap;

    public GameServerInfo(int maxNumberOfPlayers, String currentGameMap) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.currentGameMap = currentGameMap;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public String getCurrentGameMap() {
        return currentGameMap;
    }
}
