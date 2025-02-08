package com.ecom.e_backend.order;

import java.util.UUID;

import com.ecom.e_backend.product.Product;

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
public class OrderedProductEntity {
    private UUID productPublicId;
    private double price;
    private int quantity;
    private String productName;

    public static OrderedProductEntity create(int quantity, Product product){
        return OrderedProductEntity.builder()
            .price(product.getPrice())
            .quantity(quantity)
            .productName(product.getName())
            .productPublicId(product.getPublicId())
            .build();
    }
}
