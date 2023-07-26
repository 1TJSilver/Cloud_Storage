package com.example.resourceserver.controller;

import com.example.resourceserver.base.dto.AuthenticationDTO;
import com.example.resourceserver.base.domain.User;
import com.example.resourceserver.exceptions.JWTAuthenticationException;
import com.example.resourceserver.jwt.JWTTokenProvider;
import com.example.resourceserver.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cloud")
public class AuthorizationController {
    private final AuthenticationManager manager;

    private final JWTTokenProvider provider;

    private final AuthorizationService service;

    @Autowired
    public AuthorizationController (AuthenticationManager manager,
                                    JWTTokenProvider provider,
                                    AuthorizationService service){
        this.manager = manager;
        this.provider = provider;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO dto){
        try {
            String username = dto.getLogin();
            manager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            User user = service.findUser(username);

            if (user == null){
                throw new JWTAuthenticationException("User " + username + " not found");
            }

            String token = provider.createToken(username, user.getShellFiles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex){
            throw new BadCredentialsException("Invalid login or password");
        }
    }

    @PostMapping("/logout")
    public void logout (String token){
        service.logout(token);
    }

}
