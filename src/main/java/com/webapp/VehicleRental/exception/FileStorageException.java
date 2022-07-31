package com.webapp.VehicleRental.exception;

/**
 * [Source code]. https://www.callicoder.com/spring-boot-file-upload-download-jpa-hibernate-mysql-database-example/
 */

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}