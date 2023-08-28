package com.example.resourceserver.base.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ContentDTO {
    private String filename;
    private byte[] content;

    public ContentDTO(String filename, byte[] content){
        this.content = content;
        this.filename = filename;
    }

    public ContentDTO(){}
}
