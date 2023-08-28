package com.example.resourceserver.base.domain;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "cloud", name = "files")
@SecondaryTable(schema = "cloud", name = "users",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "userId", referencedColumnName = "user_id"))

public class Content {
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "file_name", nullable = false, length = 200)
    private String fileName;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "content")
    private byte[] content;

    @Column(name = "not_deleted")
    private boolean notDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(table = "users", name = "user_id", referencedColumnName = "user_id")
    /*@JoinTable(schema = "cloud", name = "users",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "user_id")})*/
    private User owner;

    public Content (Long fileId, Long userId, String fileName, byte[] content, boolean notDeleted, User owner){
        this.content = content;
        this.owner = owner;
        this.fileName = fileName;
        this.userId = userId;
        this.notDeleted = notDeleted;
        this.fileId = fileId;
    }

    public Content(){}

    public String getFileName() {
        return fileName;
    }

    public Long getFileId() {
        return fileId;
    }

    public Long getUserId() {
        return userId;
    }

    public byte[] getContent() {
        return content;
    }

    public User getOwner() {
        return owner;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public void setNotDeleted(boolean notDeleted) {
        this.notDeleted = notDeleted;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isNotDeleted() {
        return notDeleted;
    }

    public static ContentBuilder builder (){
        return new ContentBuilder();
    }
}
