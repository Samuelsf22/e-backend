package com.ecom.e_backend.user;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;

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
public class User {
    @Id
    private Long id;
    private UUID publicId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String imageUrl;
    private Instant createDate;
    private Instant lastModifiedDate;
    private Instant lastSeen;

    public void initDefaultFields() {
        this.publicId = UUID.randomUUID();
    }
}
