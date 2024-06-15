package com.uninorte.ucommerce.services.impl;

import com.uninorte.ucommerce.dto.ProductDTO;
import com.uninorte.ucommerce.dto.UserDTO;
import com.uninorte.ucommerce.models.User;
import com.uninorte.ucommerce.repository.UserRepository;
import com.uninorte.ucommerce.services.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    Optional<User> user = userRepository.findByIdFirebase(id);
    return user.map(u -> modelMapper.map(u, UserDTO.class));
  }

  @Override
  public List<UserDTO> getAllUser() {
    List<User> users = userRepository.findAll();
    return users.stream()
            .map(product -> modelMapper.map(product, UserDTO.class))
            .collect(Collectors.toList());
  }

  @Override
  public UserDTO saveUser(UserDTO userDTO) {
    User user = modelMapper.map(userDTO, User.class);
    User savedUser = userRepository.save(user);
    return modelMapper.map(savedUser, UserDTO.class);
  }

}

