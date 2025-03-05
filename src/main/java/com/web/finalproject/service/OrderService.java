package com.web.finalproject.service;

import com.web.finalproject.entity.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderEntity> getAllOrders();
    Optional<OrderEntity> getOrderById(int id);
    OrderEntity createOrder(OrderEntity orderEntity);
    OrderEntity updateOrder(int id, OrderEntity orderEntityDetails);
    void deleteOrder(int id);
}
