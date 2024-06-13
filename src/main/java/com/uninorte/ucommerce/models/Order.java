package com.uninorte.ucommerce.models;

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

  @Id
  private String id;
  private String productId;
  private String userId;
  private String status;
}
