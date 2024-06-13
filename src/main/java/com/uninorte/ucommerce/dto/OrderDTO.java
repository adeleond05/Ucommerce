package com.uninorte.ucommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

  @NotNull(message = "products is mandatory")
  List<ProductDTO> products;
  private String id;
  @NotNull(message = "userId is mandatory")
  @Size(min = 5, max = 50, message = "userId must be between 5 and 50 characters")
  private String userId;
  @NotNull(message = "status is mandatory")
  @Size(min = 0, max = 50, message = "userId must be between 0 and 50 characters")
  private String status;
  private Integer totalPrice;
}
