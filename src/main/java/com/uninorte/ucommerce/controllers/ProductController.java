package com.uninorte.ucommerce.controllers;

import com.uninorte.ucommerce.dto.ProductDTO;
import com.uninorte.ucommerce.exception.CustomException;
import com.uninorte.ucommerce.services.IProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class ProductController {

  private final IProductService productService;

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") String id) {
    Optional<ProductDTO> product = productService.getProduct(id);
    if (product.isPresent()) {
      return new ResponseEntity<>(product.get(), HttpStatus.OK);
    } else {
      throw new CustomException("404", "No se encuentra el producto");
    }
  }

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    List<ProductDTO> products = productService.getAllProducts();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ProductDTO> saveProduct(
          @NotNull(message = "name cannot be null") @RequestParam("name") String name,
          @NotNull(message = "description cannot be null") @RequestParam("description") String description,
          @NotNull(message = "price cannot be null") @RequestParam("price") Integer price,
          @NotNull(message = "category cannot be null") @RequestParam("category") String category,
          @NotNull(message = "stock cannot be null") @RequestParam("stock") Integer stock,
          @RequestPart("file") @NotNull(message = "File cannot be null") MultipartFile file) {
    ProductDTO product = ProductDTO.builder().name(name).category(category).description(description)
            .price(price).stock(stock).build();
    ProductDTO newProduct = productService.saveProduct(product, file);
    return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(
          @PathVariable("id") String id,
          @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "description", required = false) String description,
          @RequestParam(value = "price", required = false) Integer price,
          @RequestParam(value = "category", required = false) String category,
          @RequestParam(value = "stock", required = false) Integer stock,
          @RequestPart(value = "file", required = false) MultipartFile file) {
    ProductDTO product = ProductDTO.builder().name(name).category(category).description(description)
            .price(price).stock(stock).build();
    ProductDTO updatedProduct = productService.updateProduct(id, product, file);
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
    productService.deleteProduct(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
