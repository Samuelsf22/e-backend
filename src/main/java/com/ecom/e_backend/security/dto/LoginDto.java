package com.ecom.e_backend.security.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password

) {}
