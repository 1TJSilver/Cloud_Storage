package com.example.resourceserver.base;


import jakarta.persistence.*;
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

    @ManyToOne
    @JoinTable(name = "cloud.users",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "user_id")})
    private User owner;
}
