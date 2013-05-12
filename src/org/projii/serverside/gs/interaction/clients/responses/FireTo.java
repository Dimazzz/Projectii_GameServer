package org.projii.serverside.gs.interaction.clients.responses;
import org.projii.commons.net.GameServerResponses;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.gs.Vector2;

public class FireTo implements InteractionMessage {

    private final int type = GameServerResponses.FIRE_TO;
    private Vector2 startBulletPosition;
    private Vector2 endFirePosition;

    public FireTo(){}
    public FireTo(Vector2 startBulletPosition,Vector2 endFirePosition)
    {
        this.startBulletPosition = startBulletPosition;
        this.endFirePosition=endFirePosition;
    }
    @Override
    public int getType() {
        return type;
    }

    public Vector2 getStartBulletPosition() {
        return startBulletPosition;
    }



    public Vector2 getEndFirePosition() {
        return endFirePosition;
    }


}

