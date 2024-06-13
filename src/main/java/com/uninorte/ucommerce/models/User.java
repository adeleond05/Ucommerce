package com.uninorte.ucommerce.models;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private String id;
  private String idFirebase;
  private String name;
  private String email;
}
