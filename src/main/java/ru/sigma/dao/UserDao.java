package ru.sigma.dao;

import ru.sigma.domain.User;

public interface UserDao {
    User createUser(String name, String surname);
}