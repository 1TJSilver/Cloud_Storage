package com.example.resourceserver.jwt;

import com.example.resourceserver.base.domen.User;
import com.example.resourceserver.service.AuthorizationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JWTUserDetailsService implements UserDetailsService {

    private final AuthorizationService service;

    public JWTUserDetailsService(AuthorizationService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.findUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new JWTUser(user);
    }
}
