package com.example.supermarket.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.model.Product;
import com.example.supermarket.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    Product findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return product;
    }

    @PostMapping
    Product save(@RequestBody @Valid ProductDto productDto) {
        return productService
                .save(new Product(productDto.getName(), productDto.getPrice(), productDto.getStockQuantity()));
    }

    @DeleteMapping(value = "/{id}")
    void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
