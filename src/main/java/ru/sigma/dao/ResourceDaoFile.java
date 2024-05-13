package ru.sigma.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class ResourceDaoFile implements ResourceDao {

    private final ApplicationContext applicationContext;

    @Override
    public InputStream get(String path) {
        try {
            return applicationContext.getResource(path).getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}