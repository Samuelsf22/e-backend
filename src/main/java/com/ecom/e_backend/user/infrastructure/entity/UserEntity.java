package com.ecom.e_backend.user.infrastructure.entity;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecom.e_backend.user.domain.models.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Table(name = "api_user")
public class UserEntity implements UserDetails {

    @Id
    private Long id;

    @Column("public_id")
    private UUID publicId;

    private String username;

    private String password;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    private String address;

    private String roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(roles.split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .publicId(user.getPublicId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .roles(user.getRoles())
                .build();
    }

    public static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .publicId(entity.getPublicId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .address(entity.getAddress())
                .roles(entity.getRoles())
                .build();
    }
}
