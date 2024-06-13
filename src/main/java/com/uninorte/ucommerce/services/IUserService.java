package com.uninorte.ucommerce.services;

import com.uninorte.ucommerce.dto.UserDTO;
import java.util.Optional;

public interface IUserService {

  Optional<UserDTO> getUser(String id);

  UserDTO saveUser(UserDTO userDTO);
}
