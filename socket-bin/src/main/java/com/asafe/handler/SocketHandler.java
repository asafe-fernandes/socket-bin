package com.asafe.handler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler {
    ServerSocket serverSocket;
    Socket socket;
    public SocketHandler(int port) throws IOException {
        serverSocket = new ServerSocket(port);

    }
    public void accept() throws IOException {
        socket = serverSocket.accept();
    }
    public void close() throws IOException {
        socket.close();
    }
    public BufferedWriter getWriter() throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
