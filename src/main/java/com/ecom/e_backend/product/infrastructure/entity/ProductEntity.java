package com.ecom.e_backend.product.infrastructure.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
@Table("api_product")
public class ProductEntity {

    @Id
    private Long id;

    @Column("public_id")
    private UUID publicId;

    private String name;
    private String description;
    private String brand;
    private String color;
    private double price;
    private boolean featured;
    private int stock;
    
    @Column("picture_url")
    private String pictureUrl;

    @Column("category_id")
    private Long categoryId;
}
