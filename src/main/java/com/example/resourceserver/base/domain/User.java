package com.example.resourceserver.base.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY/*, mappedBy = "fileId"*/)
    /*@JoinColumn(table = "cloud.files", name = "user_id", referencedColumnName = "user_id")*/
    private List<Content> files;

    public List<ContentShell> getShellFiles() {
        List<ContentShell> result = new ArrayList<>();
        files.forEach(x -> {
            result.add(new ContentShell(x));
        });
        return result;
    }

    public User() {

    }

    public User(Long userId, String login, String password, List<Content> files) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.files = files;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Content> getFiles() {
        return files;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setFiles(List<Content> files) {
        this.files = files;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }
}
/*@JoinTable(name = "cloud.files",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "")})*/
