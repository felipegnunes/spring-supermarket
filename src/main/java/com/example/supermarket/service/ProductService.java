package com.example.supermarket.service;

import java.util.List;

import com.example.supermarket.exception.EntityNotFoundException;
import com.example.supermarket.model.Product;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long id) throws EntityNotFoundException;

    Product save(Product product);

    void decrementStockQuantity(Long productId, Integer quantity);

    void deleteById(Long id);
}
