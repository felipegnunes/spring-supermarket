package com.example.supermarket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.supermarket.dto.SaleDto;
import com.example.supermarket.model.Product;
import com.example.supermarket.model.Sale;
import com.example.supermarket.repository.SaleRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private ProductService productService;

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    public Sale save(SaleDto saleDto) {
        Optional<Product> product = productService.findById(saleDto.getProductId());

        if (!product.isPresent()) {
            throw new Error("Product doesn't exist");
        }

        if (saleDto.getQuantity() > product.get().getStockQuantity()) {
            throw new Error("You're trying to sell more items than the current stock has.");
        }

        Sale sale = new Sale(product.get(), saleDto.getQuantity(), LocalDateTime.now());

        productService.decrementStockQuantity(product.get().getId(), sale.getQuantity());
        saleRepository.save(sale);

        return sale;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
    }
}
