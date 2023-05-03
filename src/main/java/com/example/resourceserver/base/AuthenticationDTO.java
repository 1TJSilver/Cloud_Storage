package com.example.resourceserver.base;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String login;
    private String password;
}
