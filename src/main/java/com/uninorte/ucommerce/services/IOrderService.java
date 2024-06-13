package com.uninorte.ucommerce.services;

import com.uninorte.ucommerce.dto.OrderDTO;
import java.util.List;
import java.util.Optional;

public interface IOrderService {

  Optional<OrderDTO> getOrder(String id);

  List<OrderDTO> getAllOrders();

  OrderDTO saveOrder(OrderDTO orderDTO);

  OrderDTO updateOrder(String id, OrderDTO orderDTO);

  void deleteOrder(String id);
}
