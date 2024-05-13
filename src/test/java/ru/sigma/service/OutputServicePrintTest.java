package ru.sigma.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sigma.dao.OutputDao;
import ru.sigma.dao.OutputDaoSystemOut;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test OutputServicePrint")
class OutputServicePrintTest {
    
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(bos));
    }
    
    @DisplayName("Test method outputData")
    @Test
    void outputData() {
        OutputDao outputDao = new OutputDaoSystemOut();
        OutputServicePrint out = new OutputServicePrint(outputDao);
        out.outputData("Test");
        assertEquals("Test", bos.toString().trim());
        System.setOut(System.out);
    }
}