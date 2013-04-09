package org.projii.serverside.gs.interaction.clients.responses;

import org.projii.commons.net.GameServerResponses;
import org.projii.commons.net.InteractionMessage;

public class JoinResponse implements InteractionMessage {

    private final int type = GameServerResponses.JOIN_RESULT;
    private final boolean result;

    public JoinResponse(boolean result) {
        this.result = result;
    }

    @Override
    public int getType() {
        return type;
    }

    public boolean isResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JoinResponse)) return false;

        JoinResponse that = (JoinResponse) o;

        if (result != that.result) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = type;
        result1 = 31 * result1 + (result ? 1 : 0);
        return result1;
    }
}
