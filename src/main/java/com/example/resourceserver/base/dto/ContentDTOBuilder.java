package com.example.resourceserver.base.dto;

import com.example.resourceserver.base.domain.ContentShell;

public class ContentDTOBuilder {
    public static ContentDTO create(ContentShell shell){
        return new ContentDTO(shell.getFileName(), shell.getByteContent());
    }
}
