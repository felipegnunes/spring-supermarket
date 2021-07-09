package com.example.supermarket.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.model.Product;
import com.example.supermarket.model.UserRole;
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
    @RolesAllowed({ UserRole.CASHIER, UserRole.ADMIN })
    List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/{id}")
    @RolesAllowed({ UserRole.CASHIER, UserRole.ADMIN })
    Product findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return product;
    }

    @PostMapping
    @RolesAllowed({ UserRole.ADMIN })
    Product save(@RequestBody @Valid ProductDto productDto) {
        return productService
                .save(new Product(productDto.getName(), productDto.getPrice(), productDto.getStockQuantity()));
    }

    @DeleteMapping(value = "/{id}")
    @RolesAllowed({ UserRole.ADMIN })
    void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
