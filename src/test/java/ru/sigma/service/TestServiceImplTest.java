package ru.sigma.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.sigma.config.TestServiceTestConfiguration;
import ru.sigma.domain.TestResult;
import ru.sigma.domain.User;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestServiceTestConfiguration.class)
@ActiveProfiles(value = "test-test")
@DisplayName("Test TestServiceImpl")
class TestServiceImplTest {
    
    @MockBean
    OutputService outputService;
    
    @MockBean
    InputService inputService;
    
    @Autowired
    TestService testService;
    
    @Test
    @DisplayName("Test method run")
    void run() {
        when(inputService.inputData())
                .thenReturn("default")
                .thenReturn("Name")
                .thenReturn("Surname")
                .thenReturn("RightAnswer");
        testService.run();
        verify(outputService, Mockito.times(1)).outputData("Your test result is: 3");
        verify(outputService, Mockito.times(1)).outputData("You didn't pass the test :(");
    }
    
    @Test
    @DisplayName("Test method choseLocale")
    void choseLocale() {
        Locale.setDefault(Locale.ENGLISH);
        assertThat(Locale.getDefault()).isEqualTo(Locale.ENGLISH);
        assertThat(Locale.getDefault()).isNotEqualTo(Locale.ROOT);
        when(inputService.inputData()).thenReturn("default");
        doNothing().when(outputService).outputData(anyString());
        testService.choseLocale();
        verify(inputService, Mockito.times(1)).inputData();
        verify(outputService, Mockito.times(4)).outputData(anyString());
        assertThat(Locale.getDefault()).isEqualTo(Locale.ROOT);
    }
    
    @Test
    @DisplayName("Test method getAnswer")
    void getAnswer() {
        when(inputService.inputData()).thenReturn("Test answer");
        doNothing().when(outputService).outputData(anyString());
        String expected = "Test answer";
        String actual = testService.getAnswer();
        verify(inputService, Mockito.times(1)).inputData();
        verify(outputService, Mockito.times(3)).outputData(anyString());
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    @DisplayName("Test method printTestResult")
    void printTestResult() {
        User user = new User("Name", "Surname");
        user.getResults().add(new TestResult(10));
        doNothing().when(outputService).outputData(anyString());
        testService.printTestResult(user);
        verify(outputService, Mockito.times(1)).outputData("Your test result is: 10");
        verify(outputService, Mockito.times(1)).outputData("You passed the test :)");
        
        user.getResults().add(new TestResult(5));
        testService.printTestResult(user);
        verify(outputService, Mockito.times(1)).outputData("Your test result is: 5");
        verify(outputService, Mockito.times(1)).outputData("You didn't pass the test :(");
    }
}