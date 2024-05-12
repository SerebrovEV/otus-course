package ru.sigma.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sigma.dao.UserDaoImpl;
import ru.sigma.domain.TestResult;
import ru.sigma.domain.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test UserServiceImpl")
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl out;
    private User user;
    @Mock
    private InputService inputService;
    
    @Mock
    private MessageService messageService;
    
    @BeforeEach
    void setUp() {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        out = new UserServiceImpl(userDaoImpl, messageService, inputService);
        user = new User("Name", "Surname");
    }
    
    @DisplayName("Test method addUser")
    @Test
    void createUser() {
        when(inputService.inputData()).thenReturn("Name").thenReturn("Surname");
        User actual = out.createUser();
        User expected = user;
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
    
    @DisplayName("Test method getPrintableUser")
    @Test
    void getPrintableUser() {
        String actual = out.getPrintableUser(user);
        String expected = "Name Surname";
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
    
    
    @DisplayName("Test method getLastTestResult")
    @Test
    void getLastTestResult() {
        user.getResults().add(new TestResult(10));
        int actual = out.getLastTestResult(user);
        int expected = 10;
        assertThat(actual).isEqualTo(expected).isNotNull();
    }
}