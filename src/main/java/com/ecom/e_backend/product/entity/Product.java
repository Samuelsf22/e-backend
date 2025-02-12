package com.ecom.e_backend.product.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
@Table("api_product")
public class Product {
    @Id
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
