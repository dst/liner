package com.stefanski.lineserver.server.cmd;

import com.stefanski.lineserver.server.ClientHandler;
import com.stefanski.lineserver.server.resp.EmptyResponse;
import com.stefanski.lineserver.server.resp.Response;

/**
 * A request to shutdown a server.
 * 
 * @author Dariusz Stefanski
 * @date Sep 12, 2013
 */
public class ShutdownCommand implements Command {

    private static ShutdownCommand INSTANCE = new ShutdownCommand();

    public static ShutdownCommand getInstance() {
        return INSTANCE;
    }

    private ShutdownCommand() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response execute(ClientHandler handler) {
        handler.shutdownServer();
        return new EmptyResponse();
    }

}
