package com.ecom.e_backend.product.infrastructure.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.ecom.e_backend.product.domain.models.Category;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
@Table("api_category")
public class CategoryEntity {

    @Id
    private Long id;

    @Column("public_id")
    private UUID publicId;

    private String name;

    public static CategoryEntity toEntity(Category category) {
        return CategoryEntity.builder()
            .id(category.getId())
            .publicId(category.getPublicId())
            .name(category.getName())
            .build();
    }

    public static Category toDomain(CategoryEntity categoryEntity) {
        return Category.builder()
            .id(categoryEntity.getId())
            .publicId(categoryEntity.getPublicId())
            .name(categoryEntity.getName())
            .build();
    }

}
