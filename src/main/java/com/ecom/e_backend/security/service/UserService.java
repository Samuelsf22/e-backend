package com.ecom.e_backend.security.service;

import com.ecom.e_backend.security.dto.CreateUserDto;
import com.ecom.e_backend.security.dto.LoginDto;
import com.ecom.e_backend.security.dto.TokenDto;
import com.ecom.e_backend.security.entity.User;

import reactor.core.publisher.Mono;

public interface UserService {

    Mono<TokenDto> login(LoginDto loginDto);

    Mono<User> create(CreateUserDto createUserDto);

}
