package com.asafe.factory;

import com.asafe.exchanges.Response;

import java.io.BufferedWriter;

public interface ResponseFactory {
    Response create(BufferedWriter writer);
}
