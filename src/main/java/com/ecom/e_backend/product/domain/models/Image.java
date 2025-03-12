package com.ecom.e_backend.product.domain.models;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Image {

    private Long id;
    private UUID publicId;
    private String name;
    private String imageUrl;

}
