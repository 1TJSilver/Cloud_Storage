package com.example.resourceserver.base.domain;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
