package com.uninorte.ucommerce.models;

import com.uninorte.ucommerce.dto.ProductDTO;
import java.util.List;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  List<ProductDTO> products;
  @Id
  private String id;
  private String userId;
  private String status;
  private Integer totalPrice;
}
