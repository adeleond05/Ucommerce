package com.uninorte.ucommerce.services;

import com.uninorte.ucommerce.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IProductService {

  Optional<ProductDTO> getProduct(String id);

  List<ProductDTO> getAllProducts();

  ProductDTO saveProduct(ProductDTO productDTO, MultipartFile file);

  ProductDTO updateProduct(String id, ProductDTO productDTO, MultipartFile file);

  void deleteProduct(String id);
}
