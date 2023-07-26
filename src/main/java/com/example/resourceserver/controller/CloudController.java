package com.example.resourceserver.controller;

import com.example.resourceserver.base.dto.ContentDTO;
import com.example.resourceserver.exceptions.ContentNotFoundException;
import com.example.resourceserver.service.CloudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cloud")
public class CloudController {

    private CloudService service;

    public CloudController(CloudService service) {
        this.service = service;
    }

    @PostMapping("/file")
    public ResponseEntity fileOnServer(@RequestParam("auth-token") String token,
                                       @RequestParam("filename") String fileName,
                                       @RequestParam("content") byte[] content) throws ContentNotFoundException {
        service.fileToServer(token, fileName, content);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/file")
    public ResponseEntity deleteFileOnServer(@RequestParam("auth-token") String token,
                                             @RequestParam("filename") String fileName) throws ContentNotFoundException {
        service.deleteFileOnServer(token, fileName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/file")
    public ContentDTO getFileFromServer(@RequestParam("auth-token") String token,
                                        @RequestParam("filename") String fileName) throws ContentNotFoundException {
        return service.getFileFromServer(token, fileName).getDTO();
    }

    @PutMapping("/file")
    public ResponseEntity changeFile(@RequestParam("auth-token") String token,
                                     @RequestParam("filename") String fileName,
                                     @RequestParam("name") String newName) throws ContentNotFoundException {
        service.changeFile(token, fileName, newName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public Map<String, Long> getAllFileNames(@RequestParam("auth-token") String token,
                                             @RequestParam("limit") Integer limit) {
        return service.getAllFileNames(token, limit);
    }
}
