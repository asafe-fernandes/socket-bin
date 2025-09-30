package com.asafe.server;

import com.asafe.exchanges.Exchange;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Router {
    private final Map<String, Exchange> routes = new HashMap<>();

    public void register(String path, String method, Exchange exchange) {
        // Normalize path into string form (without duplicate slashes etc)
        String normalizedPath = normalize(path);
        String key = method.toUpperCase() + " " + normalizedPath;
        routes.put(key, exchange);
    }

    public Exchange resolve(String path, String method) {
        String normalizedPath = normalize(path);
        String key = method.toUpperCase() + " " + normalizedPath;
        return routes.get(key);
    }

    private String normalize(String path) {
        return "/" + Arrays.stream(path.split("/"))
                .filter(s -> !s.isEmpty())
                .reduce((a, b) -> a + "/" + b)
                .orElse("");
    }
}

