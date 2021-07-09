package com.example.supermarket.service;

import java.util.List;

import com.example.supermarket.model.Sale;

public interface SaleService {
    List<Sale> findAll();

    Sale findById(Long id);

    Sale save(Sale sale);

    void deleteById(Long id);
}