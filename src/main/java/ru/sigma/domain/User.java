package ru.sigma.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    
    private String name;
    private String surname;
    private List<TestResult> results;
    
    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.results = new ArrayList<>();
    }
}