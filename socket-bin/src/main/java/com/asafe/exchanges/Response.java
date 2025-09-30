package com.asafe.exchanges;

import java.io.BufferedWriter;

public abstract class Response {
    protected String header, body;
    protected int statusCode;
    protected BufferedWriter writer;

    public Response(BufferedWriter writer) {
        this.header = "";
        this.body = "";
        this.statusCode = 200;
        this.writer = writer;
    }

    public String getBody() { return body; }
    public String getHeader() { return header; }
    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int i) {this.statusCode = i;}
    public void setBody(String body) {this.body = body;}
    public void setHeader(String header) {this.header = header;}

    public abstract void write(String content);
    public abstract void flush();
}
