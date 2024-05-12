package ru.sigma.service;

public interface MessageService {
    
    String getMessage(String message);
    
    void printMessage(String message);
    
    void printMessage(String message, String outputData);
}