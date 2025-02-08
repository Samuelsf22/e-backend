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
public class OrderedProduct {
    private UUID publicId;
    private double price;
    private int quantity;
    private String name;

    public static OrderedProduct create(int quantity, Product product){
        return OrderedProduct.builder()
            .price(product.getPrice())
            .quantity(quantity)
            .name(product.getName())
            .publicId(product.getPublicId())
            .build();
    }
}
