package com.example.supermarket.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @ManyToOne
    // private Product product;

    private Integer quantity;
    private LocalDateTime saleDateTime;

    public Sale() {
    }
}
