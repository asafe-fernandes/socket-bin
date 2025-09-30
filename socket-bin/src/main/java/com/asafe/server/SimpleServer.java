package com.asafe.server;

import com.asafe.exchanges.*;
import com.asafe.factory.ResponseFactory;
import com.asafe.handler.SocketHandler;
import com.asafe.mappers.RequestMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class SimpleServer {
    SocketHandler socketHandler;
    Router router;
    RequestMapper mapper;
    ResponseFactory responseFactory;

    public SimpleServer(int port, RequestMapper mapper, ResponseFactory responseFactory) throws IOException {
        this.mapper = mapper;
        this.responseFactory = responseFactory;
        this.socketHandler = new SocketHandler(port);
        this.router = new Router();
    }

    public void register(String path, String method, Exchange exchange){
        router.register(path,method,exchange);
    }
    private Exchange resolve(String path, String method) {
        return router.resolve(path,method);
    }

    public void listen() throws IOException {
        System.out.println("server is listening on port 8080");
        while (true) { // keep server running until stopped
            socketHandler.accept(); // accept one client
            try (
                    BufferedReader reader = socketHandler.getReader();
                    BufferedWriter writer = socketHandler.getWriter()
            ) {
                Request request = mapper.parse(reader);

                Exchange exchange = resolve(request.getPath(), request.getType());

                Response response = responseFactory.create(writer);

                if (exchange != null) {
                    exchange.handle(request, response);
                } else {
                    response.setStatusCode(404);
                    response.write("Not Found");
                }

                // 5. Flush response
                response.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
