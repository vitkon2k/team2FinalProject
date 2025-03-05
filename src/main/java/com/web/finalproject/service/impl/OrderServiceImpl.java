package com.web.finalproject.service.impl;

import com.web.finalproject.entity.OrderEntity;
import com.web.finalproject.repository.OrderRepository;
import com.web.finalproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public OrderEntity createOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity updateOrder(int id, OrderEntity orderEntityDetails) {
        return orderRepository.findById(id).map(orderEntity -> {
            orderEntity.setUserId(orderEntityDetails.getUserId());
            orderEntity.setTotalPrice(orderEntityDetails.getTotalPrice());
            orderEntity.setOrderStatus(orderEntityDetails.getOrderStatus());
            orderEntity.setShippingAddress(orderEntityDetails.getShippingAddress());
            orderEntity.setPaymentStatus(orderEntityDetails.getPaymentStatus());
            return orderRepository.save(orderEntity);
        }).orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}

