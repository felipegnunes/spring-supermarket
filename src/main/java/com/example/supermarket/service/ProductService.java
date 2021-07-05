package com.example.supermarket.service;

import java.util.List;
import java.util.Optional;

import com.example.supermarket.model.Product;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void decrementStockQuantity(Long productId, Integer quantity);

    void deleteById(Long id);
}
