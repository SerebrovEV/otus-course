package ru.sigma.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Test InputDaoSystemIn")
class InputDaoSystemInTest {
    private final InputDao out = new InputDaoSystemIn();
    
    @DisplayName("Test method getInputStream")
    @Test
    void getInputStream() {
        InputStream expected = System.in;
        InputStream actual = out.getInputStream();
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
}