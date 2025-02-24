package com.ecom.e_backend.user.domain.models;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    private Long id;
    private UUID publicId;
    private String firstName;
    private String lastName;
    private String address;
    private String username;
    private String password;
    private String roles;
    
}
