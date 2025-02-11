package com.ecom.e_backend.security.entity;

import java.time.Instant;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "api_user")
public class User implements UserDetails {
    @Id
    private Long id;

    @Column("public_id")    
    private UUID publicId;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("email")
    private String email;

    @Column("username")
    private String username;

    @JsonIgnore
    @Column("password")
    private String password;

    @Column("address")
    private String address;

    @Column("image_url")
    private String imageUrl;

    @Column("create_date")
    private Instant createDate;

    @Column("last_modified_date")
    private Instant lastModifiedDate;

    @Column("last_seen")
    private Instant lastSeen;

    @Column("roles")
    private String roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(roles.split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
