package ru.sigma.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sigma.dao.InputDao;
import ru.sigma.dao.InputDaoSystemIn;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Test InputServiceScanner")
class InputServiceScannerTest {
    
    @BeforeEach
    void setUp() {
        ByteArrayInputStream in = new ByteArrayInputStream("Test".getBytes());
        System.setIn(in);
    }
    
    @DisplayName("Test method inputData")
    @Test
    void inputData() {
        InputDao inputDao = new InputDaoSystemIn();
        InputServiceScanner out = new InputServiceScanner(inputDao);
        String actual = out.inputData();
        String expected = "Test";
        assertThat(actual).isEqualTo(expected);
        System.setIn(System.in);
    }
}