package com.ecom.e_backend.order.controller.dto;

import java.util.UUID;

public record OrderDto(Long id, UUID publicId, String user, String status) {}
