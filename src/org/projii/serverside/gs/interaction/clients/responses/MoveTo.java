package org.projii.serverside.gs.interaction.clients.responses;
import org.projii.commons.net.GameServerResponses;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.gs.Vector2;

public class MoveTo implements InteractionMessage {

    private final int type = GameServerResponses.MOVE_TO;
    private  float previousAngle;
    private float nextAngle;
    private Vector2 pointFrom;
    private Vector2 pointTo;
    public MoveTo(){};
    public MoveTo(final float previousAngle,final float nextAngle,final Vector2 pointFrom,final Vector2 pointTo) {
        this.pointFrom = pointFrom;
        this.pointTo = pointTo;
        this.nextAngle=nextAngle;
        this.previousAngle=previousAngle;

    }

    @Override
    public int getType() {
        return type;
    }

    public float getPreviousAngle() {
        return previousAngle;
    }



    public float getNextAngle() {
        return nextAngle;
    }



    public Vector2 getPointFrom() {
        return pointFrom;
    }



    public Vector2 getPointTo() {
        return pointTo;
    }


}

