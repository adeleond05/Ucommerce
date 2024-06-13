package com.uninorte.ucommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

  private String id;

  @NotNull(message = "userId is mandatory")
  @Size(min = 5, max = 50, message = "userId must be between 5 and 50 characters")
  private String userId;

  @NotNull(message = "products is mandatory")
  @Valid
  List<ProductOrderDTO> products;

  private String status;
  private Integer totalPrice;
}
