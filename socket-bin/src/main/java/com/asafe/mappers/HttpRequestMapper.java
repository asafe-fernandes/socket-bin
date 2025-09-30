package com.asafe.mappers;

import com.asafe.exchanges.HttpRequest;
import com.asafe.exchanges.Request;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestMapper extends RequestMapper {
    @Override
    public Request parse(BufferedReader reader) {
        try {
            String line = reader.readLine();
            if (line == null) return null;

            String[] requestLine = line.split(" ");
            String method = requestLine[0];
            String path = requestLine[1];

            // Read headers (skip for now)
            StringBuilder headers = new StringBuilder();
            while (!(line = reader.readLine()).isEmpty()) {
                headers.append(line).append("\n");
            }

            // Read body if exists (very simple)
            StringBuilder body = new StringBuilder();
            if (reader.ready()) {
                int ch;
                while (reader.ready() && (ch = reader.read()) != -1) {
                    body.append((char) ch);
                }
            }

            return new HttpRequest(headers.toString(), body.toString(), method, path);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
