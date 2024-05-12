package ru.sigma.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sigma.config.ResourceDaoFileTestConfiguration;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Test ResourceFile")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ResourceDaoFileTestConfiguration.class)
class ResourceDaoFileTest {
    
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ResourceDao out;
    
    @DisplayName("Test method get")
    @Test
    void get() {
        try (InputStream actual = out.get("test.csv");
             InputStream expected = applicationContext.getResource("test.csv").getInputStream()) {
            assertThat(actual.readAllBytes()).isEqualTo(expected.readAllBytes()).isNotNull();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @DisplayName("Test RuntimeException in method get")
    @Test
    void getThrowRuntimeException() {
        assertThatThrownBy(() -> out.get("example.csv")).isInstanceOf(RuntimeException.class);
    }
}