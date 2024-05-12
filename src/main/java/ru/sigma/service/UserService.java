package ru.sigma.service;

import ru.sigma.domain.User;

public interface UserService {
    User createUser();
    
    String getPrintableUser(User user);
    
    void addTestResult(User user, int testResult);
    
    int getLastTestResult(User user);
}