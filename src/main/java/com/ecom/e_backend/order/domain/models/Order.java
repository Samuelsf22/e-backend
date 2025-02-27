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

    public void validatePayment() {
        this.status = OrderStatus.PAID;
    }
}
