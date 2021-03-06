package com.stefanski.liner.server.command.parser;

import org.springframework.stereotype.Component;

import com.stefanski.liner.server.command.Command;
import com.stefanski.liner.server.command.ShutdownCommand;

/**
 * @author Dariusz Stefanski
 * @since 18 Oct 2015
 */
@Component
class ShutdownCommandParser implements CommandParser {

    private static final String SHUTDOWN_CMD = "SHUTDOWN";

    @Override
    public boolean isApplicableTo(String line) {
        return line.startsWith(SHUTDOWN_CMD);
    }

    @Override
    public Command parse(String line) {
        return ShutdownCommand.getInstance();
    }
}
