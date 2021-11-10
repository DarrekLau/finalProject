package com.example.finalproject.service;

import com.example.finalproject.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceImpl {


    @Override
    public void createProduct(Product newProduct) {

    }

    @Override
    public Optional<Product> getProduct(Integer id) {
        return Optional.empty();
    }

    @Override
    public void updateProduct(Product updatedProduct) {

    }

    @Override
    public void deleteProduct(Integer id) {

    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Product createOrUpdateProduct(Product entity) {
        return null;
    }

    @Override
    public List<Product> findAllByUserId(Integer id) {
        return null;
    }
}
