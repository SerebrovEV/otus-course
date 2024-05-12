package ru.sigma.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sigma.domain.User;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Test UserDaoImpl")
class UserDaoImplTest {
    
    @DisplayName("Test method createUser")
    @Test
    void createUser() {
        UserDaoImpl out = new UserDaoImpl();
        User expected = new User("Name", "Surname");
        User actual = out.createUser("Name", "Surname");
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getSurname()).isEqualTo(expected.getSurname());
        assertThat(actual.getResults()).isEqualTo(Collections.EMPTY_LIST);
    }
}