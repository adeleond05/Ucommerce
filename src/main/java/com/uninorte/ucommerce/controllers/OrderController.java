package com.uninorte.ucommerce.controllers;

import com.uninorte.ucommerce.dto.OrderDTO;
import com.uninorte.ucommerce.exception.CustomException;
import com.uninorte.ucommerce.services.IOrderService;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

  private final IOrderService orderService;

  @GetMapping("/{id}")
  public ResponseEntity<OrderDTO> getOrder(@PathVariable("id") String id) {
    Optional<OrderDTO> order = orderService.getOrder(id);
    if (order.isPresent()) {
      return new ResponseEntity<>(order.get(), HttpStatus.OK);
    } else {
      throw new CustomException("404", "No se encuentra la orden");
    }
  }

  @GetMapping
  public ResponseEntity<List<OrderDTO>> getAllOrders() {
    List<OrderDTO> orders = orderService.getAllOrders();
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<OrderDTO> saveOrder(@Valid @RequestBody OrderDTO order) {
    OrderDTO newOrder = orderService.saveOrder(order);
    return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") String id,
      @RequestBody OrderDTO order) {
    OrderDTO updatedOrder = orderService.updateOrder(id, order);
    return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable("id") String id) {
    orderService.deleteOrder(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
