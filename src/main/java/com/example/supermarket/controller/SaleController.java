package com.example.supermarket.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.supermarket.dto.SaleDto;
import com.example.supermarket.exception.NotFoundException;
import com.example.supermarket.model.Product;
import com.example.supermarket.model.Sale;
import com.example.supermarket.service.ProductService;
import com.example.supermarket.service.SaleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    private final ProductService productService;

    @GetMapping
    List<Sale> findAll() {
        return saleService.findAll();
    }

    @GetMapping(value = "/{id}")
    Sale findById(@PathVariable Long id) {
        Optional<Sale> sale = saleService.findById(id);

        if (!sale.isPresent()) {
            throw new NotFoundException();
        }

        return sale.get();
    }

    @PostMapping
    Sale save(@RequestBody @Valid SaleDto saleDto) {
        Optional<Product> product = productService.findById(saleDto.getProductId());

        if (!product.isPresent()) {
            throw new Error(String.format("There is no product with id %s", saleDto.getProductId()));
        }

        Sale newSale = new Sale(product.get(), saleDto.getQuantity(), LocalDateTime.now());

        return saleService.save(newSale);
    }

}
