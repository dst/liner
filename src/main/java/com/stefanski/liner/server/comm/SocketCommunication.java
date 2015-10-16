package com.stefanski.liner.server.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

import com.stefanski.liner.server.cmd.Command;
import com.stefanski.liner.server.cmd.CommandParser;
import com.stefanski.liner.server.cmd.CommandParserException;
import com.stefanski.liner.server.cmd.EmptyCommand;
import com.stefanski.liner.server.resp.Response;

/**
 * A communication is done via a socket.
 * 
 * @author Dariusz Stefanski
 * @date Sep 12, 2013
 */
@Slf4j
public class SocketCommunication implements Communication {

    private final Socket socket;
    private final CommandParser parser;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public static SocketCommunication fromSocket(Socket socket) throws IOException {
        return new SocketCommunication(socket, new CommandParser());
    }

    public SocketCommunication(Socket socket, CommandParser parser) throws IOException {
        this.socket = socket;
        this.parser = parser;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command receiveCommand() throws CommunicationException {
        try {
            String line = reader.readLine();
            if (line == null) {
                throw new CommunicationException("Communication channel was closed");
            }
            return parser.parseCmd(line);
        } catch (IOException | CommandParserException e) {
            log.error("Cannot create new command: ", e);
            return EmptyCommand.getInstance();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendResponse(Response resp) throws CommunicationException {
        try {
            resp.write(writer);
        } catch (IOException e) {
            throw new CommunicationException("Cannot write response", e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
