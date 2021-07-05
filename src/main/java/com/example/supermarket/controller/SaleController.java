package com.example.supermarket.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.supermarket.dto.SaleDto;
import com.example.supermarket.exception.NotFoundException;
import com.example.supermarket.model.Sale;
import com.example.supermarket.service.SaleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;

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
        return saleService.save(saleDto);
    }

}
