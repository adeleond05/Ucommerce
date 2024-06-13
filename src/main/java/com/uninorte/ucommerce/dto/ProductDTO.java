package com.uninorte.ucommerce.dto;

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
  private String name;
  private String description;
  private String price;
  private String category;
}
