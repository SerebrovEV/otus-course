package ru.sigma.dao;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class InputDaoSystemIn implements InputDao{

    private final InputStream inputStream = System.in;

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }
}

