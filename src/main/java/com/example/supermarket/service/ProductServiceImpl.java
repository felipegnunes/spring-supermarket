package com.example.supermarket.service;

import java.util.List;
import java.util.Optional;

import com.example.supermarket.exception.EntityNotFoundException;
import com.example.supermarket.model.Product;
import com.example.supermarket.repository.ProductRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void decrementStockQuantity(Long productId, Integer quantity) {
        productRepository.decrementStockQuantity(productId, quantity);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            throw new EntityNotFoundException();
        }

        return product.get();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
