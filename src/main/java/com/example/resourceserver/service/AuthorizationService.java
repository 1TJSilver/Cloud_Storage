package com.example.resourceserver.service;

import com.example.resourceserver.base.User;
import com.example.resourceserver.jwt.JWTTokenProvider;
import com.example.resourceserver.repository.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    CloudRepository repository;

    private final AuthenticationManager manager;

    private final JWTTokenProvider provider;

    private final AuthorizationService service;

    @Autowired
    public AuthorizationService (AuthenticationManager manager,
                                 JWTTokenProvider provider,
                                 AuthorizationService service,
                                 CloudRepository repository){
        this.manager = manager;
        this.provider = provider;
        this.service = service;
        this.repository = repository;
    }
    public String login(String login, String password){
        User user = getUser(login, password);

        return null;
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
}
