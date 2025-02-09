package com.ecom.e_backend.security.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecom.e_backend.security.dto.CreateUserDto;
import com.ecom.e_backend.security.dto.LoginDto;
import com.ecom.e_backend.security.dto.TokenDto;
import com.ecom.e_backend.security.entity.User;
import com.ecom.e_backend.security.enums.Role;
import com.ecom.e_backend.security.jwt.JwtProvider;
import com.ecom.e_backend.security.repository.UserRepository;
import com.ecom.e_backend.security.service.UserService;

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
        return userRepository.findByUsernameOrEmail(loginDto.username(), loginDto.username())
                .filter(user -> passwordEncoder.matches(loginDto.password(), user.getPassword()))
                .map(user -> new TokenDto(jwtProvider.generateToken(user)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials")));
    }

    @Override
    public Mono<User> create(CreateUserDto createUserDto) {
        return userRepository.findByUsernameOrEmail(createUserDto.username(), createUserDto.email())
                .switchIfEmpty(Mono.defer(() -> {
                    User user = User.builder()
                            .firstName(createUserDto.firstName())
                            .lastName(createUserDto.lastName())
                            .email(createUserDto.email())
                            .username(createUserDto.username())
                            .password(passwordEncoder.encode(createUserDto.password()))
                            .address(createUserDto.address())
                            .imageUrl(createUserDto.imageUrl())
                            .roles(Role.ROLE_USER.name())
                            .build();
                    return userRepository.save(user);
                }));
    }
}
