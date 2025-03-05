package com.web.finalproject.service;

import com.web.finalproject.entity.OrderItemEntity;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItemEntity> getAllOrderItems();
    List<OrderItemEntity> getOrderItemsByOrderId(int orderId);
    Optional<OrderItemEntity> getOrderItemById(int id);
    OrderItemEntity createOrderItem(OrderItemEntity orderItem);
    OrderItemEntity updateOrderItem(int id, OrderItemEntity orderItemDetails);
    void deleteOrderItem(int id);
}
