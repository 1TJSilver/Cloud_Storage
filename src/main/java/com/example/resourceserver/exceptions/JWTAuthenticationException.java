package com.example.resourceserver.exceptions;

import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationException extends AuthenticationException {
    public JWTAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public JWTAuthenticationException(String msg) {
        super(msg);
    }
}
