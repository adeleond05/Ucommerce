package com.uninorte.ucommerce.controllers;

import com.uninorte.ucommerce.dto.UserDTO;
import com.uninorte.ucommerce.exception.CustomException;
import com.uninorte.ucommerce.services.IUserService;
import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

  private final IUserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable("id") String id) {
    Optional<UserDTO> user = userService.getUser(id);
    if (user.isPresent()) {
      return new ResponseEntity<>(user.get(), HttpStatus.OK);
    } else {
      throw new CustomException("404", "No se encuentra el usuario");
    }
  }

  @PostMapping
  public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO user) {
    UserDTO newUser = userService.saveUser(user);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }


}
