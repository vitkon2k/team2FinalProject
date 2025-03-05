package com.web.finalproject.api;

import com.web.finalproject.entity.OrderEntity;
import com.web.finalproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/orders")
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable int id) {
        Optional<OrderEntity> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity orderEntity) {
        return orderService.createOrder(orderEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable int id, @RequestBody OrderEntity orderEntityDetails) {
        try {
            OrderEntity updatedOrderEntity = orderService.updateOrder(id, orderEntityDetails);
            return ResponseEntity.ok(updatedOrderEntity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}