package com.ecom.e_backend.product.infrastructure.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.ecom.e_backend.product.domain.models.Product;

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
    
    private String imagePublicId;
    private String imageUrl;
    private String imageName;

    @Column("category_id")
    private Long categoryId;

    public static ProductEntity toEntity(Product product) {
        return ProductEntity.builder()
            .id(product.getId())
            .publicId(product.getPublicId())
            .name(product.getName())
            .description(product.getDescription())
            .brand(product.getBrand())
            .color(product.getColor())
            .price(product.getPrice())
            .featured(product.isFeatured())
            .stock(product.getStock())
            .imagePublicId(product.getImagePublicId())
            .imageUrl(product.getImageUrl())
            .imageName(product.getImageName())
            .categoryId(product.getCategoryId())
            .build();
    }

    public static Product toDomain(ProductEntity productEntity) {
        return Product.builder()
            .id(productEntity.getId())
            .publicId(productEntity.getPublicId())
            .name(productEntity.getName())
            .description(productEntity.getDescription())
            .brand(productEntity.getBrand())
            .color(productEntity.getColor())
            .price(productEntity.getPrice())
            .featured(productEntity.isFeatured())
            .stock(productEntity.getStock())
            .imagePublicId(productEntity.getImagePublicId())
            .imageUrl(productEntity.getImageUrl())
            .imageName(productEntity.getImageName())
            .categoryId(productEntity.getCategoryId())
            .build();
    }

}
