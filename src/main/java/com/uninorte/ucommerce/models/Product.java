package com.uninorte.ucommerce.models;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  private String id;
  private String name;
  private String description;
  private Integer price;
  private String category;
  private Integer stock;

}
