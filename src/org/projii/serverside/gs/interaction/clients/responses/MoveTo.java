package org.projii.serverside.gs.interaction.clients.responses;
import org.projii.commons.net.GameServerResponses;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.gs.Vector2;

public class MoveTo implements InteractionMessage {

    private final int type = GameServerResponses.MOVE_TO;
    private float previousAngle;
    private float nextAngle;
    private Vector2 currentSpeed;
    private Vector2 incrementSpeed;
    public MoveTo(){};
    public MoveTo(float previousAngle,float nextAngle,Vector2 currentSpeed,Vector2 incrementSpeed) {
        this.currentSpeed=currentSpeed;
        this.incrementSpeed=incrementSpeed;
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

    public void setPreviousAngle(float previousAngle) {
        this.previousAngle = previousAngle;
    }

    public float getNextAngle() {
        return nextAngle;
    }

    public void setNextAngle(float nextAngle) {
        this.nextAngle = nextAngle;
    }

    public Vector2 getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(Vector2 currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Vector2 getIncrementSpeed() {
        return incrementSpeed;
    }

    public void setIncrementSpeed(Vector2 incrementSpeed) {
        this.incrementSpeed = incrementSpeed;
    }
}

