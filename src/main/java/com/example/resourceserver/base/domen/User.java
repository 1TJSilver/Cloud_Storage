package com.example.resourceserver.base.domen;


import com.example.resourceserver.base.domen.Content;
import com.example.resourceserver.base.domen.ContentShell;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "cloud", name = "users")
@Builder

public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "fileId")
    /*@JoinColumn(table = "cloud.files", name = "user_id", referencedColumnName = "user_id")*/
    private List<Content> files;

    public List<ContentShell> getShellFiles() {
        List<ContentShell> result = new ArrayList<>();
        files.forEach(x -> {
            result.add(new ContentShell(x));
        });
        return result;
    }
}
/*@JoinTable(name = "cloud.files",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "")})*/
