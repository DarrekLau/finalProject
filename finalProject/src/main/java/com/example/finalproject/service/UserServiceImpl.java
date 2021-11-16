package com.example.finalproject.service;

import com.example.finalproject.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserServiceImpl {

    void createUser(User newUser);
    Optional<User> getUser(Integer id);
    void updateUser(User updatedUser);
    void deleteUser(Integer id);
    List<User> getAllUsers();
    User current_user(String name);
}
