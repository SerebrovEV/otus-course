package ru.sigma.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Test OutputDaoSystemOut")
class OutputDaoSystemOutTest {
    
    private final OutputDao out = new OutputDaoSystemOut();
    
    @DisplayName("Test method getOutputStream")
    @Test
    void getOutputStream() {
        OutputStream expected = System.out;
        OutputStream actual = out.getOutputStream();
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
}