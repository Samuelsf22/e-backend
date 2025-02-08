package com.ecom.e_backend.order;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.ecom.e_backend.order.v0.OrderStatus;
import com.ecom.e_backend.user.User;

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
public class Order {
    @Id
    private Long id;
    private UUID publicId;
    private User user;
    private OrderStatus status;
    private List<OrderedProduct> orderedProducts;

    public static Order create(User connectedUser, List<OrderedProduct> orderedProducts) {
        return Order.builder()
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
