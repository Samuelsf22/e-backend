package com.ecom.e_backend.user.application;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecom.e_backend.exception.CustomException;
import com.ecom.e_backend.user.domain.UserRepository;
import com.ecom.e_backend.user.domain.UserService;
import com.ecom.e_backend.user.domain.models.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> findByPublicId(UUID publicId) {
        return userRepository.findByPublicId(publicId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "User not found")));
    }

    @Override
    public Mono<User> update(UUID publicId, User user) {
        return null;
    }

    @Override
    public Mono<Void> delete(UUID publicId) {
        return userRepository.findByPublicId(publicId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "User not found")))
                .flatMap(user -> userRepository.deleteByPublicId(publicId));
    }

}
