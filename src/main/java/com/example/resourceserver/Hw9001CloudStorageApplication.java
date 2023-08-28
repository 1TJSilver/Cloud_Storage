package com.example.resourceserver;

import com.example.resourceserver.base.domain.User;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
@EntityScan({"com.example.resourceserver.base.domain", "com.example.resourceserver.entity"})
@EnableJpaRepositories("com.example.resourceserver.repository")
@ComponentScan({"com.example.resourceserver.controller", "com.example.resourceserver.service",
        "com.example.resourceserver.jwt", "com.example.resourceserver.config", "com.example.resourceserver.repository"})
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
                .setUserId(1L)
                .setLogin("login")
                .setPassword("password")
                .build();
        entityManager.persist(user);
    }
}
