package com.uninorte.ucommerce.models;

import javax.persistence.Id;

import com.uninorte.ucommerce.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  @Id
  private String id;
  private String userId;
  private String status;
  private Integer totalPrice;
  List<ProductDTO> products;
}
