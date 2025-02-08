package com.ecom.e_backend.order.controller.dto;

import java.util.UUID;

public record DetailOrderDto(UUID productId, long quantity) {}
