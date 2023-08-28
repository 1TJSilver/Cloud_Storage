package com.example.resourceserver.base.dto;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
