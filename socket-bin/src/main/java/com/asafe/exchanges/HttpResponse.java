package com.asafe.exchanges;

import java.io.BufferedWriter;
import java.io.IOException;

public class HttpResponse extends Response {

    public HttpResponse(BufferedWriter writer) {
        super(writer);
    }

    @Override
    public void write(String content) {
        try {
            this.body = content;
            writer.write("HTTP/1.1 " + statusCode +" "+getReasonPhrase(statusCode)+" \r\n");
            writer.write("Content-Length: " + body.length() + "\r\n");
            writer.write("\r\n");
            writer.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getReasonPhrase(int code) {
        switch (code) {
            case 200: return "OK";
            case 201: return "Created";
            case 400: return "Bad Request";
            case 401: return "Unauthorized";
            case 403: return "Forbidden";
            case 404: return "Not Found";
            case 500: return "Internal Server Error";
            default:  return "Unknown";
        }
    }
    @Override
    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
