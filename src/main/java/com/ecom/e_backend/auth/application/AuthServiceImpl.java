package com.ecom.e_backend.auth.application;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.auth.domain.AuthService;
import com.ecom.e_backend.auth.domain.AuthToken;
import com.ecom.e_backend.auth.infrastructure.jwt.JwtProvider;
import com.ecom.e_backend.exception.CustomException;
import com.ecom.e_backend.user.domain.Role;
import com.ecom.e_backend.user.domain.UserRepository;
import com.ecom.e_backend.user.domain.models.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public Mono<AuthToken> login(String username, String password) {
        return userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "Username not found")))
                .flatMap(user -> {
                    if (!passwordEncoder.matches(password, user.getPassword()))
                        return Mono.error(new CustomException(HttpStatus.UNAUTHORIZED, "Incorrect password"));

                    return Mono.just(new AuthToken(generateToken(user)));
                });
    }

    @Override
    public Mono<AuthToken> create(User user) {
        return userRepository.existsByUsername(user.getUsername())
                .flatMap(exists -> {
                    if (exists)
                        return Mono.error(new CustomException(HttpStatus.CONFLICT, "Username already exists"));

                    user.setPublicId(UUID.randomUUID());
                    user.setRoles(Role.USER.name());
                    user.setPassword(passwordEncoder.encode(user.getPassword()));

                    return userRepository.save(user)
                            .map(savedUser -> new AuthToken(generateToken(savedUser)));
                });
    }

    private String generateToken(User user) {
        List<GrantedAuthority> authorities = Arrays.stream(user.getRoles().split(",\\s*"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
        return jwtProvider.generateToken(userDetails);
    }

}
