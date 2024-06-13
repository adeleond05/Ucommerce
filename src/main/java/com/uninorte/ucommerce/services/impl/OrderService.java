package com.uninorte.ucommerce.services.impl;

import com.uninorte.ucommerce.dto.OrderDTO;
import com.uninorte.ucommerce.dto.ProductOrderDTO;
import com.uninorte.ucommerce.exception.CustomException;
import com.uninorte.ucommerce.models.Order;
import com.uninorte.ucommerce.models.Product;
import com.uninorte.ucommerce.repository.OrderRepository;
import com.uninorte.ucommerce.repository.ProductRepository;
import com.uninorte.ucommerce.repository.UserRepository;
import com.uninorte.ucommerce.services.IOrderService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

  static final String ORDER_NOT_FOUND_MESSAGE = "No se encuentra la orden";
  static final String USER_NOT_FOUND_MESSAGE = "No se encuentra el usuario";
  static final String PRODUCT_NOT_FOUND_MESSAGE ="No se encuentra el producto con id ";
  static final String PENDING_STATUS = "Pending";
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

  @Override
  public Optional<OrderDTO> getOrder(String id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new CustomException("404", ORDER_NOT_FOUND_MESSAGE));
    return Optional.of(modelMapper.map(order, OrderDTO.class));
  }

  @Override
  public List<OrderDTO> getAllOrders() {
    List<Order> orders = orderRepository.findAll();
    return orders.stream()
        .map(order -> modelMapper.map(order, OrderDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public OrderDTO saveOrder(OrderDTO orderDTO) {
    if (!userRepository.existsById(orderDTO.getUserId())) {
      throw new CustomException("404",USER_NOT_FOUND_MESSAGE);
    }
    validateProduct(orderDTO.getProducts());
    orderDTO.setTotalPrice(totalPrice(orderDTO.getProducts()));
    orderDTO.setStatus(PENDING_STATUS);
    Order order = modelMapper.map(orderDTO, Order.class);
    Order savedOrder = orderRepository.save(order);
    return modelMapper.map(savedOrder, OrderDTO.class);
  }

  private void validateProduct(List<ProductOrderDTO> products) {
      products.forEach(product -> {
        Product existingProduct = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new CustomException("404", PRODUCT_NOT_FOUND_MESSAGE + product.getProductId()));
        modelMapper.map(existingProduct, product);
      });
  }

  private Integer totalPrice(List<ProductOrderDTO> products) {
    return products.stream()
            .filter(product -> product.getPrice() != null && product.getQuantity() != null)
            .map(product -> product.getPrice() * product.getQuantity())
            .reduce(0, Integer::sum);
  }

  @Override
  public OrderDTO updateOrder(String id, OrderDTO orderDTO) {
    Order existingOrder = orderRepository.findById(id)
        .orElseThrow(() -> new CustomException("404", ORDER_NOT_FOUND_MESSAGE));
    modelMapper.map(orderDTO, existingOrder);
    Order updatedOrder = orderRepository.save(existingOrder);
    return modelMapper.map(updatedOrder, OrderDTO.class);
  }

  @Override
  public void deleteOrder(String id) {
    if (!orderRepository.existsById(id)) {
      throw new CustomException("404", ORDER_NOT_FOUND_MESSAGE);
    }
    orderRepository.deleteById(id);
  }

}

