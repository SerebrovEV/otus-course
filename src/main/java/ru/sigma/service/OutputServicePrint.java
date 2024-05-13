package ru.sigma.service;


import org.springframework.stereotype.Service;
import ru.sigma.dao.OutputDao;

import java.io.PrintStream;

@Service
public class OutputServicePrint implements OutputService {
    
    private final PrintStream printStream;
    
    public OutputServicePrint(OutputDao outputDao) {
        printStream = new PrintStream(outputDao.getOutputStream());
    }
    
    @Override
    public void outputData(String outputData) {
        printStream.println(outputData);
    }
}