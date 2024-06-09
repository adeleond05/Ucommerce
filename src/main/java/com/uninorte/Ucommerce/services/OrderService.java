package com.uninorte.Ucommerce.services;

import com.uninorte.Ucommerce.dto.OrderDTO;
import com.uninorte.Ucommerce.exception.CustomException;
import com.uninorte.Ucommerce.models.Order;
import com.uninorte.Ucommerce.repository.OrderRepository;
import com.uninorte.Ucommerce.repository.ProductRepository;
import com.uninorte.Ucommerce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, UserRepository userRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Optional<OrderDTO> getOrder(String id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(o -> modelMapper.map(o, OrderDTO.class));
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        if (!productRepository.existsById(orderDTO.getProductId())) {
            throw new CustomException("404", "No se encuentra el producto");
        }

        if (!userRepository.existsById(orderDTO.getUserId())) {
            throw new CustomException("404", "No se encuentra el usuario");
        }
        Order order = modelMapper.map(orderDTO, Order.class);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDTO.class);
    }

    public OrderDTO updateOrder(String id, OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            throw new CustomException("404", "No se encuentra la orden");
        }
        Order existingOrder = optionalOrder.get();
        modelMapper.map(orderDTO, existingOrder);
        Order updatedOrder = orderRepository.save(existingOrder);
        return modelMapper.map(updatedOrder, OrderDTO.class);
    }

    public void deleteOrder(String id) {
        if (!orderRepository.existsById(id)) {
            throw new CustomException("404", "No se encuentra la orden");
        }
        orderRepository.deleteById(id);
    }
}

