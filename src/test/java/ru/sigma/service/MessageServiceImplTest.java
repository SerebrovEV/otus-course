package ru.sigma.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sigma.config.MessageServiceTestConfiguration;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MessageServiceTestConfiguration.class)
@DisplayName("Test MessageServiceImpl")
class MessageServiceImplTest {
    
    @Autowired
    private MessageService out;
    
    @DisplayName("Test method getMessage")
    @Test
    void getDefaultMessage() {
        Locale.setDefault(Locale.ROOT);
        String actual = out.getMessage("message.test-hello");
        String expected = "Test 1";
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
    
    @DisplayName("Test method getMessage")
    @Test
    void getRussianMessage() {
        Locale.setDefault(new Locale("ru", "RU"));
        String actual = out.getMessage("message.test-hello");
        String expected = "Тест 1";
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
    
    
}