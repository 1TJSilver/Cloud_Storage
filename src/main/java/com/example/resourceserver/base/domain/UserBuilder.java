package com.example.resourceserver.base.domain;

import java.util.List;

public class UserBuilder {
    Long userId;
    String login;
    String password;
    List<Content> files;

    public UserBuilder(){}
    public UserBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setFiles(List<Content> files) {
        this.files = files;
        return this;
    }
    //Long userId, String login, String password, List<Content> files

    public User build(){
        return new User(userId, login, password, files);
    }

}
