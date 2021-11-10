package com.example.finalproject.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private int id;
    private String name;
    private String description;
    private int price;

    @Column(nullable = true, length = 64)
    private String imageURL;

    @Column(name = "user_id")
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(int id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, int price, Integer user_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.user_id = user_id;
    }

    public Product(){
        super();
    }
}
