package com.asafe.server;

import com.asafe.exchanges.Exchange;

import javax.xml.xpath.XPath;
import java.util.Arrays;
import java.util.Map;

public class Router {
    class RouterNode {
        Map<String, RouterNode> nodes;
        Map<String, Exchange> methods;
    }
    private final RouterNode root = new RouterNode();

    public void register(String path,String method, Exchange exchange) {
        String[] pathArray = parse(path);

        RouterNode current = root;
        for(String part : pathArray) {
            if (current.nodes.containsKey(part)){
                current = current.nodes.get(part);
                continue;
            }
            RouterNode newNode = new RouterNode();
            current.nodes.put(part,newNode);
            current = newNode;
        }
        current.methods.put(method,exchange);
    }
    public Exchange resolve(String path,String method){
        String[] pathArray = parse(path);

        RouterNode current = root;
        for(String part : pathArray) {
            if(current.nodes.containsKey(part)) {
                current = current.nodes.get(part);
                if (current == null) return null;
            }
        }
        return current.methods.get(method);
    }
    private String[] parse(String path) {
        return Arrays.stream(path.split("/"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }
}
