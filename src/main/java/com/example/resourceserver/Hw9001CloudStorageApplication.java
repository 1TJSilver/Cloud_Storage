package com.example.resourceserver;

import com.example.resourceserver.base.domen.User;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class Hw9001CloudStorageApplication implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Hw9001CloudStorageApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user = User.builder()
                .userId(1L)
                .login("login")
                .password("password")
                .build();

        entityManager.persist(user);
    }
}
