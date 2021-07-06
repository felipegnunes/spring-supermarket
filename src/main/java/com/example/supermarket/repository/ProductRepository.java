package com.example.supermarket.repository;

import com.example.supermarket.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query(value = "UPDATE products p SET p.stock_quantity = p.stock_quantity - ?2 WHERE p.id = ?1", nativeQuery = true)
    void decrementStockQuantity(Long id, Integer decrement);
}
