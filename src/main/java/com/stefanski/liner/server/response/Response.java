package com.stefanski.liner.server.response;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * A response from a server to a client.
 *
 * @author Dariusz Stefanski
 * @since Sep 12, 2013
 */
public interface Response {

    /**
     * Writes a response using writer.
     *
     * @param writer
     * @throws IOException
     */
    void write(PrintWriter writer) throws IOException;
}
