package com.ecom.e_backend.order.infrastructure.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.ecom.e_backend.order.domain.OrderStatus;
import com.ecom.e_backend.order.domain.models.Order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table("api_order")
public class OrderEntity {

    @Id
    private Long id;

    @Column("public_id")
    private UUID publicId;

    @Column("user_id")
    private Long userId;

    private OrderStatus status;

    public static OrderEntity toEntity(Order order) {
        return OrderEntity.builder()
            .id(order.getId())
            .publicId(order.getPublicId())
            .userId(order.getUserId())
            .status(order.getStatus())
            .build();
    }

    public static Order toDomain(OrderEntity orderEntity) {
        return Order.builder()
            .id(orderEntity.getId())
            .publicId(orderEntity.getPublicId())
            .userId(orderEntity.getUserId())
            .status(orderEntity.getStatus())
            .build();
    }
    
}
