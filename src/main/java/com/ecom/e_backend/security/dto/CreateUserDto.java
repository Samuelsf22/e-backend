package com.ecom.e_backend.security.dto;

public record CreateUserDto(String first_name, String last_name ,String email, String username, String password, String address, String imageUrl) {}