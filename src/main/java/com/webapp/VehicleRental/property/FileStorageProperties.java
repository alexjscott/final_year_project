package com.webapp.VehicleRental.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * [Source code]. https://www.callicoder.com/spring-boot-file-upload-download-jpa-hibernate-mysql-database-example/
 */

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
