package com.ecom.e_backend.order.controller.dto;

public record OrderedProductDto(String productPublicId, double price, int quantity, String productName) {}
