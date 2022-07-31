package com.webapp.VehicleRental.controller;

import com.webapp.VehicleRental.domain.Booking;
import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.domain.VehicleListing;
import com.webapp.VehicleRental.exception.ResourceNotFoundException;
import com.webapp.VehicleRental.repository.BookingRepository;
import com.webapp.VehicleRental.repository.UserRepository;
import com.webapp.VehicleRental.repository.VehicleListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleListingRepository vehicleRepository;
    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Returns All customer bookings.
     * @PathVariable customerId
     * @return bookings
     */
    @GetMapping("/customer/{customerId}/bookings")
    public ResponseEntity<List<Booking>> getAllBookingsByCustomerId(@PathVariable(value = "customerId") int customerId) {
        try {
            if (!userRepository.existsById(customerId)) {
                throw new ResourceNotFoundException("Not found User with id = " + customerId);
            }
            List<Booking> bookings = bookingRepository.findByCustomerId(customerId);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Returns All owner bookings
     * @PathVariable ownerId
     * @return bookings
     */
    @GetMapping("/owner/{ownerId}/bookings")
    public ResponseEntity<List<Booking>> getAllBookingsByOwnerId(@PathVariable(value = "ownerId") int ownerId) {
        try {
            if (!userRepository.existsById(ownerId)) {
                throw new ResourceNotFoundException("Not found User with id = " + ownerId);
            }
            List<Booking> bookings = bookingRepository.findByOwnerId(ownerId);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Returns All vehicle bookings
     * @PathVariable vehicleId
     * @return bookings
     */
    @GetMapping("/vehicle/{vehicleId}/bookings")
    public ResponseEntity<List<Booking>> getAllBookingsByVehicleId(@PathVariable(value = "vehicleId") String vehicleId) {
        try {
            if (!vehicleRepository.existsById(vehicleId)) {
                throw new ResourceNotFoundException("Not found Vehicle with id = " + vehicleId);
            }
            List<Booking> bookings = bookingRepository.findByVehicleId(vehicleId);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Returns All vehicle bookings by bookingId
     * @PathVariable id
     * @return bookings
     */
    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getBookingsId(@PathVariable(value = "id") int id) {
        try {

            Booking bookings = bookingRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Listing with id = " + id));
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete by userId and BookingId
     * @PathVariable userId, bookingId
     * @return HttpStatus
     */
    @DeleteMapping("/user/{userId}/booking/{bookingId}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteBooking(@PathVariable(value = "userId") int userId,
                                                    @PathVariable(value = "bookingId") int bookingId) {
        try {
            // Check User
            if (!userRepository.existsById(userId)) {
                throw new ResourceNotFoundException("Not found User with id : " + userId);
            }
            // Check Booking ID
            if (!bookingRepository.existsById(bookingId)) {
                throw new ResourceNotFoundException("Registration not found : " + bookingId);
            } else {
                bookingRepository.deleteById(bookingId);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Create a booking
     * @PathVariable userId, vehicleID
     * @return HttpStatus
     */
    @PostMapping("/user/{userId}/vehicle/{vehicleID}/booking")
    public HttpStatus createBooking(HttpServletRequest req,
                                    HttpServletResponse res,
                                    @PathVariable(value = "userId") int userId,
                                    @PathVariable(value = "vehicleID") String vehicleID,
                                    Booking bookingRequest) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        VehicleListing vehicleListing = vehicleRepository.findById(vehicleID)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + vehicleID));

        User owner =  vehicleListing.getUser();

        try {
            Booking booking = bookingRepository.save(new Booking(
                    bookingRequest.getDrop_off_date(),
                    bookingRequest.getPickup_date(),
                    bookingRequest.getPickup_time(),
                    bookingRequest.getDays(),
                    bookingRequest.getPrice())
            );

            // Add the user and vehicle id
            booking.setCustomer(user);
            booking.setOwner(owner);
            booking.setVehicleListing(vehicleListing);
            bookingRepository.save(booking);

            new ResponseEntity<>(booking, HttpStatus.CREATED);
            return HttpStatus.CREATED;
        }catch (Exception e){
            System.out.println(e);
            return HttpStatus.BAD_REQUEST;
        }
    }

}
