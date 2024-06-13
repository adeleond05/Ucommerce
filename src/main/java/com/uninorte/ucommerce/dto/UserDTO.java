package com.uninorte.ucommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class UserDTO {

  private String id;

  @NotNull
  @Size(min = 0, max = 50)
  @NotBlank(message = "idFirebase is mandatory")
  private String idFirebase;

  @NotNull
  @Size(min = 0, max = 50)
  @NotBlank(message = "name is mandatory")
  private String name;

  @NotNull
  @Size(min = 0, max = 50)
  @Email(message = "Email should be valid")
  @NotBlank(message = "Email is mandatory")
  private String email;
}
