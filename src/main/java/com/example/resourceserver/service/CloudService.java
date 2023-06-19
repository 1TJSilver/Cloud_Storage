package com.example.resourceserver.service;

import com.example.resourceserver.base.domen.Content;
import com.example.resourceserver.base.domen.ContentShell;
import com.example.resourceserver.base.domen.User;
import com.example.resourceserver.exceptions.ContentNotFoundException;
import com.example.resourceserver.jwt.JWTTokenProvider;
import com.example.resourceserver.repository.CloudRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudService {
    private CloudRepository repository;
    private JWTTokenProvider provider;

    public CloudService( CloudRepository repository,
                        JWTTokenProvider provider) {
        this.repository = repository;
        this.provider = provider;
    }

    public void fileToServer(String token, String filename, byte[] byteContent) throws ContentNotFoundException{
        User user = getUserByToken(token);
        ContentShell content = new ContentShell(Content.builder()
                .fileId(repository.getNextFileId())
                .fileName(filename)
                .userId(user.getUserId())
                .content(byteContent)
                .owner(user)
                .notDeleted(true)
                .build());
        user.getFiles().add(content.getContent());
        repository.addFileToServer(content);
    }

    public void deleteFileOnServer(String token, String filename) throws ContentNotFoundException{
        ContentShell content = getContentShellByToken(token, filename);
        repository.deleteByFileId(content.getFileId());
    }
    public ContentShell getFileFromServer (String token, String filename) throws ContentNotFoundException {
        return getContentShellByToken(token, filename);
    }

    public User getUserByToken (String token){
        return repository.findUser(provider.getLoginWithToken(token));
    }
    public ContentShell getContentShellByToken(String token, String filename) throws ContentNotFoundException {
        User user = getUserByToken(token);
        ContentShell contentShell = new ContentShell(repository.getContentByFileNameAndUserId(
                filename, user.getUserId()
        ));
        if (contentShell.isNotDeleted()) {
            return contentShell;
        } else {
            throw new ContentNotFoundException();
        }
    }

    public void changeFile(String token, String filename, String newName) throws ContentNotFoundException{
        ContentShell contentShell = getContentShellByToken(token, filename);
        contentShell.setFileName(newName);
    }

    public Map<String, Long> getAllFileNames (String token, Integer limit){
        User user = getUserByToken(token);
        List<ContentShell> files = user.getShellFiles();
        Map<String, Long> result = new HashMap<>();
        for (int i = 0; i < limit; i++){
            ContentShell x = files.get(i);
            if (x.isNotDeleted()){
                result.put(x.getFileName(), x.getFileContent().length());
            }
        }
        return result;
    }
}
