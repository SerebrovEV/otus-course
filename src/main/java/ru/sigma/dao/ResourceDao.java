package ru.sigma.dao;

import java.io.InputStream;

public interface ResourceDao {
    
    InputStream get(String path);
}