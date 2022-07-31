package com.webapp.VehicleRental.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.webapp.VehicleRental.repository.FileRepository;
import com.webapp.VehicleRental.repository.UserRepository;
import com.webapp.VehicleRental.repository.VehicleListingRepository;
import com.webapp.VehicleRental.service.FileStorageService;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

@Entity
@Table(name = "uploadFileResponse")
public class UploadFileResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @ManyToOne
    @JoinColumn(name="vehicle_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VehicleListing vehicleListing;

//    @OneToOne(mappedBy = "uploadFileResponse")
//    private VehicleListing vehicleListing;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "fileDownloadUri")
    private String fileDownloadUri;

    @Column(name = "fileType")
    private String fileType;

    @Column(name = "size")
    private long size;

    public UploadFileResponse() {
    }

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, VehicleListing vehicleListing) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.vehicleListing = vehicleListing;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleListing getVehicleListing() {
        return vehicleListing;
    }

    public void setVehicleListing(VehicleListing vehicleListing) {
        this.vehicleListing = vehicleListing;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}