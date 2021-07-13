package com.example.supermarket.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.supermarket.exception.EntityNotFoundException;
import com.example.supermarket.model.Product;
import com.example.supermarket.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductServiceTests {
    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    private ProductService productService;

    @BeforeEach
    void setup() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void shouldSaveProduct() {
        Product product = product();

        when(productRepository.save(any(Product.class))).then(returnsFirstArg());
        Product savedProduct = productService.save(product);

        assertThat(savedProduct).isEqualTo(product);
    }

    @Test
    void shouldFindProduct() {
        Product product = product();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product foundProduct = productService.findById(1L);

        assertThat(foundProduct).isEqualTo(product);
    }

    @Test
    void shouldThrowWhenNotFound() {
        when(productRepository.findById(1L)).thenThrow(new EntityNotFoundException());

        assertThatThrownBy(() -> {
            productService.findById(7L);
        }).isInstanceOf(EntityNotFoundException.class);
    }

    Product product() {
        return new Product("Sugar", new BigDecimal("10.99"), 10);
    }
}
