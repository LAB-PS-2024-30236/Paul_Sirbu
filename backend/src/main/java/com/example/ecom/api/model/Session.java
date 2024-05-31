package com.example.ecom.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter
@Setter
public class Session {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID uuid;
    private Long userId;
    private LocalDateTime timestamp;
}

