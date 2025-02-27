package com.ecom.e_backend.auth.domain;

import lombok.Builder;

@Builder
public record AuthToken(String token) {}
