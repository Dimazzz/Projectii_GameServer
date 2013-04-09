package org.projii.serverside.gs;

import org.jboss.netty.channel.ChannelPipelineFactory;
import org.projii.commons.net.InteractionMessage;
import org.projii.serverside.commons.RequestHandler;
import org.projii.serverside.commons.crossServerResponses.AuthorizationResultResponse;
import org.projii.serverside.commons.crossServerResponses.CrossServerResponsesCodes;
import org.projii.serverside.commons.crossServerResponses.NewClientRequest;
import org.projii.serverside.gs.interaction.coordinationserver.handlers.AuthorizationResultHandler;
import org.projii.serverside.gs.interaction.coordinationserver.handlers.NewClientRequestHandler;
import org.projii.serverside.gs.networking.Networking;
import org.projii.serverside.gs.networking.coordinationserver.CoordinationServerChannelPipelineFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    public static void main(String[] args) {
        final int clientsPort = 6677;
        final int coordinationServerPort = 6667;
        final String coordinationServerAddress = "localhost";

        final SessionsManager sessionsManager = new SessionsManager();
        final ExecutorService executorService = Executors.newFixedThreadPool(2);

        Map<Class<? extends InteractionMessage>, RequestHandler> coordinationServerRequestHandlers =
                initializeCoordinationServerRequestHandlers(sessionsManager);
        ExecutionLayer coordinationServerExecutionLayer =
                new ExecutionLayer(
                        coordinationServerRequestHandlers,
                        executorService);
        Map<Integer, Class> coordinationServerRequestCorrespondenceTable =
                initializeCoordinationServerRequestCorrespondenceTable();
        ChannelPipelineFactory coordinationServerPipelineFactory =
                new CoordinationServerChannelPipelineFactory(
                        coordinationServerExecutionLayer,
                        coordinationServerRequestCorrespondenceTable);

        ChannelPipelineFactory clientsServerPipelineFactory = null;

        Networking networking =
                new Networking(
                        clientsPort,
                        coordinationServerPort,
                        coordinationServerAddress,
                        clientsServerPipelineFactory,
                        coordinationServerPipelineFactory);
        networking.run();
    }

    private static Map<Class<? extends InteractionMessage>, RequestHandler> initializeCoordinationServerRequestHandlers(SessionsManager sessionsManager) {
        Map<Class<? extends InteractionMessage>, RequestHandler> coordinationServerRequestHandlers = new HashMap<>();
        coordinationServerRequestHandlers.put(AuthorizationResultResponse.class, new AuthorizationResultHandler());
        coordinationServerRequestHandlers.put(NewClientRequest.class, new NewClientRequestHandler(sessionsManager));
        return coordinationServerRequestHandlers;
    }

    private static Map<Integer, Class> initializeCoordinationServerRequestCorrespondenceTable() {
        Map<Integer, Class> coordinationServerRequestCorrespondenceTable = new HashMap<>();
        coordinationServerRequestCorrespondenceTable.put(
                CrossServerResponsesCodes.AUTHORIZATION_RESULT, AuthorizationResultResponse.class);
        coordinationServerRequestCorrespondenceTable.put(
                CrossServerResponsesCodes.NEW_CLIENT, NewClientRequest.class);
        return coordinationServerRequestCorrespondenceTable;
    }
}
