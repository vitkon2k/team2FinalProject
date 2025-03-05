package com.web.finalproject.service.impl;

import com.web.finalproject.entity.OrderItemEntity;
import com.web.finalproject.repository.OrderItemRepository;
import com.web.finalproject.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItemEntity> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public List<OrderItemEntity> getOrderItemsByOrderId(int orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    public Optional<OrderItemEntity> getOrderItemById(int id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItemEntity createOrderItem(OrderItemEntity orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItemEntity updateOrderItem(int id, OrderItemEntity orderItemDetails) {
        return orderItemRepository.findById(id)
                .map(orderItem -> {
                    orderItem.setOrderId(orderItemDetails.getOrderId());
                    orderItem.setProductId(orderItemDetails.getProductId());
                    orderItem.setQuantity(orderItemDetails.getQuantity());
                    orderItem.setUnitPrice(orderItemDetails.getUnitPrice());
                    return orderItemRepository.save(orderItem);
                })
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    @Override
    public void deleteOrderItem(int id) {
        orderItemRepository.deleteById(id);
    }
}

