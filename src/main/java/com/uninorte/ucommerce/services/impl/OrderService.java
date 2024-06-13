package com.uninorte.ucommerce.services.impl;

import static com.uninorte.ucommerce.services.impl.ProductService.PRODUCT_NOT_FOUND_MESSAGE;

import com.uninorte.ucommerce.dto.OrderDTO;
import com.uninorte.ucommerce.exception.CustomException;
import com.uninorte.ucommerce.models.Order;
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
    if (!productRepository.existsById(orderDTO.getProductId())) {
      throw new CustomException("404", PRODUCT_NOT_FOUND_MESSAGE);
    }
    if (!userRepository.existsById(orderDTO.getUserId())) {
      throw new CustomException("404", "No se encuentra el usuario");
    }
    Order order = modelMapper.map(orderDTO, Order.class);
    Order savedOrder = orderRepository.save(order);
    return modelMapper.map(savedOrder, OrderDTO.class);
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

