package com.example.resourceserver.base.domain;

import com.example.resourceserver.base.dto.ContentDTO;
import com.example.resourceserver.base.dto.ContentDTOBuilder;
import com.example.resourceserver.exceptions.InternalServerException;
import lombok.Data;

import java.io.*;

@Data
public class ContentShell {
    private Content content;
    private Long fileId;
    private String fileName;
    private Long userId;
    private byte[] byteContent;
    private File fileContent;
    private boolean notDeleted;

    public ContentShell(Content content) {
        this.fileId = content.getFileId();
        this.fileName = content.getFileName();
        this.userId = content.getUserId();
        this.byteContent = content.getContent();
        fileContent = new File(fileName);
        this.notDeleted = content.isNotDeleted();

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileContent))) {
            bos.write(byteContent);
        } catch (IOException ex) {
            throw new InternalServerException("Internal server exception. Try check filename.");
        }
    }

    /*public boolean downloadFile() throws IOException {
        return fileContent.createNewFile();
    }*/

    public Content getContent() {
        return content;
    }

    public ContentDTO createDTO(){
        return ContentDTOBuilder.create(this);
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getByteContent() {
        return byteContent;
    }
}
