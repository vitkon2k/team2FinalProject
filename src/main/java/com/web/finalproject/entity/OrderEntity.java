package com.web.finalproject.entity;

import com.web.finalproject.enums.OrderStatus;
import com.web.finalproject.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", columnDefinition = "ENUM('pending', 'shipped', 'delivered', 'canceled') DEFAULT 'pending'")
    private OrderStatus orderStatus = OrderStatus.pending;

    @Column(name = "shipping_address", columnDefinition = "TEXT")
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", columnDefinition = "ENUM('pending', 'paid', 'failed') DEFAULT 'pending'")
    private PaymentStatus paymentStatus = PaymentStatus.pending;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

}