package com.stefanski.liner.server.command;

import com.stefanski.liner.server.resp.EmptyResponse;
import com.stefanski.liner.server.resp.Response;

/**
 * A request to shutdown a server.
 *
 * @author Dariusz Stefanski
 * @since Sep 12, 2013
 */
public class ShutdownCommand implements Command {

    private static final ShutdownCommand INSTANCE = new ShutdownCommand();

    public static ShutdownCommand getInstance() {
        return INSTANCE;
    }

    private ShutdownCommand() {
    }

    @Override
    public Response execute(CommandContext ctx) {
        ctx.getClientHandler().shutdownServer();
        return EmptyResponse.getInstance();
    }
}