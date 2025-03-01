package com.ecom.e_backend.product;


import java.util.UUID;

import org.springframework.data.annotation.Id;

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
public class Category {
    @Id
    private Long id;
    private UUID publicId;
    private String name;

    public void initDefaultFields() {
        this.publicId = UUID.randomUUID();
    }
}
