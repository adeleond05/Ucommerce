package com.uninorte.ucommerce.services;

import com.uninorte.ucommerce.dto.ProductDTO;
import java.util.List;
import java.util.Optional;

public interface IProductService {

  Optional<ProductDTO> getProduct(String id);

  List<ProductDTO> getAllProducts();

  ProductDTO saveProduct(ProductDTO productDTO);

  ProductDTO updateProduct(String id, ProductDTO productDTO);

  void deleteProduct(String id);
}
