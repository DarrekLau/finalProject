package com.example.finalproject.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository <User, Integer> {

//    @Query("SELECT n FROM User n WHERE n.username = ?1")

    User findByUsername(String username);
}
