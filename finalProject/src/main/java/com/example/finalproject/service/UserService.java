package com.example.finalproject.service;

import com.example.finalproject.model.User;
import com.example.finalproject.model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImpl{

    @Autowired
    private UserRepo userRepo;

    @Override
    public void createUser(User newUser) {

    }

    @Override
    public Optional<User> getUser(Integer id) {
        return Optional.empty();
    }

    @Override
    public void updateUser(User updatedUser) {

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User current_user(String name) {
        System.out.println(name);
        User u = userRepo.findByUsername(name);
        System.out.println(u);
        System.out.println(u.getId());
        System.out.println(u.getUsername());

        return u;

    }
}
