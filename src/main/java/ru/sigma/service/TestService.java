package ru.sigma.service;

import ru.sigma.domain.User;

public interface TestService {
    
    void run();
    
    void choseLocale();
    
    String getAnswer();
    
    void printTestResult(User user);
}