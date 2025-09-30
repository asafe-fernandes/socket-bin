package com.asafe.exchanges;

public abstract class Request {
    protected String header, body, type, path;
    public Request(String header, String body, String type, String path) {
        this.header = header;
        this.body = body;
        this.type = type;
        this.path = path;
    }

    public String getBody() {return body;}
    public String getHeader() {return header;}
    public String getType() {return type;}
    public String getPath() {return path;}
}
