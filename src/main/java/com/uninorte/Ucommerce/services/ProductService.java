package com.uninorte.Ucommerce.services;

import com.uninorte.Ucommerce.dto.ProductDTO;
import com.uninorte.Ucommerce.exception.CustomException;
import com.uninorte.Ucommerce.models.Product;
import com.uninorte.Ucommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<ProductDTO> getProduct(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(p -> modelMapper.map(p, ProductDTO.class));
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            throw new CustomException("404", "No se encuentra el producto");
        }
        Product existingProduct = optionalProduct.get();
        modelMapper.map(productDTO, existingProduct);
        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new CustomException("404", "No se encuentra el producto");
        }
        productRepository.deleteById(id);
    }
}