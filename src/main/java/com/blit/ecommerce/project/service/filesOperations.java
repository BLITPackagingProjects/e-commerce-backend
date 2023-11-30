package com.blit.ecommerce.project.service;

import com.blit.ecommerce.project.exception.FileManagerException;
import com.blit.ecommerce.project.config.FilesManagerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;

@Service
public class filesOperations implements FilesOperations_interface {


    private final FilesManagerProperties properties;

   @Autowired
   public filesOperations(FilesManagerProperties properties) {
        this.properties = properties;
   }

    @Override
    public String upload(MultipartFile file) throws IOException {
        if(file.isEmpty()){

            throw new FileManagerException("No file found!");
        }

        InputStream getfile = file.getInputStream();


        //check the file type
            if(! this.checkFileType(file.getContentType())) throw new FileManagerException("Unsupported file type!");


        //check if the generate file name is true, it means will generate a new name to the file in the storage.
        String FileName = (properties.isGenerateFilesName())?
                this.generateFileName(file.getOriginalFilename()).replace(" ","-") : file.getOriginalFilename().replace(" ","-");


        // Get the target directory
        if(properties.getStorageDir().trim().isEmpty()){

            throw new FileManagerException("Error in the storage directory configuration! ");
        }

        // Assign the storage directory
        Path Dir = Paths.get(properties.getStorageDir());

        Path targetPath = Dir.resolve(FileName).normalize().toAbsolutePath();

        File getFileStatus  =  new File( String.valueOf(targetPath));


        if(getFileStatus.exists() && !properties.isOverWrite()){
            throw new FileManagerException("Unable to upload the '"+file.getOriginalFilename()+"' file, it's already exists. ");
        }else {
            Files.copy(getfile,targetPath, StandardCopyOption.REPLACE_EXISTING );
        }

        return FileName;

    }

    @Override
    public String generateFileName(String fileName) {


       int dotPos =  fileName.lastIndexOf('.');

       // Extract the extension
       String extension = "";
       if(dotPos != -1){
           extension = fileName.substring(dotPos).toLowerCase();
       }

       // Extract the file name
        String getFileName = (!extension.trim().isEmpty())? fileName.replace(extension,"").toLowerCase() : fileName;

       if(getFileName.length() > 10){

           getFileName = getFileName.substring(0,10);
       }


       //Generate random number
        int num = new Random().nextInt(1000);

        // GetDate
        LocalDate date = LocalDate.now();


        return getFileName+"-"+date+num+extension;
    }

    @Override
    public InputStream getFileContent(String path,String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;

        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;

    }

    @Override
    public boolean deleteFile(String fileName) throws IOException {

       Path fullPath = Paths.get(properties.getStorageDir()).resolve(fileName).normalize().toAbsolutePath();

        return Files.deleteIfExists(fullPath);

    }


    @Override
    public boolean checkFileType(String type){

        String[] getTypes = properties.getAcceptableTypes();

        if(getTypes.length > 0 && !getTypes[0].equalsIgnoreCase("all")){
            //  if  the (getAcceptableTypes) array has values.

           return  Arrays.asList(getTypes).contains(type);
        }else {
            return true;
        }

    }
}
