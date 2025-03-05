package com.web.finalproject.api;

import com.web.finalproject.entity.OrderItemEntity;
import com.web.finalproject.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemApi {
    private final OrderItemService orderItemService;

    public OrderItemApi(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItemEntity> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemEntity> getOrderItemById(@PathVariable int id) {
        Optional<OrderItemEntity> orderItem = orderItemService.getOrderItemById(id);
        return orderItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItemEntity> getOrderItemsByOrderId(@PathVariable int orderId) {
        return orderItemService.getOrderItemsByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<OrderItemEntity> createOrderItem(@RequestBody OrderItemEntity orderItem) {
        OrderItemEntity savedItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemEntity> updateOrderItem(@PathVariable int id, @RequestBody OrderItemEntity orderItemDetails) {
        try {
            OrderItemEntity updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDetails);
            return ResponseEntity.ok(updatedOrderItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}

