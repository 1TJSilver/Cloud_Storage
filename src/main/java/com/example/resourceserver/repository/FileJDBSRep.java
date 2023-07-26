package com.example.resourceserver.repository;

import com.example.resourceserver.base.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface FileJDBSRep extends JpaRepository<Content, Long> {

    Content getContentByFileNameAndUserIdAndNotDeletedTrue(String filename, Long id);

    Content getContentByFileId(Long fileId);

    @Query("INSERT INTO cloud.files VALUE :content")
    void addFileToServer(@Param("content") Content content);

    @Query("select max('file_id') from cloud.files")
    long getLastId();


}
