package com.example.resourceserver.base;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "cloud", name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    @OneToMany
    @JoinTable(name = "cloud.files",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<Content> files;

    public List<ContentShell> getShellFiles() {
        List<ContentShell> result = new ArrayList<>();
        files.forEach(x -> {
            result.add(new ContentShell(x));
        });
        return result;
    }
}
