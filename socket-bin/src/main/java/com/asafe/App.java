package com.asafe;
import com.asafe.factory.HttpResponseFactory;
import com.asafe.mappers.HttpRequestMapper;
import com.asafe.server.SimpleServer;
import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        SimpleServer server = new SimpleServer(8080, new HttpRequestMapper(), new HttpResponseFactory());

        server.register("/hello", "GET", (req, res) -> {
            res.write("<p>Hello!!</p>");
        });
        server.register("/name", "POST", (req,res) -> {
            String re = req.getBody();
            res.write(re);
        });

        server.listen(); // blocks forever
    }
}

