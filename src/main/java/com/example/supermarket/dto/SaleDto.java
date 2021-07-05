package com.example.supermarket.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaleDto {

    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private Integer quantity;

}
