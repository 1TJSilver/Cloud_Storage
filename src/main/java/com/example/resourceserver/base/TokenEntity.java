package com.example.resourceserver.base;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "cloud", name = "revoke_tokens")
public class TokenEntity {
    @Id
    private String token;
}
