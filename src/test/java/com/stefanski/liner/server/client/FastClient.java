package com.stefanski.liner.server.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * Client sending next random request immediately after receiving response from server.
 * 
 * It assumes that file in each line contain only line number.
 * 
 * 
 * @author Dariusz Stefanski
 * @since Sep 6, 2013
 */
@Slf4j
public class FastClient extends Client {
    private final long time;
    private final int maxLineNr;

    public FastClient(String name, int port, long time, int maxLineNr) {
        super(name, port);
        this.time = time;
        this.maxLineNr = maxLineNr;
    }

    @Override
    protected void doJob(BufferedReader in, PrintWriter out) throws IOException {
        long start = System.currentTimeMillis();
        Random generator = new Random();
        while (System.currentTimeMillis() - start < time) {
            long lineNr = generator.nextInt(maxLineNr) + 1;
            String cmd = "LINE " + lineNr;

            out.println(cmd);

            String status = in.readLine();
            String resp = in.readLine();

            if (!(("OK").equals(status) && resp.equals(String.valueOf(lineNr)))) {
                throw new AssertionError("Invalid LINE response, status: " + status + ", resp: "
                        + resp);
            }

            registerRequest();
        }

        log.info("{} did {} requests in {} ms", getName(), getRequestCount(), time);
    }
}
