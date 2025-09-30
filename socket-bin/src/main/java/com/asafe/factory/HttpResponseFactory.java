package com.asafe.factory;

import com.asafe.exchanges.HttpResponse;
import com.asafe.exchanges.Response;

import java.io.BufferedWriter;

public class HttpResponseFactory implements ResponseFactory{
    @Override
    public Response create(BufferedWriter writer) {
        return new HttpResponse(writer);
    }
}
