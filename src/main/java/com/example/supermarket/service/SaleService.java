package com.example.supermarket.service;

import java.util.List;
import java.util.Optional;

import com.example.supermarket.model.Sale;

public interface SaleService {
    List<Sale> findAll();

    Optional<Sale> findById(Long id);

    Sale save(Sale sale);

    void deleteById(Long id);
}
