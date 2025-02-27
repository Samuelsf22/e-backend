package com.ecom.e_backend.order.domain.models;

import java.util.UUID;

import com.ecom.e_backend.order.domain.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class Order {

    private Long id;
    private UUID publicId;
    private Long userId;
    private OrderStatus status;

    public static Order create(Long userId) {
        return Order.builder()
                .publicId(UUID.randomUUID())
                .userId(userId)
                .status(OrderStatus.PENDING)
                .build();
    }

    public void validatePayment() {
        this.status = OrderStatus.PAID;
    }
}
