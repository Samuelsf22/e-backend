package com.ecom.e_backend.order;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.ecom.e_backend.order.v0.OrderStatus;
import com.ecom.e_backend.security.entity.User;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {
    @Id
    private Long id;
    private UUID publicId;
    private User user;
    private OrderStatus status;
    private List<OrderedProductEntity> orderedProducts;

    public static OrderEntity create(User connectedUser, List<OrderedProductEntity> orderedProducts) {
        return OrderEntity.builder()
                .user(connectedUser)
                .status(OrderStatus.PENDING)
                .orderedProducts(orderedProducts)
                .publicId(UUID.randomUUID())
                .build();
    }

    public void validatePayment() {
        this.status = OrderStatus.PAID;
    }
}
