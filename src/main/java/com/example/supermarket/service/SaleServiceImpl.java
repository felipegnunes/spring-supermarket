package com.example.supermarket.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.supermarket.exception.EntityNotFoundException;
import com.example.supermarket.model.Product;
import com.example.supermarket.model.Sale;
import com.example.supermarket.repository.SaleRepository;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    private final ProductService productService;

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Sale findById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);

        if (!sale.isPresent()) {
            throw new EntityNotFoundException();
        }

        return sale.get();
    }

    @Override
    @Transactional
    public Sale save(Sale sale) {
        Product product = sale.getProduct();

        if (sale.getQuantity() > product.getStockQuantity()) {
            throw new Error("You're trying to sell more items than the current stock has.");
        }

        productService.decrementStockQuantity(product.getId(), sale.getQuantity());

        return saleRepository.save(sale);
    }

    @Override
    public void deleteById(Long id) {
        throw new NotYetImplementedException();
    }
}
