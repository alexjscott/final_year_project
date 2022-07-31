package com.webapp.VehicleRental.repository;

import com.webapp.VehicleRental.domain.UploadFileResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepository extends CrudRepository<UploadFileResponse, Integer> {

    @Query(value = "SELECT file_name FROM VehicleRental.upload_file_response WHERE vehicle_id = ?1", nativeQuery = true)
    List<String> findImageByReg(String reg);

}
