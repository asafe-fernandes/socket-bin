package com.asafe.exchanges;

@FunctionalInterface
public interface Exchange {
    void handle(Request req, Response res);
}
