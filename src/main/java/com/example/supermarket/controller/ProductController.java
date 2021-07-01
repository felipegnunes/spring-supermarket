package com.example.supermarket.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.exception.NotFoundException;
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

    private ProductService productService;

    @GetMapping
    List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);

        if (!product.isPresent()) {
            throw new NotFoundException();
        }

        return product.get();
    }

    @PostMapping
    Product save(@RequestBody @Valid ProductDto productDto) {
        return productService
                .save(new Product(productDto.getName(), productDto.getPrice(), productDto.getStockQuantity()));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
