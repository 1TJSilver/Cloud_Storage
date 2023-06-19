package com.example.resourceserver.base.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class ContentDTO {
    private String filename;
    private byte[] content;
}
