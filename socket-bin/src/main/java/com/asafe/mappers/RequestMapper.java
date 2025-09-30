package com.asafe.mappers;

import com.asafe.exchanges.Request;

import java.io.BufferedReader;

public abstract class RequestMapper {
    public abstract Request parse(BufferedReader reader);
}
