package org.projii.serverside.gs;

import java.util.ArrayList;
import java.util.List;

public class SessionsManager {
    List<Entry> clients;

    public SessionsManager() {
        removeAll();
    }

    public void add(ClientInfo clientInfo) {
        clients.add(new Entry(clientInfo));
    }

    public boolean confirm(int sessionId) {
        Entry e = find(sessionId);
        if (e != null) {
            e.confirm();
            return true;
        }
        return false;
    }

    public void remove(int sessionId) {
        Entry e = find(sessionId);
        if (e != null) {
            clients.remove(e);
        }
    }

    public boolean isConfirmed(int sessionId) {
        Entry e = find(sessionId);
        return e != null && e.isConfirmed();
    }

    private Entry find(int sessionId) {
        for (Entry e : clients) {
            if (e.getClientInfo().sessionId == sessionId) {
                return e;
            }
        }
        return null;
    }

    public void removeAll() {
        clients = new ArrayList<>();
    }

    private class Entry {
        private final ClientInfo clientInfo;
        private boolean isConfirmed;

        private Entry(ClientInfo clientInfo) {
            this.clientInfo = clientInfo;
            this.isConfirmed = false;
        }

        public void confirm() {
            isConfirmed = true;
        }

        public ClientInfo getClientInfo() {
            return clientInfo;
        }

        public boolean isConfirmed() {
            return isConfirmed;
        }
    }

}
