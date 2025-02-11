package com.ecom.e_backend.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @JsonProperty("first_name")
        @NotBlank(message = "First name is required")
        @Size(min = 2, message = "First name must be at least 2 characters")
        String firstName,

        @JsonProperty("last_name")
        @NotBlank(message = "Last name is required")
        @Size(min = 2, message = "Last name must be at least 2 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Username is required")
        @Size(min = 3, message = "Username must be at least 3 characters")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotBlank(message = "Address is required")
        String address,
        
        @JsonProperty("image_url")
        String imageUrl
) {}