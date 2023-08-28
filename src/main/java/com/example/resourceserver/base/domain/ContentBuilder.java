package com.example.resourceserver.base.domain;

public class ContentBuilder {
    /*.fileId(repository.getNextFileId())
                .fileName(filename)
                .userId(user.getUserId())
                .content(byteContent)
                .owner(user)
                .notDeleted(true)
                .build());*/
    private Long fileId;
    private String fileName;
    private Long userId;
    private byte[] content;
    private User owner;
    private boolean notDeleted;

    public ContentBuilder setNotDeleted(boolean notDeleted) {
        this.notDeleted = notDeleted;
        return this;
    }

    public ContentBuilder setContent(byte[] content) {
        this.content = content;
        return this;
    }

    public ContentBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ContentBuilder setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public ContentBuilder setFileId(long fileId) {
        this.fileId = fileId;
        return this;
    }

    public ContentBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }
    //Long fileId, Long userId, String fileName, byte[] content, boolean notDeleted, User owner
    public Content build(){
        return new Content(fileId, userId, fileName, content, notDeleted, owner);
    }
}
