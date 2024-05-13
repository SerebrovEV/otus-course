package ru.sigma.dao;


import org.springframework.stereotype.Component;
import ru.sigma.domain.User;

@Component
public class UserDaoImpl implements UserDao {

    public User createUser(String name, String surname) {
        return new User(name, surname);
    }
}