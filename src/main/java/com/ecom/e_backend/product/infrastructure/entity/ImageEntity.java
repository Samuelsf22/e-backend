package com.ecom.e_backend.product.infrastructure.entity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.ecom.e_backend.product.domain.models.Image;

import lombok.Builder;

@Getter
@Setter
@Builder
@Table("api_product_image")
public class ImageEntity {

    @Id
    private Long id;
    private String publicId;
    private String name;
    private String imageUrl;

    public static ImageEntity toEntity(Image image) {
        return ImageEntity.builder()
            .id(image.getId())
            .publicId(image.getPublicId())
            .name(image.getName())
            .imageUrl(image.getImageUrl())
            .build();
    }

    public static Image toDomain(ImageEntity imageEntity) {
        return Image.builder()
            .id(imageEntity.getId())
            .publicId(imageEntity.getPublicId())
            .name(imageEntity.getName())
            .imageUrl(imageEntity.getImageUrl())
            .build();
    }
        
}
