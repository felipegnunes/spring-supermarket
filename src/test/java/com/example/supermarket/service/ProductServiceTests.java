package com.example.supermarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import com.example.supermarket.model.Product;
import com.example.supermarket.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setup() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void iCanDoATest() {
        productService.findAll();
        assertEquals(true, true);
    }

    @Test
    void shouldCreateProduct() {
        String productName = "Sugar";

        Product product = productService.save(new Product(productName, new BigDecimal("10.99"), 10));

        assertEquals(product.getName(), productName);
    }
}
