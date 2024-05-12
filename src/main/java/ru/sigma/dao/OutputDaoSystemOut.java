package ru.sigma.dao;

import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class OutputDaoSystemOut implements OutputDao {

    private final OutputStream outputStream = System.out;
    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }
}

