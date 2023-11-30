package com.blit.ecommerce.project.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FilesOperations_interface {


    String upload(MultipartFile file) throws IOException;

    String generateFileName(String fileName);

    InputStream getFileContent(String path, String fileName) throws FileNotFoundException;

    boolean  deleteFile(String fileName) throws IOException;

    boolean checkFileType(String type);



}
