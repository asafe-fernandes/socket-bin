package com.asafe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2020)) {
            Socket socket = serverSocket.accept();

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream(),
                            StandardCharsets.UTF_8
                    )
            );

            writer.write("Hello.\n");
            writer.flush();

            socket.close();
            // the challenge is to make this more usable for the requests controller.
            // also i need to make a mapper for parsing and serialize the requests.

        } catch (IOException e) {
            throw new RuntimeException("Stack Trace: " + e.getMessage(), e);
        }
    }
}

