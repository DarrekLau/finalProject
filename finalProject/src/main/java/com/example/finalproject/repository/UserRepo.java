package com.example.finalproject.repository;

import com.example.finalproject.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;



public interface UserRepo extends JpaRepository <User, Integer> {

//    @Query("SELECT n FROM User n WHERE n.username = ?1")
//
    User findByUsername(String username);
}
