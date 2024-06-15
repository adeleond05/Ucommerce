package com.uninorte.ucommerce.services;

import com.uninorte.ucommerce.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {

  Optional<UserDTO> getUser(String id);

  List<UserDTO> getAllUser();

  UserDTO saveUser(UserDTO userDTO);
}
