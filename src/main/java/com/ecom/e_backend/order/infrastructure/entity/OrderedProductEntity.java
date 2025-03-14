package com.ecom.e_backend.order.infrastructure.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.ecom.e_backend.order.domain.models.OrderedProduct;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table("api_ordered_product")
public class OrderedProductEntity {

    @Id
    private Long id;

    @Column("order_id")
    private Long orderId;

    @Column("product_public_id")
    private UUID productPublicId;

    private Double price;

    private Integer quantity;   

    @Column("product_name")
    private String productName;

    public static OrderedProductEntity toEntity(OrderedProduct orderedProduct) {
        return OrderedProductEntity.builder()
                .id(orderedProduct.getId())
                .orderId(orderedProduct.getOrderId())
                .productPublicId(orderedProduct.getProductPublicId())
                .price(orderedProduct.getPrice())
                .quantity(orderedProduct.getQuantity())
                .productName(orderedProduct.getProductName())
                .build();
    }

    public static OrderedProduct toDomain(OrderedProductEntity orderedProductEntity) {
        return OrderedProduct.builder()
                .id(orderedProductEntity.getId())
                .orderId(orderedProductEntity.getOrderId())
                .productPublicId(orderedProductEntity.getProductPublicId())
                .price(orderedProductEntity.getPrice())
                .quantity(orderedProductEntity.getQuantity())
                .productName(orderedProductEntity.getProductName())
                .build();
    }

}
