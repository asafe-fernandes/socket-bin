package com.asafe.exchanges;

public abstract class Request {
    protected String header;
    protected String body;
    protected String type;
    public Request(String header, String body, String type) {
        this.header = header;
        this.body = body;
        this.type = type;
    }

    public String getBody() {return body;}
    public String getHeader() {return header;}
    public String getType() {return type;}
}
