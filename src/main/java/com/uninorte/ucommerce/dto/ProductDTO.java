package com.uninorte.ucommerce.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

  private String id;

  @NotNull(message = "Name is mandatory")
  @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
  private String name;

  @NotNull(message = "Description is mandatory")
  @Size(min = 1, max = 50, message = "Description must be between 1 and 50 characters")
  private String description;

  @NotNull(message = "Price is mandatory")
  @Min(value = 0, message = "Price must be at least 0")
  @Max(value = 10000, message = "Price must be less than 10000")
  private Integer price;

  @NotNull(message = "Category is mandatory")
  @Size(min = 1, max = 250, message = "Category must be between 1 and 10 characters")
  private String category;

  @NotNull(message = "Stock is mandatory")
  @Min(value = 0, message = "Stock must be at least 0")
  @Max(value = 10000, message = "Stock must be less than 10000")
  private Integer stock;

  private String image;
}
