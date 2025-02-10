package com.ecom.e_backend.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserDto(
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        String email,
        String username,
        String password,
        String address,
        
        @JsonProperty("image_url")
        String imageUrl
) {}