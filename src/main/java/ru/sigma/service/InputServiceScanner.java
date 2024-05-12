package ru.sigma.service;

import org.springframework.stereotype.Service;
import ru.sigma.dao.InputDao;

import java.util.Scanner;

@Service
public class InputServiceScanner implements InputService {
    
    private final Scanner scanner;
    
    public InputServiceScanner(InputDao inputDao) {
        this.scanner = new Scanner(inputDao.getInputStream());
    }
    
    @Override
    public String inputData() {
        return scanner.next();
    }
}