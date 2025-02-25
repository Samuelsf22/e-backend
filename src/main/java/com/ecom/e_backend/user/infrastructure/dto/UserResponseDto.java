package com.ecom.e_backend.user.infrastructure.dto;

import java.util.UUID;

import com.ecom.e_backend.user.domain.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record UserResponseDto(

    @JsonProperty("public_id")
    UUID publicId,

    @JsonProperty("first_name")
    String firstName,

    @JsonProperty("last_name")
    String lastName,

    String username,
    
    String address,

    String roles
) {
    
    public static UserResponseDto fromUser(User user) {
        return UserResponseDto.builder()
            .publicId(user.getPublicId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .username(user.getUsername())
            .address(user.getAddress())
            .roles(user.getRoles())
            .build();
    }
}
