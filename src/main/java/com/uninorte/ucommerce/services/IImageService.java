package com.uninorte.ucommerce.services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

  ResponseEntity<Resource> getImage(String idProduct);

  String saveImage(MultipartFile file, String fileName);

  void deleteImage(String fileName);
}
