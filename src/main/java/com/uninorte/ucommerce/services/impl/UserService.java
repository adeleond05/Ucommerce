package com.uninorte.ucommerce.services.impl;

import com.uninorte.ucommerce.dto.UserDTO;
import com.uninorte.ucommerce.models.User;
import com.uninorte.ucommerce.repository.UserRepository;
import com.uninorte.ucommerce.services.IUserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  @Override
  public Optional<UserDTO> getUser(String id) {
    Optional<User> user = userRepository.findById(id);
    return user.map(u -> modelMapper.map(u, UserDTO.class));
  }

  @Override
  public UserDTO saveUser(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    User savedUser = userRepository.save(user);
    return modelMapper.map(savedUser, UserDTO.class);
  }

}

