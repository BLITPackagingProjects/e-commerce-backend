package com.blit.ecommerce.project.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class filesManagerProperties {

    private final String storageDir = "files_storage";

    private  String[] acceptableTypes = {"image/jpeg","image/png","image/bmp","image/gif"};
    private boolean generateFilesName = true;

    private boolean overWrite = false;


    public filesManagerProperties() {
    }

    public filesManagerProperties(String[] acceptableTypes) {
        this.acceptableTypes = acceptableTypes;
    }

    public filesManagerProperties(boolean generateFilesName, boolean overWrite) {
        this.generateFilesName = generateFilesName;
        this.overWrite = overWrite;
    }

    public filesManagerProperties(String[] acceptableTypes, boolean generateFilesName, boolean overWrite) {
        this.acceptableTypes = acceptableTypes;
        this.generateFilesName = generateFilesName;
        this.overWrite = overWrite;
    }


}