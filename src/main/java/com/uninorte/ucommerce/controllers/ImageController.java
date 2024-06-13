package com.uninorte.ucommerce.controllers;

import com.uninorte.ucommerce.services.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

  private final IImageService imageService;

  @PostMapping("/upload")
  public ResponseEntity<String> uploadImage(@RequestBody String base64Image) {
    String imageUrl = imageService.saveImage(base64Image);
    return new ResponseEntity<>(imageUrl, HttpStatus.CREATED);
  }
}