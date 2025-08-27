package com.asafe;

import com.asafe.handler.SocketHandler;
import com.asafe.server.SimpleServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        try {
            SimpleServer server = new SimpleServer(2020);
            server.register("/","GET",((req, res) -> {
                res.write("ola");
            }));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

