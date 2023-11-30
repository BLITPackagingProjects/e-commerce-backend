package com.blit.ecommerce.project.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface filesOperations_interface {


    String upload(MultipartFile file) throws IOException;

    String generateFileName(String fileName);

    Resource getFileContent(String fileName);

    boolean  deleteFile(String fileName) throws IOException;

    boolean checkFileType(String type);



}
