package com.example.resourceserver.repository;

import com.example.resourceserver.base.dto.TokenEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RevokeTokensJDBSRep extends JpaRepository<TokenEntity, String> {
    @Query("INSERT INTO cloud.revoke_tokens VALUE :token")
    void addRevokeToken(@Param("token") String token);

    TokenEntity getTokenEntityByToken(String token);
}
