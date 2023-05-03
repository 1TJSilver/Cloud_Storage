package com.example.resourceserver.jwt;

import com.example.resourceserver.base.ContentShell;
import com.example.resourceserver.exceptions.JWTAuthenticationException;
import com.example.resourceserver.service.AuthorizationService;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JWTTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("@{jwt.token.expired}")
    private long validityTime;

    private UserDetailsService detailsService;

    private AuthorizationService service;

    @Autowired
    public JWTTokenProvider(UserDetailsService detailsService,
                            AuthorizationService service) {
        this.detailsService = detailsService;
        this.service = service;
    }

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    private void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String login, List<ContentShell> files) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("files", getFileNames(files));

        Date now = new Date();
        Date validityLimit = new Date(now.getTime() + validityTime);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validityLimit)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public List<String> getFileNames(List<ContentShell> files) {
        List<String> result = new ArrayList<>();
        files.forEach(x -> result.add(x.getFileName()));
        return result;
    }

    public Authentication getAuthentication(String token) {
        UserDetails details = detailsService.loadUserByUsername(getLoginWithToken(token));
        return new UsernamePasswordAuthenticationToken(details, "", details.getAuthorities());
    }

    public String getLoginWithToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())
                    || service.isRevoked(token)) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            throw new JWTAuthenticationException("JWT token is expired or invalid");
        }
    }
}
