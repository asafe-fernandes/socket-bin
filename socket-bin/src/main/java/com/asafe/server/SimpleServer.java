package com.asafe.server;

import com.asafe.exchanges.Exchange;
import com.asafe.handler.SocketHandler;

import java.io.BufferedReader;
import java.io.IOException;

public class SimpleServer {
    SocketHandler socketHandler;
    Router router;

    public SimpleServer(int port) throws IOException {
        socketHandler = new SocketHandler(port);
        router = new Router();
    }

    public void register(String path, String method, Exchange exchange){
        router.register(path,method,exchange);
    }
    private Exchange resolve(String path, String method) {
        Exchange exchange = router.resolve(path,method);
        return exchange;
    }

    public void listen() throws IOException {
        socketHandler.accept();
        BufferedReader reader = socketHandler.getReader();

    }
    private String parseRequest(BufferedReader reader) throws IOException {
        return "dps eu faco";
     }

}
