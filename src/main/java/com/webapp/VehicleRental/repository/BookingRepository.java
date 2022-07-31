package com.webapp.VehicleRental.repository;

import com.webapp.VehicleRental.domain.Booking;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    @Query(value="select * from VehicleRental.booking where customer_id = ?1", nativeQuery = true)
    List<Booking> findByCustomerId(int customer_id);

    @Query(value="select * from VehicleRental.booking where owner_id = ?1", nativeQuery = true)
    List<Booking> findByOwnerId(int customer_id);

    @Query(value="select * from VehicleRental.booking where vehicle_listing_id = ?1", nativeQuery = true)
    List<Booking> findByVehicleId(String vehicle_listing_id);

    // Not used
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM VehicleRental.booking WHERE customer_id = ?1", nativeQuery = true)
    void deleteByCustomer_id(int userId);
}
