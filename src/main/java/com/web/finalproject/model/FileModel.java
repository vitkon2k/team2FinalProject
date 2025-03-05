package com.web.finalproject.model;

import lombok.Data;

@Data
public class FileModel {
    private String fileName;
    private String filePath;

    public FileModel(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
