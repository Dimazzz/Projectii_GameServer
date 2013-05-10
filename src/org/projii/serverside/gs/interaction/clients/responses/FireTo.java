package org.projii.serverside.gs.interaction.clients.responses;
import org.projii.commons.net.GameServerResponses;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.gs.Vector2;

public class FireTo implements InteractionMessage {

    private final int type = GameServerResponses.FIRE_TO;
    private Vector2 startFirePosition;
    private Vector2 endFirePosition;

    public FireTo(){}
    public FireTo(Vector2 startFirePosition,Vector2 endFirePosition)
    {
        this.setStartFirePosition(startFirePosition);
        this.setEndFirePosition(endFirePosition);
    }
    @Override
    public int getType() {
        return type;
    }

    public Vector2 getStartFirePosition() {
        return startFirePosition;
    }

    public void setStartFirePosition(Vector2 startFirePosition) {
        this.startFirePosition = startFirePosition;
    }

    public Vector2 getEndFirePosition() {
        return endFirePosition;
    }

    public void setEndFirePosition(Vector2 endFirePosition) {
        this.endFirePosition = endFirePosition;
    }
}

