package com.example.finalproject.repository;

import com.example.finalproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepo extends JpaRepository <Product, Integer>{

//    @Query("SELECT n FROM Product n WHERE n.user_id = ?1")
}
