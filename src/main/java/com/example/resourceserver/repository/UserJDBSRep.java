package com.example.resourceserver.repository;

import com.example.resourceserver.base.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJDBSRep extends JpaRepository<User, Long> {
    User findUserByLoginAndPassword(String login, String password);

    User findUserByLogin(String username);

}
