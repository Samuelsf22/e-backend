package com.ecom.e_backend.order.domain.models;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class OrderedProduct {
    private UUID productPublicId;
    private double price;
    private int quantity;
    private String productName;
}
