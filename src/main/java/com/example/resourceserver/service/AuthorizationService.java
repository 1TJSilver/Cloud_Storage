package com.example.resourceserver.service;

import com.example.resourceserver.base.domain.User;
import com.example.resourceserver.jwt.JWTTokenProvider;
import com.example.resourceserver.repository.CloudRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    CloudRepository repository;

    private AuthenticationManager manager;

    private JWTTokenProvider provider;

    //private final AuthorizationService service;

    public AuthorizationService (){;
    }

    public void logout(String token){
        repository.addRevokeToken(token);
    }

    public boolean isRevoked(String token){
        return repository.isRevoked(token);
    }

    public User getUser(String login, String password){
        return repository.getUser(login, password);
    }

    public User findUser(String username){
        return repository.findUser(username);
    }

    public void setManager(AuthenticationManager manager) {
        this.manager = manager;
    }

    public void setProvider(JWTTokenProvider provider) {
        this.provider = provider;
    }

    public void setRepository(CloudRepository repository) {
        this.repository = repository;
    }
}
