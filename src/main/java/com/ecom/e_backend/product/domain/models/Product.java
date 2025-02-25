package com.ecom.e_backend.product.domain.models;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {

    private Long id;
    private UUID publicId;
    private String name;
    private String description;
    private String brand;
    private String color;
    private double price;
    private boolean featured;
    private int stock;
    private String pictureUrl;
    private Long categoryId;

}
