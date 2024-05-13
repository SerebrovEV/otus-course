package ru.sigma.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    
    private final OutputService outputService;
    private final MessageSource messageSource;
    
    @Override
    public String getMessage(String message) {
        return messageSource.getMessage(message, null, Locale.getDefault());
    }
    
    @Override
    public void printMessage(String message) {
        outputService.outputData(getMessage(message));
    }
    
    @Override
    public void printMessage(String message, String outputDate) {
        outputService.outputData(getMessage(message) + outputDate);
    }
}