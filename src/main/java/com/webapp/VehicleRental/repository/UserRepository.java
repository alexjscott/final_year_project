package com.webapp.VehicleRental.repository;

import com.webapp.VehicleRental.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {



    @Query(value = "SELECT id FROM VehicleRental.user WHERE email = ?1", nativeQuery = true)
    String finduseridbyemail(String email);

    @Query(value = "SELECT id FROM VehicleRental.user WHERE password = ?1", nativeQuery = true)
    String finduseridbypassword(String password);



}
