package com.uninorte.ucommerce.services.impl;

import com.uninorte.ucommerce.dto.ProductDTO;
import com.uninorte.ucommerce.exception.CustomException;
import com.uninorte.ucommerce.models.Product;
import com.uninorte.ucommerce.repository.ProductRepository;
import com.uninorte.ucommerce.services.IProductService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

  static final String PRODUCT_NOT_FOUND_MESSAGE = "No se encuentra el producto";
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

  @Override
  public Optional<ProductDTO> getProduct(String id) {
    Optional<Product> product = productRepository.findById(id);
    return product.map(p -> modelMapper.map(p, ProductDTO.class));
  }

  @Override
  public List<ProductDTO> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream()
        .map(product -> modelMapper.map(product, ProductDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public ProductDTO saveProduct(ProductDTO productDTO) {
    Product product = modelMapper.map(productDTO, Product.class);
    Product savedProduct = productRepository.save(product);
    return modelMapper.map(savedProduct, ProductDTO.class);
  }

  @Override
  public ProductDTO updateProduct(String id, ProductDTO productDTO) {
    Optional<Product> optionalProduct = productRepository.findById(id);
    if (!optionalProduct.isPresent()) {
      throw new CustomException("404", PRODUCT_NOT_FOUND_MESSAGE);
    }
    Product existingProduct = optionalProduct.get();
    modelMapper.map(productDTO, existingProduct);
    Product updatedProduct = productRepository.save(existingProduct);
    return modelMapper.map(updatedProduct, ProductDTO.class);
  }

  @Override
  public void deleteProduct(String id) {
    if (!productRepository.existsById(id)) {
      throw new CustomException("404", PRODUCT_NOT_FOUND_MESSAGE);
    }
    productRepository.deleteById(id);
  }
}