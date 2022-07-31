package com.webapp.VehicleRental.repository;

import com.webapp.VehicleRental.controller.VehicleListingController;
import com.webapp.VehicleRental.domain.VehicleListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface VehicleListingRepository extends CrudRepository<VehicleListing, String> {


    @Query(value="select * from VehicleRental.vehicle_listing where owner_id = ?1", nativeQuery = true)
    List<VehicleListing> findByUserId(int userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM VehicleRental.vehicle_listing WHERE owner_id = ?1", nativeQuery = true)
    void deleteByUserId(int userId);

    @Query(value= "select * from VehicleRental.vehicle_listing where city = ?1", nativeQuery = true)
    List<VehicleListing> findAllByCity(String city);

    @Query(value= "select * from VehicleRental.vehicle_listing where country = ?1", nativeQuery = true)
    List<VehicleListing> findAllByCountry(String country);

    @Query(value= "select * from VehicleRental.vehicle_listing where price = ?1", nativeQuery = true)
    List<VehicleListing> findAllByPrice(String price);

    @Query(value= "select * from VehicleRental.vehicle_listing where vehicletype = ?1", nativeQuery = true)
    List<VehicleListing> findAllByVehicle(String vehicle);

    // Unimplemented
    @Query(value= "select * from VehicleRental.vehicle_listing where date = ?1", nativeQuery = true)
    List<VehicleListing> findAllByDate(String date);

    @Query(value= "select * from VehicleRental.vehicle_listing where make = ?1", nativeQuery = true)
    List<VehicleListing> findAllByMake(String make);

    @Query(value= "select * from VehicleRental.vehicle_listing where model = ?1", nativeQuery = true)
    List<VehicleListing> findAllByModel(String model);

    @Query(value= "select * from VehicleRental.vehicle_listing where year = ?1", nativeQuery = true)
    List<VehicleListing> findAllByYear(String year);

    @Query(value= "select * from VehicleRental.vehicle_listing where doors = ?1", nativeQuery = true)
    List<VehicleListing> findAllByDoors(String doors);

    @Query(value= "select * from VehicleRental.vehicle_listing where colour = ?1", nativeQuery = true)
    List<VehicleListing> findAllByColour(String colour);



//
//    @Query(value="SELECT * FROM VehicleRental.vehicle_listing WHERE location = ?1", nativeQuery = true)
//    List<Listing> findByUserId(int appUserId);
//
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM VehicleRental.listing WHERE user_id = ?1", nativeQuery = true)
//    void deleteByUserId(int userId);
}
