package com.example.supermarket.repository;

import com.example.supermarket.model.Sale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
