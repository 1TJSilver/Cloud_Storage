package com.example.resourceserver.repository;

import com.example.resourceserver.base.Content;
import com.example.resourceserver.base.ContentShell;
import com.example.resourceserver.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CloudRepository {
    UserJDBSRep jdbcAuth;

    RevokeTokensJDBSRep jdbcToken;

    FileJDBSRep jdbcFile;
    @Autowired
    public CloudRepository(UserJDBSRep jdbcAuth,
                           RevokeTokensJDBSRep jdbcToken,
                           FileJDBSRep jdbcFile){
        this.jdbcAuth = jdbcAuth;
        this.jdbcToken = jdbcToken;
        this.jdbcFile = jdbcFile;
    }

    public User findUser(String username){
        return jdbcAuth.findUserByLogin(username);
    }
    public User getUser(String login, String password){
        return jdbcAuth.findUserByLoginAndPassword(login, password);
    }

    public void addRevokeToken(String token){
        jdbcToken.addRevokeToken(token);
    }
    public boolean isRevoked(String token) {
        if (jdbcToken.getTokenEntityByToken(token).getToken() == null) {
            return false;
        } else return true;
    }
    public void addFileToServer(ContentShell contentShell){
        jdbcFile.addFileToServer(contentShell.getContent());
    }

    public void deleteByFileId(Long fileId){
        jdbcFile.getContentByFileId(fileId).setNotDeleted(false);
    }

    public long getNextFileId(){
        return jdbcFile.getLastId() + 1;
    }

    public Content getContentByFileNameAndUserId(String filename, Long id){
        return jdbcFile.getContentByFileNameAndUserIdAndNotDeletedTrue(filename, id);
    }

    //public void changeName ()

}
