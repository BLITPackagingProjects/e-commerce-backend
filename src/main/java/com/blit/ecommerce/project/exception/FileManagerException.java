package com.blit.ecommerce.project.exception;

import java.io.IOException;

public class FileManagerException extends IOException {

    public FileManagerException(String message) {
        super(message);
    }

    public FileManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
