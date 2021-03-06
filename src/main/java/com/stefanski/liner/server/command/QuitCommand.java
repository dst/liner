package com.stefanski.liner.server.command;

import lombok.extern.slf4j.Slf4j;

import com.stefanski.liner.server.response.EmptyResponse;
import com.stefanski.liner.server.response.Response;

/**
 * A request to disconnect client.
 *
 * @author Dariusz Stefanski
 * @since Sep 12, 2013
 */
@Slf4j
public class QuitCommand implements Command {

    private static final QuitCommand INSTANCE = new QuitCommand();

    public static QuitCommand getInstance() {
        return INSTANCE;
    }

    private QuitCommand() {
    }

    @Override
    public Response execute(CommandContext ctx) {
        log.info("Disconnecting client");
        ctx.getClientHandler().quit();
        return EmptyResponse.getInstance();
    }
}
