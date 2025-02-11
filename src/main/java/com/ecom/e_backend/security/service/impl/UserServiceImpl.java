package com.ecom.e_backend.security.service.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.security.dto.CreateUserDto;
import com.ecom.e_backend.security.dto.LoginDto;
import com.ecom.e_backend.security.dto.TokenDto;
import com.ecom.e_backend.security.entity.User;
import com.ecom.e_backend.security.enums.Role;
import com.ecom.e_backend.security.jwt.JwtProvider;
import com.ecom.e_backend.security.repository.UserRepository;
import com.ecom.e_backend.security.service.UserService;
import com.ecom.e_backend.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public Mono<TokenDto> login(LoginDto loginDto) {
        return userRepository.findByEmail(loginDto.email())
                .filter(user -> passwordEncoder.matches(loginDto.password(), user.getPassword()))
                .map(user -> new TokenDto(jwtProvider.generateToken(user)))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid credentials")));
    }

    @Override
    public Mono<User> create(CreateUserDto createUserDto) {
        User user = User.builder()
                .publicId(UUID.randomUUID())
                .firstName(createUserDto.firstName())
                .lastName(createUserDto.lastName())
                .email(createUserDto.email())
                .username(createUserDto.username())
                .password(passwordEncoder.encode(createUserDto.password()))
                .address(createUserDto.address())
                .imageUrl(createUserDto.imageUrl())
                .roles(Role.ROLE_USER.name())
                .build();

        return userRepository.findByEmail(createUserDto.email())
                .hasElement()
                .flatMap(exists -> exists
                        ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Email already exists"))
                        : userRepository.save(user));
    }
}
