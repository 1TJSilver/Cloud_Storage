package com.example.resourceserver.base.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "cloud", name = "revoke_tokens")
public class TokenEntity {
    @Id
    private String token;

    public String getToken() {
        return token;
    }
}
