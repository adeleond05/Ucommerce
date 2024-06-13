package com.uninorte.ucommerce.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOrderDTO {


    @NotNull(message = "productId is mandatory")
    @Size(min = 5, max = 50, message = "productId must be between 5 and 50 characters")
    private String productId;

    @NotNull(message = "quantity is mandatory")
    @Max(value = 10000, message = "quantity must be less than 10000")
    private Integer quantity;

    private Integer price;
    private Integer stock;
    private String name;
    private String description;
    private String category;
}
