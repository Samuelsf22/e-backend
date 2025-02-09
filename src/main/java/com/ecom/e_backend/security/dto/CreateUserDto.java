package com.ecom.e_backend.security.dto;

public record CreateUserDto(String firstName, String lastName ,String email, String username, String password, String address, String imageUrl) {}